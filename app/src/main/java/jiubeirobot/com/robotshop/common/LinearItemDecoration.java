package jiubeirobot.com.robotshop.common;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/25.
 * 创建内容：recyclerview 定义的分割线格式
 * ==========================
 */

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanSpace = 20;

    public LinearItemDecoration() {
    }

    public LinearItemDecoration(int span) {
        mSpanSpace = span;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (isVertical(parent)) {
            if (parent.getChildAdapterPosition(view) < parent.getAdapter().getItemCount() - 1) {
                outRect.set(0, 0, 0, mSpanSpace);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            if (parent.getChildAdapterPosition(view) < parent.getAdapter().getItemCount() - 1) {
                outRect.set(0, 0, mSpanSpace, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    private boolean isVertical(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) layoutManager)
                    .getOrientation();
            return orientation == LinearLayoutManager.VERTICAL;
        }
        return false;
    }
}
