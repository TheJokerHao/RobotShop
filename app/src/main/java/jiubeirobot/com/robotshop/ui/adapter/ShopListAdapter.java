package jiubeirobot.com.robotshop.ui.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseRecyclerAdapter;
import jiubeirobot.com.robotshop.base.BaseViewHolder;
import jiubeirobot.com.robotshop.bean.ShopListBean;
import jiubeirobot.com.robotshop.bean.StockBean;
import jiubeirobot.com.robotshop.listener.ItemClick;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/22.
 * 创建内容：
 * ==========================
 */

public class ShopListAdapter extends BaseRecyclerAdapter<ShopListBean> {

    private CellClick cellClick;

    public void setCellClick(CellClick cellClick) {
        this.cellClick = cellClick;
    }

    public ShopListAdapter(Context context, List<ShopListBean> list) {
        super(context, list, R.layout.item_shop_list);
    }

    @Override
    public void convert(BaseViewHolder holder, ShopListBean shopListBean) {

    }

    public interface CellClick extends ItemClick{
        void addToCard(View view, int[] startLocation, StockBean stockBean,int shopNum);
    }


}
