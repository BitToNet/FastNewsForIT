package com.chingsoft.museum.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.chingsoft.museum.R;
import com.chingsoft.museum.base.activity.BaseActivity;
import com.chingsoft.museum.fragment.AnalyzeFragment;
import com.chingsoft.museum.fragment.DeviceFragment;
import com.chingsoft.museum.fragment.SettingFragment;
import com.chingsoft.museum.fragment.ShowingFragment;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout          flContent;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int lastIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fragments.add(new ShowingFragment());
        fragments.add(new DeviceFragment());
        fragments.add(new AnalyzeFragment());
        fragments.add(new SettingFragment());

        selectFragment(0);

        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_showing:
                selectFragment(0);
                return true;
            case R.id.navigation_device:
                selectFragment(1);
                return true;
            case R.id.navigation_analyze:
                selectFragment(2);
                return true;
            case R.id.navigation_setting:
                selectFragment(3);
                return true;
            default:
                return false;
        }
    };

    private void selectFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment currentFragment = fragments.get(position);
        Fragment lastFragment    = fragments.get(lastIndex);

        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.fl_content, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected View injectTarget() {
        return flContent;
    }

}
