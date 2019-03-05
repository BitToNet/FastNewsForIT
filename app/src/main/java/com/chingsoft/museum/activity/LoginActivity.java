package com.chingsoft.museum.activity;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.chingsoft.museum.R;
import com.chingsoft.museum.apps.App;
import com.chingsoft.museum.base.activity.BaseActivity;
import com.chingsoft.museum.bean.BaseBean;
import com.chingsoft.museum.bean.UserBean;
import com.chingsoft.museum.http.RetrofitManager;
import com.chingsoft.museum.utils.StringUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.root_layout)
    ConstraintLayout mRlLogin;

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.line_1)
    View     mLine1;
    @BindView(R.id.et_password)
    EditText mEtPwd;
    @BindView(R.id.line_2)
    View     mLine2;
    @BindView(R.id.btn_login)
    Button   mBtnLogin;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String mMobile = mEtPhone.getText().toString().trim();
        String mPwd    = mEtPwd.getText().toString().trim();

        if (StringUtils.isEmpty(mMobile)) {
            showToast(R.string.mobile_not_null);
            return;
        } else if (StringUtils.isEmpty(mPwd)) {
            showToast(R.string.password_not_null);
            return;
        } else {
            login(mMobile, mPwd);
        }
    }

    private void login(String mMobile, String mPwd) {
        mStateView.showLoading("正在登录...");
        RetrofitManager.getInstance()
                       .getApiService()
                       .login(mMobile, mPwd, "token", "9")
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<BaseBean<UserBean>>() {

                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(BaseBean<UserBean> bean) {
                               mStateView.hidenLoading();
                               if (bean.success()) {
                                   UserBean data = bean.getResult();
                                   App.setSessionId(data.getSessionInfo().getSessionId());
                                   openActivity(MainActivity.class, true);
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
    }

    @Override
    protected View injectTarget() {
        return mRlLogin;
    }
}
