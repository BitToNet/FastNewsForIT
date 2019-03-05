package com.chingsoft.museum.activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chingsoft.museum.R;
import com.chingsoft.museum.adapter.MonitorTypeAdapter;
import com.chingsoft.museum.apps.App;
import com.chingsoft.museum.base.activity.BaseActivity;
import com.chingsoft.museum.bean.BaseBean;
import com.chingsoft.museum.bean.DataBean;
import com.chingsoft.museum.bean.HumidityBean;
import com.chingsoft.museum.bean.LightingBean;
import com.chingsoft.museum.bean.ShopBean;
import com.chingsoft.museum.bean.TemperatureBean;
import com.chingsoft.museum.bean.UvBean;
import com.chingsoft.museum.bean.ValueBean;
import com.chingsoft.museum.http.RetrofitManager;
import com.chingsoft.museum.utils.ChartUtils;
import com.chingsoft.museum.utils.LogUtils;
import com.chingsoft.museum.utils.TimeUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import org.angmarch.views.NiceSpinner;

public class ShowingDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView     mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar      toolbar;
    @BindView(R.id.tv_name)
    TextView     mTvName;
    @BindView(R.id.tv_state)
    TextView     mTvState;
    @BindView(R.id.recyclerview_state)
    RecyclerView gridRecyclerview;
    @BindView(R.id.ns_left)
    NiceSpinner  nsLeft;
    @BindView(R.id.ll_showing_detail)
    LinearLayout mLlShowingDetail;
    @BindView(R.id.chart)
    LineChart    mChart;
    @BindView(R.id.ns_right)
    NiceSpinner  nsRight;

    private ShopBean           shopBean;
    private MonitorTypeAdapter monitorTypeAdapter;
    private String             today;
    private long               endTime;
    private long               startTime;
    private String             type;
    private List<ValueBean>    values;
    private ArrayList<Entry> sbpList   = new ArrayList<>();
    private int              mPosition = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_showing_detail;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, mTvTitle, true, "博物馆");
        shopBean = (ShopBean) getSerializableExtra("ShopBean");
        List<String> monitorType = shopBean.getRepoArea().getCurrentMonitor().getMonitorType();

        mTvName.setText(shopBean.getDataList().get(0).getDevice().getName());
        if (shopBean.getDataList().get(0).getDevice().getIsConnect() == 0) {
            mTvState.setText("未连接");
            mTvState.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
        } else {
            mTvState.setText("已连接");
            mTvState.setTextColor(ContextCompat.getColor(this, R.color.text_color_green));
        }

        values = getValueBeans(monitorType);
        gridRecyclerview.setNestedScrollingEnabled(false);
        gridRecyclerview.setHasFixedSize(true);
        gridRecyclerview.setLayoutManager(new GridLayoutManager(this, 5));
        monitorTypeAdapter = new MonitorTypeAdapter(values, shopBean.getRepoArea());
        gridRecyclerview.setAdapter(monitorTypeAdapter);

        //下半部分的初始化
        initSpinner();
        initListener();

        //初始化默认湿度和一小时
        if(values.size()>0){
            type = values.get(0).getObjectType();
        }

        setTime(1);
        getChartReport();

        initCalendar();
    }

    private void setTime(int i) {
        SimpleDateFormat format       = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar         cal          = Calendar.getInstance();
        String           start_update = format.format(cal.getTime());
        LogUtils.d(start_update, "=====");
        endTime = TimeUtils.datetimeToLong(start_update);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - i);
        String end_update = format.format(cal.getTime());
        startTime = TimeUtils.datetimeToLong(end_update);
    }

    private void initSpinner() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nsLeft.setBackground(this.getDrawable(R.drawable.ic_bg_spinner));
            nsRight.setBackground(this.getDrawable(R.drawable.ic_bg_spinner));
        }
        List<String> datasetLeft  = new LinkedList<>();
        List<String> datasetRight = new LinkedList<>();
        if(values.size()>0){
            for (int i = 0; i < values.size(); i++) {
                datasetLeft.add(values.get(i).getName());
            }
            nsLeft.attachDataSource(datasetLeft);
        }

        datasetRight.add("1小时内");
        datasetRight.add("3小时内");
        datasetRight.add("12小时内");
        datasetRight.add("24小时内");


        nsRight.attachDataSource(datasetRight);
    }

    private void initListener() {
        nsRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setTime(1);
                        break;
                    case 1:
                        setTime(3);
                        break;
                    case 2:
                        setTime(12);
                        break;
                    case 3:
                        setTime(24);
                        break;
                }
                getChartReport();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nsLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                type = values.get(position).getObjectType();
                getChartReport();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 通过monitorType选出可用的标准
     *
     * @param monitorType
     * @return
     */
    @NonNull
    private List<ValueBean> getValueBeans(List<String> monitorType) {
        List<ValueBean> values = new ArrayList<>();
        for (int i = 0; i < monitorType.size(); i++) {
            switch (monitorType.get(i)) {
                case "HUMIDITY":
                    if(shopBean.getDataList().get(0).getHumidity()==null){
                        break;
                    }
                    HumidityBean humidity = shopBean.getDataList().get(0).getHumidity();
                    values.add(new ValueBean(humidity.getId(), "湿度", humidity.getValue(),
                                             humidity.getObjectType()));
                    break;
                case "TEMPERATURE":
                    if(shopBean.getDataList().get(0).getTemperature()==null){
                        break;
                    }
                    TemperatureBean temperature = shopBean.getDataList().get(0).getTemperature();
                    values.add(new ValueBean(temperature.getId(), "温度", temperature.getValue(),
                                             temperature.getObjectType()));
                    break;
                case "UV":
                    if(shopBean.getDataList().get(0).getUv()==null){
                        break;
                    }
                    UvBean uv = shopBean.getDataList().get(0).getUv();
                    values.add(new ValueBean(uv.getId(), "紫外线", uv.getValue(), uv.getObjectType()));
                    break;
                case "LIGHTING":
                    if(shopBean.getDataList().get(0).getLighting()==null){
                        break;
                    }
                    LightingBean lighting = shopBean.getDataList().get(0).getLighting();
                    values.add(new ValueBean(lighting.getId(), "光照", lighting.getValue(),
                                             lighting.getObjectType()));
                    break;
                default:
                    break;
            }
        }
        return values;
    }

    private void initCalendar() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected View injectTarget() {
        return mLlShowingDetail;
    }

    @OnClick({R.id.ns_left, R.id.ns_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ns_left:
                break;
            case R.id.ns_right:
                break;
        }
    }

    private void getChartReport() {
        if(values.size()>0){
            mStateView.showLoading();
            RetrofitManager.getInstance()
                           .getApiService()
                           .getHistoryData("token", endTime,
                                           shopBean.getDataList().get(0).getDevice().getId(), startTime,
                                           type, App.getSessionId())
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new Observer<BaseBean<List<DataBean>>>() {

                               @Override
                               public void onSubscribe(Disposable d) {
                               }

                               @Override
                               public void onNext(BaseBean<List<DataBean>> bean) {
                                   mStateView.hidenLoading();
                                   if (bean.success()) {
                                       mStateView.hidenLoading();
                                       setLineData(bean);
                                   } else {
                                       showToast(bean.getMessage());
                                   }

                               }

                               @Override
                               public void onError(Throwable e) {
                                   mStateView.hidenLoading();
                                   showToast(e.toString());
                               }

                               @Override
                               public void onComplete() {

                               }
                           });
        }else {
            showToast("该设备未上传信息");
        }

    }

    private void setLineData(BaseBean<List<DataBean>> bean) {
        List<DataBean> result = bean.getResult();
        sbpList.clear();
        for (int i = 0, length = result.size(); i < length; i++) {
            sbpList.add(new Entry(i, result.get(i).getValue(), result.get(i)));
        }

        float v = bean.getMax() - bean.getMin();
        switch (values.get(mPosition).getObjectType()) {
            case "HUMIDITY":
                ChartUtils.initBpChart(context, mChart, bean.getMax()+v/3, bean.getMin()-v/3, 2, "%", sbpList,
                                       shopBean.getRepoArea().getSaveStandard().getHumidityMax(),
                                       shopBean.getRepoArea().getSaveStandard().getHumidityMin(),
                                       values.get(mPosition).getName());
                break;
            case "TEMPERATURE":
                ChartUtils.initBpChart(context, mChart, bean.getMax()+v/3, bean.getMin()-v/3, 2, "℃", sbpList,
                                       shopBean.getRepoArea().getSaveStandard().getTemperatureMax(),
                                       shopBean.getRepoArea().getSaveStandard().getTemperatureMin(),
                                       values.get(mPosition).getName());

                break;
            case "UV":
                ChartUtils.initBpChart(context, mChart, bean.getMax()+v/3, bean.getMin()-v/3, 2, "uW/lm", sbpList,
                                       (int) shopBean.getRepoArea().getSaveStandard().getUv(), 0,
                                       values.get(mPosition).getName());
                break;
            case "LIGHTING":
                ChartUtils.initBpChart(context, mChart, bean.getMax()+v/3, bean.getMin()-v/3, 2, "lux", sbpList,
                                       (int) shopBean.getRepoArea().getSaveStandard().getLighting(),
                                       0, values.get(mPosition).getName());
                break;
            default:
                break;
        }


    }
}
