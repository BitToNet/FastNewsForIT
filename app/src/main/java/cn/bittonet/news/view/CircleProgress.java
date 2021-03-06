package cn.bittonet.news.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import cn.bittonet.news.R;
import cn.bittonet.news.utils.ScreenUtils;

import static cn.bittonet.news.utils.ScreenUtils.dp2px;
import static cn.bittonet.news.utils.ScreenUtils.measureTextHeight;
import static cn.bittonet.news.utils.ScreenUtils.sp2px;
import static cn.bittonet.news.utils.StringUtils.getPrecisionFormat;


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
 * Package com.chingtech.library.widget
 * Description:
 * Created by 师春雷
 * Created at 17/11/28 下午5:47
 */
public class CircleProgress extends View {

    private Context mContext;

    public static final boolean ANTI_ALIAS = true;

    public static final int DEFAULT_SIZE        = 150;
    public static final int DEFAULT_START_ANGLE = 270;
    public static final int DEFAULT_SWEEP_ANGLE = 360;

    public static final int DEFAULT_ANIM_TIME = 1000;

    public static final int DEFAULT_MAX_VALUE = 100;
    public static final int DEFAULT_VALUE     = 50;

    public static final int DEFAULT_HINT_SIZE  = 15;
    public static final int DEFAULT_UNIT_SIZE  = 15;
    public static final int DEFAULT_VALUE_SIZE = 18;

    public static final int DEFAULT_ARC_WIDTH = 5;

    //默认大小
    private int          mDefaultSize;
    //是否开启抗锯齿
    private boolean      antiAlias;
    private int          progressColor;
    //绘制提示
    private TextPaint    mHintPaint;
    private CharSequence mHint;
    private int          mHintColor;
    private float        mHintSize;
    private float        mHintOffset;

    //绘制单位
    private TextPaint    mUnitPaint;
    private CharSequence mUnit;
    private int          mUnitColor;
    private float        mUnitSize;
    private float        mUnitOffset;

    //绘制数值
    private TextPaint mValuePaint;
    private float     mValue;
    private float     mMaxValue;
    private float     mValueOffset;
    private int       mPrecision;
    private String    mPrecisionFormat;
    private int       mValueColor;
    private float     mValueSize;

    //绘制圆弧
    private Paint mArcPaint;
    private float mArcWidth;
    private float mStartAngle, mSweepAngle;
    private RectF         mRectF;
    //当前进度，[0.0f,1.0f]
    private float         mPercent;
    //动画时间
    private long          mAnimTime;
    //属性动画
    private ValueAnimator mAnimator;

    //绘制背景圆弧
    private Paint mBgArcPaint;
    private int   mBgArcColor;
    private float mBgArcWidth;

    //圆心坐标，半径
    private Point mCenterPoint;
    private float mRadius;
    private float mTextOffsetPercentInRadius;

    //渐变的颜色是360度，如果只显示270，那么则会缺失部分颜色
    private SweepGradient mSweepGradient;
    private int[] mGradientColors = {ContextCompat.getColor(getContext(), R.color.arc1),
            ContextCompat.getColor(getContext(), R.color.arc2),
            ContextCompat.getColor(getContext(), R.color.arc3),
            ContextCompat.getColor(getContext(), R.color.arc4),
            ContextCompat.getColor(getContext(), R.color.arc5),
            ContextCompat.getColor(getContext(), R.color.arc1)};

    private static final int SINGLE_COLOR = 0;
    private static final int MULTI_COLOR  = 1;

    private int arcColorType;

