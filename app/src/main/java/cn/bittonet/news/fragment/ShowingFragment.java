package cn.bittonet.news.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import cn.bittonet.news.R;
import cn.bittonet.news.adapter.GridShopAdapter;
import cn.bittonet.news.base.fragment.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.ArrayList;

/**
 * Created by Felone on 2019/2/20 0020 下午 3:28
 * description：
 * 50257836@qq.com
 */
public class ShowingFragment extends BaseFragment {


    @BindView(R.id.recyclerview)
    RecyclerView       recyclerview;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.layout)
    FrameLayout        layout;
    @BindView(R.id.layout_bottom)
    LinearLayout       layoutBottom;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_showing;
    }

    @Override
    protected void initView() {

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        recyclerview.setNestedScrollingEnabled(false);
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            // 在 Adapter的 onBindViewHolder 之后执行
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager,
                    int velocityX, int velocityY) {
                //                // TODO 找到对应的Index
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                //                setMvItemClick(linearLayoutManager.findViewByPosition(targetPos));
                //                if (targetPos >= (strings.size() - 1)) {
                //                    strings.addAll(Arrays.asList(urls));
                //                    mvAdapter.notifyDataSetChanged();
                //                }
                return targetPos;
            }

            // 在 Adapter的 onBindViewHolder 之后执行
            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                // TODO 找到对应的View
                View view = super.findSnapView(layoutManager);
                //                setMvItemClick(view);
                return view;
            }
        };
        snapHelper.attachToRecyclerView(recyclerview);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);


        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(R.drawable.bg_all);
        objects.add(R.drawable.bg_half);
        GridShopAdapter gridShopAdapter = new GridShopAdapter(objects);
        recyclerview.setAdapter(gridShopAdapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected View injectTarget() {
        return layout;
    }

}
