package com.chingsoft.museum.base.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chingsoft.museum.view.LoadingView;
import com.chingsoft.museum.view.StateView;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * library
 * Package com.chingtech.library.base.fragment
 * Description:
 * Created by 师春雷
 * Created at 2016/9/9 下午4:03
 */
@SuppressWarnings("ConstantConditions")
public abstract class BaseFragment extends Fragment implements LifecycleProvider<FragmentEvent> {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Activity mActivity;

    protected ImmersionBar mImmersionBar;

    protected StateView mStateView;

    Unbinder unbinder;

    private LoadingView progress;

    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);

        setHasOptionsMenu(true);// 加上这句话，menu才会显示出来

        //解决fragment重叠问题
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                fragmentTransaction.hide(this);
            } else {
                fragmentTransaction.show(this);
            }
            fragmentTransaction.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initView();
        loadData();
    }

    /**
     * 一些初始化准备
     */
    public void init(View view) {
        mStateView = StateView.inject(injectTarget());
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar         Toolbar
     * @param textView        标题控件
     * @param homeAsUpEnabled 是否显示左上角图标
     * @param title           标题
     * @param isDark          是否深色主题
     */
    public void initToolBar(Toolbar toolbar, TextView textView, boolean homeAsUpEnabled,
            String title, boolean isDark) {
        if (isImmersionBarEnabled()) {
            initImmersionBar(toolbar, isDark);
        }

        toolbar.setTitle("");
        textView.setText(title);

        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                                           .setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar         Toolbar
     * @param textView        标题控件
     * @param homeAsUpEnabled 是否显示左上角图标
     * @param title           标题
     * @param resId           NavigationIcon图标
     * @param isDark          是否深色主题
     */
    public void initToolBar(Toolbar toolbar, TextView textView, boolean homeAsUpEnabled,
            String title, boolean isDark, @DrawableRes int resId) {
        if (isImmersionBarEnabled()) {
            initImmersionBar(toolbar, isDark);
        }

        toolbar.setTitle("");
        textView.setText(title);

        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(resId);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                                           .setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar Toolbar
     * @param isDark  是否深色主题
     */
    public void initToolBar(Toolbar toolbar, boolean isDark) {
        if (isImmersionBarEnabled()) {
            initImmersionBar(toolbar, isDark);
        }

        toolbar.setTitle("");

        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar Toolbar
     * @param isDark  是否深色主题
     * @param resId   NavigationIcon图标
     */
    public void initToolBar(Toolbar toolbar, boolean isDark, @DrawableRes int resId) {
        if (isImmersionBarEnabled()) {
            initImmersionBar(toolbar, isDark);
        }

        toolbar.setTitle("");

        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(resId);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.init();
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar(Toolbar toolbar, boolean isDark) {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(toolbar).keyboardEnable(true);
        if (isDark) { // 状态栏文字为黑色
            mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        } else { // 状态栏文字为白色
            mImmersionBar.init();
        }
    }

    /**
     * 设置布局资源
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view操作
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void loadData();

    protected abstract View injectTarget();

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * 接收前一个页面传递的String值
     *
     * @param key
     * @return
     */
    protected String getStringExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getStringExtra(key);
    }

    /**
     * 接收前一个页面传递的Integer值
     *
     * @param key
     * @return
     */
    protected Integer getIntExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getIntExtra(key, 0);
    }

    /**
     * 接收前一个页面传递的Long值
     *
     * @param key
     * @return
     */
    protected Long getLongExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getLongExtra(key, 0L);
    }

    /**
     * 接收前一个页面传递的Boolean值
     *
     * @param key
     * @return
     */
    protected Boolean getBooleanExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getBooleanExtra(key, false);
    }

    public Object getSerializableExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getSerializableExtra(key);
    }

    @SuppressWarnings("unchecked")
    public List<Object> getSerializable(String key) {
        Intent receive = getActivity().getIntent();
        return (ArrayList<Object>) receive.getSerializableExtra(key);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        showActivity(pClass, null, null, null, false, -1);
    }

    /**
     * @param pClass
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1);
    }

    /**
     * @param pClass
     * @param tag
     */
    protected void openActivity(Class<?> pClass, int tag) {
        showActivity(pClass, null, null, null, false, tag);
    }

    /**
     * @param pClass
     * @param key
     * @param value
     * @param tag
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, int tag) {
        showActivity(pClass, null, key, value, false, tag);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     * @param key
     * @param value
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value) {
        showActivity(pClass, null, key, value, false, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, boolean isfinish) {
        showActivity(pClass, pBundle, null, null, isfinish, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param tag
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, int tag) {
        showActivity(pClass, pBundle, null, null, false, tag);
    }

    /**
     * @param pClass
     * @param key
     * @param value
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
        showActivity(pClass, null, key, value, isfinish, -1);
    }

    /**
     * @param pClass
     * @param bundle
     * @param key
     * @param value
     * @param isfinish
     * @param tag
     */
    protected void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
            boolean isfinish, int tag) {

        Intent intent = new Intent(mActivity, pClass);

        if (null != key) {
            intent.putExtra(key, value);
        }

        if (null != bundle) {
            intent.putExtras(bundle);
        }

        if (tag == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, tag);
        }

        if (isfinish) {
            getActivity().finish();
        }
    }

    protected void openActivityScaleUp(View view, Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(mActivity, pClass);
        intent.putExtras(bundle);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2,
                                                            view.getHeight() / 2, 1000, 1000);
        ActivityCompat.startActivity(mActivity, intent, compat.toBundle());
    }

    private static Toast mToast;
    private static long time = 0;

    /**
     * 显示Toast
     * 点一次显示内容并且为短时间显示，若在两秒内点击两次，则更改为长时间显示
     * 不会重复创建Toast
     *
     * @param content
     */
    @SuppressLint("ShowToast")
    public void showToast(String content) {
        long temp = System.currentTimeMillis();
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, content, Toast.LENGTH_SHORT);
        }
        mToast.setText(content);
        if (temp - time < 1000) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        time = temp;
        mToast.show();
    }

    /**
     * 显示Toast
     * 点一次显示内容并且为短时间显示，若在两秒内点击两次，则更改为长时间显示
     * 不会重复创建Toast
     *
     * @param resId
     */
    @SuppressLint("ShowToast")
    public void showToast(@StringRes int resId) {
        long temp = System.currentTimeMillis();
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, getString(resId), Toast.LENGTH_SHORT);
        }
        mToast.setText(getString(resId));
        if (temp - time < 1000) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        time = temp;
        mToast.show();
    }

    public void showLoading() {
        if (null == progress) {
            progress = new LoadingView(mActivity);
            progress.setTitleTxt("加载中...");
        }
        progress.show();
    }

    public void dismissLoading() {
        if (null != progress) {
            progress.dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
        unbinder.unbind();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
