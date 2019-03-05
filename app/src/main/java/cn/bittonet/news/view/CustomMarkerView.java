package cn.bittonet.news.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import cn.bittonet.news.R;
import cn.bittonet.news.bean.DataBean;
import cn.bittonet.news.utils.TimeUtils;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * YueHealth
 * Package com.chingtech.health.elder.view
 * Description:
 * Created by 师春雷
 * Created at 18/4/27 下午5:20
 */
@SuppressLint({"SetTextI18n", "ViewConstructor"})
public class CustomMarkerView extends MarkerView {

    private TextView tvContent;

    private String type;

    public CustomMarkerView(Context context, int layoutResource, String type) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
        this.type = type;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        DataBean bpDayReportBean = (DataBean) e.getData();
        String   title;
        tvContent.setText(
                TimeUtils.formatDateTime(bpDayReportBean.getTimestamp(), TimeUtils.DATETIME_FORMAT)
                        + "\n"
                        + type
                        +":"+
                        bpDayReportBean.getValue());
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}

