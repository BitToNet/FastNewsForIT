package cn.bittonet.news.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import cn.bittonet.news.R;
import cn.bittonet.news.base.holder.BaseViewHolderHelper;
import cn.bittonet.news.bean.DataListBean;
import cn.bittonet.news.bean.RepoAreaBean;
import cn.bittonet.news.bean.ShopBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;

/**
 * Created by Felone on 2019/2/22 0022 上午 10:30
 * description：
 * 50257836@qq.com
 */
public class GridShopAdapter extends BaseQuickAdapter<ShopBean, BaseViewHolderHelper> {
    private DataListBean dataListBean;
    private RepoAreaBean repoArea;

    public GridShopAdapter(@Nullable List<ShopBean> data) {
        super(R.layout.item_grid_shop, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, ShopBean item) {
        repoArea = item.getRepoArea();
        if (item.getDataList().size() > 0) {
            dataListBean = item.getDataList().get(0);
        } else {
            return;
        }

        helper.setImageUrl(R.id.iv, item.getRepoArea().getImageUrl())
              .setText(R.id.tv, item.getRepoArea().getName());
        if (dataListBean.getDevice().getIsConnect() == 0) {
            helper.setText(R.id.tv_state, "未连接")
                  .setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.colorRed));
        } else {
            helper.setText(R.id.tv_state, "正常")
                  .setTextColor(R.id.tv_state,
                                ContextCompat.getColor(mContext, R.color.text_color_green));
        }

        if (dataListBean.getHumidity() != null && dataListBean.getTemperature() != null) {
            helper.setValue(R.id.cp_temp, dataListBean.getTemperature().getValue(), 30)
                  .setValue(R.id.cp_humi, dataListBean.getHumidity().getValue(), 30);
            setTemp(helper);
            sethumi(helper);
        } else if (dataListBean.getHumidity() == null && dataListBean.getTemperature() == null) {
        } else {
            if (dataListBean.getTemperature() == null) {
                helper.setValue(R.id.cp_humi, dataListBean.getHumidity().getValue(), 30);
                sethumi(helper);
            }
            if (dataListBean.getHumidity() == null) {
                helper.setValue(R.id.cp_temp, dataListBean.getTemperature().getValue(), 30);
                setTemp(helper);
            }
        }
    }

    private void sethumi(BaseViewHolderHelper helper) {
        //湿度在范围内，绿色
        //湿度不在范围内，黄色；若设备已连接，状态字体和颜色也改为污染和黄色
        if (dataListBean.getHumidity().getValue() > repoArea.getSaveStandard().getHumidityMax()
                || dataListBean.getHumidity().getValue() < repoArea.getSaveStandard()
                                                                   .getHumidityMin()) {
            helper.setProgressColor(R.id.cp_humi,
                                    ContextCompat.getColor(mContext, R.color.text_color_yellow))
                  .setValue(R.id.cp_humi, dataListBean.getTemperature().getValue(), 60);
            if (dataListBean.getDevice().getIsConnect() != 0) {
                helper.setText(R.id.tv_state, "污染")
                      .setTextColor(R.id.tv_state,
                                    ContextCompat.getColor(mContext, R.color.text_color_yellow));
            }
        }
    }

    private void setTemp(BaseViewHolderHelper helper) {
        //温度在范围内，绿色
        //温度不在范围内，黄色；若设备已连接，状态字体和颜色也改为污染和黄色
        if (dataListBean.getTemperature().getValue() > repoArea.getSaveStandard()
                                                               .getTemperatureMax()
                || dataListBean.getTemperature().getValue() < repoArea.getSaveStandard()
                                                                      .getTemperatureMin()) {
            helper.setProgressColor(R.id.cp_temp,
                                    ContextCompat.getColor(mContext, R.color.text_color_yellow))
                  .setValue(R.id.cp_temp, dataListBean.getTemperature().getValue(), 60);
            if (dataListBean.getDevice().getIsConnect() != 0) {
                helper.setText(R.id.tv_state, "污染")
                      .setTextColor(R.id.tv_state,
                                    ContextCompat.getColor(mContext, R.color.text_color_yellow));
            }
        }
    }
}
