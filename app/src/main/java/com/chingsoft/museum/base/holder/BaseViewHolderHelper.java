package com.chingsoft.museum.base.holder;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chingsoft.museum.R;
import com.chingsoft.museum.utils.DownloadImageTask;
import com.chingsoft.museum.utils.ViewUtils;
import com.chingsoft.museum.view.CircleProgress;
import com.chingsoft.museum.view.RoundTextView;


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
 * Created at 17/12/10 上午11:49
 */
public class BaseViewHolderHelper extends BaseViewHolder {

    public BaseViewHolderHelper(View view) {
        super(view);
    }

    public BaseViewHolderHelper setHtmlText(@IdRes int viewId, @StringRes int strId, String value) {
        TextView view = getView(viewId);
        String htmlValue = String.format(view.getContext().getResources().getString(strId), value);
        view.setText(Html.fromHtml(htmlValue));
        return this;
    }

    /**
     * 给TextView添加删除线
     *
     * @param viewId 控件ID
     * @return
     */
    public BaseViewHolderHelper setTextDeleteLine(@IdRes int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    /**
     * 给TextView添加下划线
     *
     * @param viewId 控件ID
     * @return
     */
    public BaseViewHolderHelper setTextUnderLine(@IdRes int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        return this;
    }

    /**
     * Set a view visibility to VISIBLE or GONE or INVISIBLE.
     *
     * @param viewId  The view id.
     * @param visible VISIBLE, GONE, or INVISIBLE.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setVisible(@IdRes int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId   控件ID
     * @param imageUrl 图片URL
     * @return
     */
    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().priority(Priority.HIGH)
                                                     .placeholder(R.drawable.bg_default)
                                                     .error(R.drawable.bg_default)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId          控件ID
     * @param imageUrl        图片URL
     * @param defaultDrawable 默认图片
     * @return
     */
    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 设置圆环数值
     *
     * @param viewId 控件ID
     * @param value 参数值
     * @param syle 圆环显示的弧度
     * @return
     */
    public BaseViewHolderHelper setValue(@IdRes int viewId, float value, float syle) {
        CircleProgress cpTemp = getView(viewId);
        cpTemp.setValue(value, syle);
        return this;
    }

    /**
     * 设置圆环类型名称
     *
     * @param viewId 控件ID
     * @param hint 参数值
     * @return
     */
    public BaseViewHolderHelper setHint(@IdRes int viewId, String hint) {
        CircleProgress cpTemp = getView(viewId);
        cpTemp.setHint(hint);
        return this;
    }

    /**
     * 设置圆环单位
     *
     * @param viewId 控件ID
     * @param hint 参数值
     * @return
     */
    public BaseViewHolderHelper setUnit(@IdRes int viewId, String hint) {
        CircleProgress cpTemp = getView(viewId);
        cpTemp.setUnit(hint);
        return this;
    }

    /**
     * 设置圆环颜色
     *
     * @param viewId 控件ID
     * @param progressColor
     * @return
     */
    public BaseViewHolderHelper setProgressColor(@IdRes int viewId, int progressColor) {
        CircleProgress cpTemp = getView(viewId);
        cpTemp.setProgressColor(progressColor);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId          控件ID
     * @param imageUrl        图片URL
     * @param defaultDrawable 默认图片
     * @param isGif           是否开启gif
     * @return
     */
    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl, boolean isGif,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        if (isGif) {
            Glide.with(imageview.getContext())
                 .asGif()
                 .load(imageUrl)
                 .apply(options)
                 .into(imageview);
        } else {
            Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        }
        return this;
    }

    /**
     * 设置圆角图片
     *
     * @param viewId          this view id
     * @param imageUrl        网络图片地址
     * @param defaultDrawable 默认图片
     * @return
     */
    public BaseViewHolderHelper setCircleImageUrl(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 设置网络头像
     *
     * @param viewId          this view id
     * @param imageUrl        网络图片地址
     * @param defaultDrawable 默认图片
     * @return
     */
    public BaseViewHolderHelper setAvatar(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    /**
     * 加载网络图片作为一个布局的背景
     *
     * @param viewId        布局控件ID
     * @param backgroundUrl 背景图片URL
     * @return
     */
    public BaseViewHolderHelper setViewGroupBackgroundUrl(@IdRes int viewId, String backgroundUrl) {
        ViewGroup imageview = getView(viewId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            new DownloadImageTask(imageview).execute(backgroundUrl);
        }
        return this;
    }

    /**
     * 设置图片资源的颜色
     *
     * @param viewId    控件ID
     * @param colorARGB 要设置的颜色
     * @return
     */
    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, @ColorInt int colorARGB) {
        ImageView view = getView(viewId);
        view.setColorFilter(colorARGB);
        return this;
    }

    /**
     * 设置图片资源的颜色
     *
     * @param viewId 控件ID
     * @param r      红色
     * @param g      绿色
     * @param b      蓝色
     * @return
     */
    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, int r, int g, int b) {
        ImageView view = getView(viewId);
        view.setColorFilter(Color.argb(255, r, g, b));
        return this;
    }

    /**
     * 设置图片资源的颜色
     *
     * @param viewId 控件ID
     * @param a      透明度
     * @param r      红色
     * @param g      绿色
     * @param b      蓝色
     * @return
     */
    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, int a, int r, int g, int b) {
        ImageView view = getView(viewId);
        view.setColorFilter(Color.argb(a, r, g, b));
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     * @return
     */
    public BaseViewHolderHelper setViewHeight(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     * @param screen 间隔尺寸
     * @return
     */
    public BaseViewHolderHelper setViewHeight(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     */
    public BaseViewHolderHelper setViewWidth(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewWidth(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     * @param type   所占屏幕宽度比例
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的尺寸
     *
     * @param viewId The view id.
     * @param width  The view width.
     * @param height The view height.
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, int width, int height) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, width, height);
        return this;
    }

    /**
     * 控件是否可点击
     *
     * @param viewId
     * @param enable 是否可点击
     * @return
     */
    public BaseViewHolderHelper setEnabled(@IdRes int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    /**
     * 圆角TEXTVIEW控件背景颜色
     *
     * @param viewId 控件ID
     * @param color  背景颜色
     * @return
     */
    public BaseViewHolderHelper setRoundViewBgColor(@IdRes int viewId, @ColorRes int color) {
        RoundTextView view = getView(viewId);
        view.getDelegate().setBackgroundColor(ContextCompat.getColor(view.getContext(), color));
        return this;
    }

    /**
     * 圆角TEXTVIEW控件点击背景颜色
     *
     * @param viewId 控件ID
     * @param color  背景颜色
     * @return
     */
    public BaseViewHolderHelper setRoundViewBgPressColor(@IdRes int viewId, @ColorRes int color) {
        RoundTextView view = getView(viewId);
        view.getDelegate()
            .setBackgroundPressColor(ContextCompat.getColor(view.getContext(), color));
        return this;
    }

    public BaseViewHolderHelper setStrokeColor(@IdRes int viewId, @ColorRes int color) {
        RoundTextView view = getView(viewId);
        view.getDelegate().setStrokeColor(ContextCompat.getColor(view.getContext(), color));
        return this;
    }

    public BaseViewHolderHelper setStrokePressColor(@IdRes int viewId, @ColorRes int color) {
        RoundTextView view = getView(viewId);
        view.getDelegate().setStrokePressColor(ContextCompat.getColor(view.getContext(), color));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsLeft(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableRes), null, null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsTop(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes), null,
                                                     null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsRight(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes), null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsBottom(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId,
            @DrawableRes int drawableResLeft, @DrawableRes int drawableResTop,
            @DrawableRes int drawableResRight, @DrawableRes int drawableResBottom) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableResLeft),
                ContextCompat.getDrawable(view.getContext(), drawableResTop),
                ContextCompat.getDrawable(view.getContext(), drawableResRight),
                ContextCompat.getDrawable(view.getContext(), drawableResBottom));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesLeft(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableRes), null,
                                  null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesTop(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesRight(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, null,
                                  ContextCompat.getDrawable(view.getContext(), drawableRes), null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesBottom(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, null, null,
                                  ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawables(@IdRes int viewId,
            @DrawableRes int drawableResLeft, @DrawableRes int drawableResTop,
            @DrawableRes int drawableResRight, @DrawableRes int drawableResBottom) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableResLeft),
                                  ContextCompat.getDrawable(view.getContext(), drawableResTop),
                                  ContextCompat.getDrawable(view.getContext(), drawableResRight),
                                  ContextCompat.getDrawable(view.getContext(), drawableResBottom));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawables(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setLayoutManager(@IdRes int viewId, LinearLayoutManager manager) {
        RecyclerView recyclerview = getView(viewId);
        recyclerview.setLayoutManager(manager);
        return this;
    }

    public BaseViewHolderHelper setLayoutManager(@IdRes int viewId, GridLayoutManager manager) {
        RecyclerView recyclerview = getView(viewId);
        recyclerview.setLayoutManager(manager);
        return this;
    }

    /**
     * 控件旋转
     *
     * @param viewId 控件ID
     * @param value  旋转角度
     * @return
     */
    public BaseViewHolderHelper rotation(@IdRes int viewId, float value) {
        ImageView view = getView(viewId);
        ViewCompat.animate(view).rotation(value).start();
        return this;
    }

    /**
     * 获取控件隐藏显示状态
     *
     * @return
     */
    public Boolean getVisible(@IdRes int viewId) {
        View view       = getView(viewId);
        int  visibility = view.getVisibility();
        if (visibility == 0) {
            return true;
        } else if (visibility == 8) {
            return false;
        } else {
            return false;
        }
    }
}
