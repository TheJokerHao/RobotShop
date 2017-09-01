package jiubeirobot.com.robotshop.ui.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseRecyclerAdapter;
import jiubeirobot.com.robotshop.base.BaseViewHolder;
import jiubeirobot.com.robotshop.listener.ItemClick;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/30.
 * 创建内容：搜索  热门的适配器
 * ==========================
 */
public class SearchRecodeAdapter extends BaseRecyclerAdapter<String> {
    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public SearchRecodeAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_search_recode_item);
    }

    @Override
    public void convert(final BaseViewHolder holder, String s) {
        holder.setText(R.id.tv_text, s);
        holder.getView(R.id.llyt_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.onItemClick(holder.getPostion());
                }
            }
        });
    }
}
