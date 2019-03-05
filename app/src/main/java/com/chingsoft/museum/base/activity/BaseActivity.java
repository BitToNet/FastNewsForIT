package com.chingsoft.museum.base.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chingsoft.museum.apps.App;
import com.chingsoft.museum.utils.TimeUtils;
import com.chingsoft.museum.view.StateView;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import java.io.Serializable;

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
 * Package com.chingtech.library.base.activity
 * Description: Activity基类
 * Created by 师春雷
 * Created at 2016/9/23 上午11:29
 */
@SuppressWarnings("ConstantConditions")
public abstract class BaseActivity extends AppCompatActivity
        implements LifecycleProvider<ActivityEvent> {

    protected Context context;

    protected Activity activity;

    protected StateView mStateView;

    private long exitTime = 0L;
    private Unbinder unbinder;

    private   InputMethodManager imm;
    protected ImmersionBar       mImmersionBar;

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法

        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        lifecycleSubject.onNext(ActivityEvent.CREATE);

        App.activities.add(this);

        setContentView(getLayoutId());

        init();
        initView();
        loadData();
    }


    /**
     * 一些初始化准备
     */
    public void init() {
        unbinder = ButterKnife.bind(this);
        context = this;
        activity = this;
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        mStateView = StateView.inject(injectTarget());
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true)//解决软键盘与底部输入框冲突问题，默认为false
                     .init();
    }

    protected void initImmersionBar(Toolbar toolbar, boolean isDark) {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(toolbar).keyboardEnable(true);//解决软键盘与底部输入框冲突问题，默认为false
        if (isDark) {
            mImmersionBar.statusBarDarkFont(true, 0.2f)// 解决白色状态栏问题
                         .init();
        } else {
            mImmersionBar.init();
        }
    }

    protected void initImmersionBar(View statusBarView, boolean isDark) {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarView(statusBarView).keyboardEnable(true);//解决软键盘与底部输入框冲突问题，默认为false
        if (isDark) {
            mImmersionBar.statusBarDarkFont(true, 0.2f)// 解决白色状态栏问题
                         .init();
        } else {
            mImmersionBar.init();
        }
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {

        toolbar.setTitle(title);
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setContentInsetEndWithActions(0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param tvTitle
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, TextView tvTitle, boolean homeAsUpEnabled,
            String title) {

        toolbar.setTitle("");
        tvTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    /**
     * 设置布局资源
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view操作
     *
     * @return
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @return
     */
    protected abstract void loadData();

    protected abstract View injectTarget();

    /**
     * 接收前一个页面传递的String值
     *
     * @param key
     * @return
     */
    public String getStringExtra(String key) {
        Intent receive = getIntent();
        return receive.getStringExtra(key);
    }

    /**
     * 接收前一个页面传递的Integer值
     *
     * @param key
     * @return
     */
    public Integer getIntExtra(String key) {
        Intent receive = getIntent();
        return receive.getIntExtra(key, 0);
    }

    /**
     * 接收前一个页面传递的Long值
     *
     * @param key
     * @return
     */
    public Long getLongExtra(String key) {
        Intent receive = getIntent();
        return receive.getLongExtra(key, 0L);
    }

    /**
     * 接收前一个页面传递的Boolean值
     *
     * @param key
     * @return
     */
    protected Boolean getBooleanExtra(String key) {
        Intent receive = getIntent();
        return receive.getBooleanExtra(key, false);
    }

    public Object getSerializableExtra(String key) {
        Intent receive = getIntent();
        return receive.getSerializableExtra(key);
    }

    /**
     * 通过类名启动Activity，是否结束本页面
     *
     * @param pClass
     * @param tag
     */
    protected void openActivity(Class<?> pClass, int tag) {
        showActivity(pClass, null, null, null, false, tag, -1);
    }

    /**
     * 通过类名启动Activity，是否结束本页面
     *
     * @param pClass
     * @param isfinish
     */
    public void openActivity(Class<?> pClass, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带数据
     *
     * @param pClass
     * @param key
     * @param value
     */
    public void openActivity(Class<?> pClass, String key, Serializable value) {
        showActivity(pClass, null, key, value, false, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带单个数据
     *
     * @param pClass
     * @param key
     * @param value
     * @param isfinish
     */
    public void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
        showActivity(pClass, null, key, value, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带单个数据
     *
     * @param pClass
     * @param key
     * @param value
     * @param tag
     */
    public void openActivity(Class<?> pClass, String key, Serializable value, int tag) {
        showActivity(pClass, null, key, value, false, tag, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Flags数据
     *
     * @param pClass
     * @param flags
     * @param isfinish
     */
    public void openActivity(Class<?> pClass, int flags, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1, flags);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param bundle
     */
    public void openActivity(Class<?> pClass, Bundle bundle) {
        showActivity(pClass, bundle, null, null, false, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param bundle
     * @param isfinish
     */
    public void openActivity(Class<?> pClass, Bundle bundle, boolean isfinish) {
        showActivity(pClass, bundle, null, null, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param tag
     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int tag) {
        showActivity(pClass, pBundle, null, null, false, tag, -1);
    }

    /**
     * @param pClass
     * @param bundle
     * @param key
     * @param value
     * @param isfinish
     * @param tag
     * @param flags
     */
    private void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
            boolean isfinish, int tag, int flags) {

        Intent intent = new Intent(activity, pClass);

        if (null != key) {
            intent.putExtra(key, value);
        }

        if (null != bundle) {
            intent.putExtras(bundle);
        }

        if (flags != -1) {
            intent.setFlags(flags);
        }

        if (tag == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, tag);
        }

        hideSoftKeyBoard();
        if (isfinish) {
            this.finish();
        }
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
            mToast = Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT);
        }
        mToast.setText(content);
        if (temp - time < TimeUtils.ONE_SECOND) {
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
            mToast = Toast.makeText(getApplicationContext(), getString(resId), Toast.LENGTH_SHORT);
        }
        mToast.setText(getString(resId));
        if (temp - time < TimeUtils.ONE_SECOND) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        time = temp;
        mToast.show();
    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > TimeUtils.ONE_SECOND * 2) {
            showToast("再按一次退出！");
            exitTime = System.currentTimeMillis();
        } else {
            App.exit();
        }
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁
        }
    }

    @Override
    public void onBackPressed() {
        hideSoftKeyBoard();
        ActivityCompat.finishAfterTransition(this);
    }
}