    private boolean showValues;

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mDefaultSize = (int) sp2px(DEFAULT_SIZE);
        mAnimator = new ValueAnimator();
        mRectF = new RectF();
        mCenterPoint = new Point();
        initAttrs(attrs);
        initPaint();
        setValue(mValue,0);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgress);

        antiAlias = typedArray.getBoolean(R.styleable.CircleProgress_cp_anti_alias, ANTI_ALIAS);

        mHint = typedArray.getString(R.styleable.CircleProgress_cp_hint);
        mHintColor = typedArray.getColor(R.styleable.CircleProgress_cp_hint_color, Color.BLACK);
        mHintSize = typedArray.getDimension(R.styleable.CircleProgress_cp_hint_size,
                                            sp2px(DEFAULT_HINT_SIZE));

        mValue = typedArray.getFloat(R.styleable.CircleProgress_cp_value, DEFAULT_VALUE);
        mMaxValue = typedArray.getFloat(R.styleable.CircleProgress_cp_max_value, DEFAULT_MAX_VALUE);
        //内容数值精度格式
        mPrecision = typedArray.getInt(R.styleable.CircleProgress_cp_precision, 0);
        mPrecisionFormat = getPrecisionFormat(mPrecision);
        mValueColor = typedArray.getColor(R.styleable.CircleProgress_cp_value_color, Color.BLACK);
        mValueSize = typedArray.getDimension(R.styleable.CircleProgress_cp_value_size,
                                             sp2px(DEFAULT_VALUE_SIZE));

        mUnit = typedArray.getString(R.styleable.CircleProgress_cp_unit);
        mUnitColor = typedArray.getColor(R.styleable.CircleProgress_cp_unit_color, Color.BLACK);
        mUnitSize = typedArray.getDimension(R.styleable.CircleProgress_cp_unit_size,
                                            sp2px(DEFAULT_UNIT_SIZE));

        mArcWidth = typedArray.getDimension(R.styleable.CircleProgress_arc_width,
                                            dp2px(DEFAULT_ARC_WIDTH));
        mStartAngle = typedArray.getFloat(R.styleable.CircleProgress_cp_start_angle,
                                          DEFAULT_START_ANGLE);
        mSweepAngle = typedArray.getFloat(R.styleable.CircleProgress_cp_sweep_angle,
                                          DEFAULT_SWEEP_ANGLE);

        mBgArcColor = typedArray.getColor(R.styleable.CircleProgress_bg_arc_color, Color.WHITE);
        mBgArcWidth = typedArray.getDimension(R.styleable.CircleProgress_bg_arc_width,
                                              dp2px(DEFAULT_ARC_WIDTH));
        mTextOffsetPercentInRadius = typedArray.getFloat(
                R.styleable.CircleProgress_textOffsetPercentInRadius, 0.33f);

        mAnimTime = typedArray.getInt(R.styleable.CircleProgress_cp_anim_time, DEFAULT_ANIM_TIME);

        progressColor = typedArray.getColor(R.styleable.CircleProgress_arc_color, Color.BLUE);

        arcColorType = typedArray.getInt(R.styleable.CircleProgress_arc_color_type, SINGLE_COLOR);

        showValues = typedArray.getBoolean(R.styleable.CircleProgress_arc_show_value, true);

        typedArray.recycle();
    }

    private void initPaint() {
        mHintPaint = new TextPaint();
        // 设置抗锯齿,会消耗较大资源，绘制图形速度会变慢。
        mHintPaint.setAntiAlias(antiAlias);
        // 设置绘制文字大小
        mHintPaint.setTextSize(mHintSize);
        // 从中间向两边绘制，不需要再次计算文字
        mHintPaint.setTextAlign(Paint.Align.CENTER);

        mValuePaint = new TextPaint();
        mValuePaint.setAntiAlias(antiAlias);
        mValuePaint.setTextSize(mValueSize);
        mValuePaint.setColor(mValueColor);
        // 设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等
        mValuePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        mUnitPaint = new TextPaint();
        mUnitPaint.setAntiAlias(antiAlias);
        mUnitPaint.setTextSize(mUnitSize);
        mUnitPaint.setTextAlign(Paint.Align.CENTER);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(antiAlias);
        // 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔粗细
        mArcPaint.setStrokeWidth(mArcWidth);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(antiAlias);
        mBgArcPaint.setColor(mBgArcColor);
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(ScreenUtils.measure(widthMeasureSpec, mDefaultSize),
                             ScreenUtils.measure(heightMeasureSpec, mDefaultSize));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //求圆弧和背景圆弧的最大宽度
        float maxArcWidth = Math.max(mArcWidth, mBgArcWidth);
        //求最小值作为实际值
        int minSize = Math.min(w - getPaddingLeft() - getPaddingRight() - 2 * (int) maxArcWidth,
                               h - getPaddingTop() - getPaddingBottom() - 2 * (int) maxArcWidth);
        //减去圆弧的宽度，否则会造成部分圆弧绘制在外围
        mRadius = minSize / 2;
        //获取圆的相关参数
        mCenterPoint.x = w / 2;
        mCenterPoint.y = h / 2;
        //绘制圆弧的边界
        mRectF.left = mCenterPoint.x - mRadius - maxArcWidth / 2;
        mRectF.top = mCenterPoint.y - mRadius - maxArcWidth / 2;
        mRectF.right = mCenterPoint.x + mRadius + maxArcWidth / 2;
        mRectF.bottom = mCenterPoint.y + mRadius + maxArcWidth / 2;
        //计算文字绘制时的 baseline
        //由于文字的baseline、descent、ascent等属性只与textSize和typeface有关，所以此时可以直接计算
        //若value、hint、unit由同一个画笔绘制或者需要动态设置文字的大小，则需要在每次更新后再次计算
        mValueOffset = mCenterPoint.y + getBaselineOffsetFromY(mValuePaint);
        mHintOffset = mCenterPoint.y - mRadius * mTextOffsetPercentInRadius
                + getBaselineOffsetFromY(mHintPaint);
        mUnitOffset = mCenterPoint.y
                + mRadius * mTextOffsetPercentInRadius
                + getBaselineOffsetFromY(mUnitPaint);
    }

    private float getBaselineOffsetFromY(Paint paint) {
        return measureTextHeight(paint) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawArc(canvas);
    }

    /**
     * 绘制内容文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        // 计算文字宽度，由于Paint已设置为居中绘制，故此处不需要重新计算
        // float textWidth = mValuePaint.measureText(mValue.toString());
        // float x = mCenterPoint.x - textWidth / 2;
        if (showValues) {
            if(mValue==-1){
                canvas.drawText("--", mCenterPoint.x, mValueOffset,
                                mValuePaint);
            }else {
                canvas.drawText(String.format(mPrecisionFormat, mValue), mCenterPoint.x, mValueOffset,
                                mValuePaint);
            }

        } else {
            mHintOffset = mValueOffset;
        }

        if (mHint != null) {
            // 设置画笔颜色
            mHintPaint.setColor(mHintColor);
            canvas.drawText(mHint.toString(), mCenterPoint.x, mHintOffset, mHintPaint);
        }

        if (mUnit != null) {
            mUnitPaint.setColor(mUnitColor);
            canvas.drawText(mUnit.toString(), mCenterPoint.x, mUnitOffset, mUnitPaint);
        }
    }

    private void drawArc(Canvas canvas) {
        // 绘制背景圆弧
        // 从进度圆弧结束的地方开始重新绘制，优化性能
        canvas.save();
        float currentAngle = mSweepAngle * mPercent;
        canvas.rotate(mStartAngle, mCenterPoint.x, mCenterPoint.y);
        canvas.drawArc(mRectF, 0, mSweepAngle, false, mBgArcPaint);
        if (arcColorType == MULTI_COLOR) {
            // 设置渐变
            mSweepGradient = new SweepGradient(mCenterPoint.x, mCenterPoint.y, mGradientColors,
                                               null);
            mArcPaint.setShader(mSweepGradient);
        } else if (arcColorType == SINGLE_COLOR) {
            mArcPaint.setColor(progressColor);
        }
        // 第一个参数 oval 为 RectF 类型，即圆弧显示区域
        // startAngle 和 sweepAngle  均为 float 类型，分别表示圆弧起始角度和圆弧度数
        // 3点钟方向为0度，顺时针递增
        // 如果 startAngle < 0 或者 > 360,则相当于 startAngle % 360
        // useCenter:如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形
        canvas.drawArc(mRectF, 0, currentAngle, false, mArcPaint);
        canvas.restore();
    }

    public void setHint(CharSequence hint) {
        this.mHint = hint;
        invalidate();
    }

    public void setUnit(CharSequence unit) {
        this.mUnit = unit;
        invalidate();
    }

    public void setHintColor(int hintColor) {
        this.mHintColor = hintColor;
        invalidate();
    }

    /**
     * 设置当前值
     *
     * @param value
     */
    public void setValue(float value, float syle) {
        if (value > mMaxValue) {
            value = mMaxValue;
        }
        mValue = value;
        float start = mPercent;
        float end   = syle / mMaxValue;
        startAnimator(start, end, mAnimTime);
    }

    private void startAnimator(float start, float end, long animTime) {
        mAnimator = ValueAnimator.ofFloat(start, end);
        mAnimator.setDuration(animTime);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.start();
    }

    /**
     * 设置最大值
     *
     * @param maxValue
     */
    public void setMaxValue(float maxValue) {
        this.mMaxValue = maxValue;
        invalidate();
    }

    public void setPrecision(int precision) {
        this.mPrecision = precision;
        mPrecisionFormat = getPrecisionFormat(precision);
        invalidate();
    }

    /**
     * 重置
     */
    public void reset() {
        startAnimator(mPercent, 0.0f, mAnimTime);
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        invalidate();
    }
}
