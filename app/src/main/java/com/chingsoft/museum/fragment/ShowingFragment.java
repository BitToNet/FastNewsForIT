package com.chingsoft.museum.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chingsoft.museum.R;
import com.chingsoft.museum.activity.ShowingDetailActivity;
import com.chingsoft.museum.adapter.GridShopAdapter;
import com.chingsoft.museum.apps.App;
import com.chingsoft.museum.base.fragment.BaseFragment;
import com.chingsoft.museum.bean.BaseBean;
import com.chingsoft.museum.bean.ClassifyBean;
import com.chingsoft.museum.bean.ShopBean;
import com.chingsoft.museum.http.RetrofitManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.angmarch.views.NiceSpinner;

/**
 * Created by Felone on 2019/2/20 0020 下午 3:28
 * description：
 * 50257836@qq.com
 */
public class ShowingFragment extends BaseFragment {

    List<String> dataset = new LinkedList<>();

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.nice_spinner)
    NiceSpinner  mNiceSpinner;
    @BindView(R.id.recyclerview_department)
    RecyclerView gridRecyclerview;
    @BindView(R.id.root_layout)
    LinearLayout mRootLayout;

    private List<ClassifyBean> data;
    private List<ShopBean>     beanList;
    private GridShopAdapter    gridShopAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_showing;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, mTvTitle, false, "博物馆", false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mNiceSpinner.setBackground(getContext().getDrawable(R.drawable.ic_bg_spinner));
        }
        mNiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadShopData(data.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridRecyclerview.setNestedScrollingEnabled(false);
        gridRecyclerview.setHasFixedSize(true);
        gridRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        beanList = new ArrayList<>();
        gridShopAdapter = new GridShopAdapter(beanList);
        gridRecyclerview.setAdapter(gridShopAdapter);
        gridShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ShopBean", beanList.get(position));
                openActivity(ShowingDetailActivity.class,bundle,false );
            }
        });

    }

    @Override
    protected void loadData() {
        RetrofitManager.getInstance()
                       .getApiService()
                       .getHallList("token", App.getSessionId())
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<BaseBean<List<ClassifyBean>>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(BaseBean<List<ClassifyBean>> bean) {
                               if (bean.success()) {
                                   data = bean.getResult();
                                   dataset.clear();
                                   for (int i = 0; i < data.size(); i++) {
                                       dataset.add(data.get(i).getName());
                                   }
                                   mNiceSpinner.attachDataSource(dataset);
                                   if (data.size() > 0) {
                                       loadShopData(data.get(0).getId());
                                   }

                               } else {
                                   showToast(bean.getMessage());
                               }

                           }

                           @Override
                           public void onError(Throwable e) {

                           }

                           @Override
                           public void onComplete() {

                           }
                       });
    }

    private void loadShopData(int id) {
        mStateView.showLoading();
        RetrofitManager.getInstance()
                       .getApiService()
                       .getHallShop("token", "9", id)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<BaseBean<List<ShopBean>>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(BaseBean<List<ShopBean>> bean) {
                               mStateView.showContent();
                               if (bean.success()) {
                                   List<ShopBean> result = bean.getResult();
                                   beanList.clear();
                                   beanList.addAll(result);
                                   gridShopAdapter.notifyDataSetChanged();
                               } else {
                                   showToast(bean.getMessage());
                               }

                           }

                           @Override
                           public void onError(Throwable e) {
                               mStateView.showRetry();
                           }

                           @Override
                           public void onComplete() {

                           }
                       });
    }

    @Override
    protected View injectTarget() {
        return mRootLayout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
