package cn.bittonet.news.adapter;

import cn.bittonet.news.R;
import cn.bittonet.news.base.holder.BaseViewHolderHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;

/**
 * Created by Felone on 2019/2/22 0022 上午 10:30
 * description：
 * 50257836@qq.com
 */
public class GridShopAdapter extends BaseQuickAdapter<Integer, BaseViewHolderHelper> {

    public GridShopAdapter(ArrayList<Integer> data) {
        super(R.layout.item_grid_shop, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, Integer item) {
//        helper.setImageUrl(R.id.video_image,"http://img.xspic.com/img8/39/123/2456359_1.jpg");
        helper.setImageResource(R.id.video_image,item);
    }
}
