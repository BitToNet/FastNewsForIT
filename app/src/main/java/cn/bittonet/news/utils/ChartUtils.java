package cn.bittonet.news.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v4.content.ContextCompat;
import cn.bittonet.news.R;
import cn.bittonet.news.bean.DataBean;
import cn.bittonet.news.view.CustomMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
 * Package com.chingtech.health.elder.utils
 * Description:
 * Created by 师春雷
 * Created at 17/9/23 上午9:14
 */
public class ChartUtils {

    public static final int WEEK  = 0x0001;
    public static final int MONTH = 0x0011;

    private static final int DURATION_MILLIS = 1400;

    /**
     * 初始化血压图表
     *
     * @param context    context
     * @param chart      图表
     * @param max        Y轴方向最大值
     * @param labelCount y轴方向刻度数
     * @param yLabel     Y轴方向数据单位
     * @param list       数据
     * @param up         范围最大值
     * @param low        范围最小值
     * @param type       数据类型
     */
    public static void initBpChart(Context context, LineChart chart, float max, float min, int labelCount,
            String yLabel, ArrayList<Entry> list, int up, int low, String type) {
        chart.setData(null);
        chart.zoom(0, 0, 0, 0);
        // 设置描述文本不显示
        chart.getDescription().setEnabled(false);
        chart.setNoDataText("暂无数据");
        // enable touch gestures
        chart.setTouchEnabled(true);
        // 设置是否可以拖拽
        chart.setDragEnabled(true);
        // 设置是否可以缩放
        chart.setScaleEnabled(false);
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);

        // set an alternative background color
        // chart.setBackgroundColor(Color.GRAY);


        // create a custom MarkerView (extend MarkerView) and specify the layout to use for it
        CustomMarkerView mv = new CustomMarkerView(context, R.layout.custom_marker_view, type);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        LimitLine ll1 = new LimitLine(up, "");
        // 设置线的宽度
        ll1.setLineWidth(1f);
        // 设置虚线样式
        ll1.enableDashedLine(10f, 10f, 0f);
        // 设置文字的在线的相对位置
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        // 设置文字大小
        ll1.setTextSize(10f);
        // 设置线的颜色
        ll1.setLineColor(Color.RED);

        LimitLine ll2 = new LimitLine(low, "");
        ll2.setLineWidth(1f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setLineColor(Color.BLUE);

        //修改y轴属性
        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setAxisMinimum(min);
        axisLeft.setAxisMaximum(max);
        axisLeft.setDrawGridLines(false);
//        axisLeft.setLabelCount(labelCount, false);
        axisLeft.setAxisLineColor(ContextCompat.getColor(context, R.color.black_text));
        axisLeft.setTextColor(ContextCompat.getColor(context, R.color.black_text));
        axisLeft.setAxisLineWidth(1);
        axisLeft.setSpaceTop(1.3f);
        axisLeft.setSpaceBottom(1.2f);
        axisLeft.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        axisLeft.addLimitLine(ll1);
        axisLeft.addLimitLine(ll2);
        axisLeft.setValueFormatter((value, axis) -> {
            DecimalFormat df = new DecimalFormat("#.0");
            String  label    = String.valueOf((df.format(value)));
            float[] mEntries = axis.mEntries;
            if (value == mEntries[mEntries.length - 1]) {
                label = yLabel;
            }
            return label;
        });

        // 修改x轴属性
        XAxis xAxis = chart.getXAxis();
        // 设置x轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 是否画X轴方向上的网格线
        xAxis.setDrawGridLines(false);
        // 设置轴颜色
        xAxis.setAxisLineColor(ContextCompat.getColor(context, R.color.chart_line));
        // 设置轴宽度
        xAxis.setAxisLineWidth(1);
        // 设置轴字体颜色
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.black_text));
        // 设置轴字体大小
        xAxis.setTextSize(8);

        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelCount(3);
        xAxis.setValueFormatter((value, axis) -> TimeUtils.formatDateTime(
                ((DataBean) list.get((int) value).getData()).getTimestamp(),
                TimeUtils.DATETIME_FORMAT));

        //不绘制右边y轴
        chart.getAxisRight().setEnabled(false);

        //不显示标签
        chart.getLegend().setEnabled(false);

        setBpChartData(context, chart, list, xAxis);

        chart.setExtraOffsets(0, 0, 30, 15);
        //添加动画
        chart.animateX(DURATION_MILLIS, Easing.EasingOption.EaseInOutQuart);
    }

    /**
     * 设置血压图表数据
     *
     * @param context context
     * @param chart   图标
     * @param values  数据
     * @param xAxis   X轴
     */
    private static void setBpChartData(Context context, LineChart chart, ArrayList<Entry> values,
            XAxis xAxis) {
        LineDataSet set;
        if (values.size() > 0) {
            set = new LineDataSet(values, "");
            // set.enableDashedLine(10f, 5f, 0f);
            // set.enableDashedHighlightLine(10f, 5f, 0f);
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            set.setColor(ContextCompat.getColor(context, R.color.chart_text));
            set.setLineWidth(1f);
            set.setFormLineWidth(1f);
            set.setFormLineDashEffect(new DashPathEffect(new float[] {10f, 5f}, 0f));
            set.setFormSize(15.f);
            set.setDrawCircleHole(false);
            set.setHighLightColor(ContextCompat.getColor(context, R.color.chart_text));
            // 是否绘制文字
            set.setDrawValues(false);
            // 是否绘制圆点
            set.setDrawCircles(false);
            // 原点半径
            set.setCircleRadius(0.1f);
            set.setCircleColor(Color.RED);
            set.setValueFormatter(
                    (value, entry, dataSetIndex, viewPortHandler) -> (int) value + "");
            // 圆滑模式
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            LineData data;
            data = new LineData(set);

            chart.setData(data);
        } else {
            chart.setData(null);
        }
//        xAxis.setAxisMinimum(chart.getData().getXMin() - 0.15f);
//        xAxis.setAxisMaximum(chart.getData().getXMax() + 0.15f);

        // 设置一页最大显示个数为20，超出部分就滑动
//        float ratio = (float) (values.size() / 40);
//        LogUtils.w("ChartUtils", "----------------------------------" + ratio);
        // 显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
//        chart.zoom(ratio, 1f, 0, 0);
        chart.invalidate();
        chart.notifyDataSetChanged();
    }

}
