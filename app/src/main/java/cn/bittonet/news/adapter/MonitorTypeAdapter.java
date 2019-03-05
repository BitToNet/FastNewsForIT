package cn.bittonet.news.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import cn.bittonet.news.R;
import cn.bittonet.news.base.holder.BaseViewHolderHelper;
import cn.bittonet.news.bean.RepoAreaBean;
import cn.bittonet.news.bean.ValueBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;

/**
 * Created by Felone on 2019/2/28 0028 上午 10:39
 * description：
 * 50257836@qq.com
 */
public class MonitorTypeAdapter extends BaseQuickAdapter<ValueBean, BaseViewHolderHelper> {
    private final RepoAreaBean area;

    public MonitorTypeAdapter(@Nullable List<ValueBean> data, RepoAreaBean repoArea) {
        super(R.layout.item_monitor_type, data);
        area = repoArea;
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, ValueBean item) {
        switch (item.getObjectType()) {
            case "HUMIDITY":
                helper.setHint(R.id.cp, item.getName())
                .setUnit(R.id.cp, "%");
                if (item.getValue() > area.getSaveStandard().getHumidityMax()
                        || item.getValue() < area.getSaveStandard().getHumidityMin()) {
                    helper.setValue(R.id.cp, item.getValue(),60)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_yellow));
                }else {
                    helper.setValue(R.id.cp, item.getValue(),30)
                    .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_green));
                }

                break;
            case "TEMPERATURE":
                helper.setHint(R.id.cp, item.getName())
                      .setUnit(R.id.cp, "℃");
                if (item.getValue() > area.getSaveStandard().getTemperatureMax()
                        || item.getValue() < area.getSaveStandard().getTemperatureMin()) {
                    helper.setValue(R.id.cp, item.getValue(),60)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_yellow));
                }else {
                    helper.setValue(R.id.cp, item.getValue(),30)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_green));
                }
                break;
            case "UV":
                helper.setHint(R.id.cp, item.getName())
                      .setUnit(R.id.cp, "uW/lm");
                if (item.getValue() > area.getSaveStandard().getUv()) {
                    helper.setValue(R.id.cp, item.getValue(),60)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_yellow));
                }else {
                    helper.setValue(R.id.cp, item.getValue(),30)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_green));
                }
                break;
            case "LIGHTING":
                helper.setHint(R.id.cp, item.getName())
                      .setUnit(R.id.cp, "lux");
                if (item.getValue() > area.getSaveStandard().getLighting()) {
                    helper.setValue(R.id.cp, item.getValue(),60)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_yellow));
                }else {
                    helper.setValue(R.id.cp, item.getValue(),30)
                          .setProgressColor(R.id.cp, ContextCompat.getColor(mContext, R.color.text_color_green));
                }
                break;
            default:
                break;
        }
    }
}
