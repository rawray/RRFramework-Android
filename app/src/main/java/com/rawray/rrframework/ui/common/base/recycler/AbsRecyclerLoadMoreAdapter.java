package com.rawray.rrframework.ui.common.base.recycler;

import android.content.Context;

import com.rawray.rrframework.ui.common.base.recycler.cell.LoadMoreCell;
import com.rawray.rrframework.ui.common.base.recycler.cell.RecyclerCellType;

/**
 * Created by rawray on 17-6-22.
 */

public class AbsRecyclerLoadMoreAdapter extends AbsRecyclerAdapter {

    private LoadMoreCell mLoadMoreCell = new LoadMoreCell("正在加载...");

    private boolean mHasMoreData = true;
    private boolean mUiCanShowLoadMore = false;
    private boolean mNetworkLoadMoreing = false;

    //----------------------- Override Methed ---------------------------------

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    public AbsRecyclerLoadMoreAdapter(Context context) {
        super(context);
    }

    public void loadMoreStart() {
        //update network state
        mNetworkLoadMoreing = true;
    }

    public void loadMoreComplete() {
        //update network state
        mNetworkLoadMoreing = false;

        //update ui
        int index = -1;
        for (int i = getItemCount() - 1; i >= 0; --i) {
            if (RecyclerCellType.typeFromClass(LoadMoreCell.class) == getItemViewType(i)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            remove(index);
        }
    }

    public void setHasMoreData(boolean value) {
        mHasMoreData = value;
    }

    public void setUiCanShowLoadMore(boolean value) {
        mUiCanShowLoadMore = value;
    }

    public void updateLoadMoreCell() {
        int index = -1;
        for (int i = getItemCount() - 1; i >= 0; --i) {
            if (RecyclerCellType.typeFromClass(LoadMoreCell.class) == getItemViewType(i)) {
                index = i;
                break;
            }
        }

        if (canShowLoadMore()) {
            if (index == -1) {
                add(mLoadMoreCell);
            }
        } else {
            if (index != -1) {
                remove(index);
            }
        }
    }

    public boolean canOnEvent() {
        return canShowLoadMore() && !mNetworkLoadMoreing;
    }

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------

    private boolean canShowLoadMore() {
        return mHasMoreData && mUiCanShowLoadMore;
    }
}
