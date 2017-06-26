package com.rawray.rrframework.ui.common.base.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.rawray.rrframework.R;
import com.rawray.rrframework.ui.common.base.recycler.AbsRecyclerLoadMoreAdapter;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

import butterknife.BindView;

/**
 * Created by rawray on 17-5-27.
 */

public abstract class AbsSwipeRecyclerStatusFragment extends AbsRecyclerStatusFragment {

    private int mLastVisiblePosition = -1;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    //----------------------- Abstract Methed ----------------------------------
    protected abstract void onLoadData();
    protected abstract void onPullRefresh();
    protected abstract void onLoadMore();
    protected abstract void onAutoLoadMore();

    //----------------------- AbsFragment -------------------------------------
    @Override
    public void initContentView(View view) {
        super.initContentView(view);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullRefresh();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if(layoutManager instanceof LinearLayoutManager){
                    mLastVisiblePosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if(layoutManager instanceof GridLayoutManager){
                    mLastVisiblePosition = ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if(layoutManager instanceof StaggeredGridLayoutManager){
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int []lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    mLastVisiblePosition = findMax(lastPositions);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                View firstView = recyclerView.getChildAt(0);
                int top = firstView.getTop();
                int topEdge = recyclerView.getPaddingTop();

                boolean isFullScreen = top < topEdge;
                getAdapter().setUiCanShowLoadMore(isFullScreen);
                getAdapter().updateLoadMoreCell();

                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                int itemCount = manager.getItemCount();

                if (newState == RecyclerView.SCROLL_STATE_IDLE && getAdapter().canOnEvent()) {
                    if (mLastVisiblePosition == itemCount - 1) { //最后一个Item
                        onLoadMore();
                    } else {
                        if (itemCount > 2) {
                            if (mLastVisiblePosition == itemCount - 2 || mLastVisiblePosition == itemCount - 3) {
                                onAutoLoadMore();
                            }
                        }
                    }
                }

            }
        });

        onLoadData();
    }

    //----------------------- AbsStatusFragment -------------------------------
    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(getActivity())
                .contentView(R.layout.fragment_absswiperecycler)
                .emptyDataView(R.layout.rl_empty_data)
                .errorView(R.layout.rl_error)
                .loadingView(R.layout.rl_loading)
                .netWorkErrorView(R.layout.rl_network_error)
                .build();
    }

    //----------------------- AbsRecyclerStatusFragment -----------------------
    @Override
    protected AbsRecyclerLoadMoreAdapter onCreateRecyclerAdapter() {
        return new AbsRecyclerLoadMoreAdapter(getActivity());
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected AbsRecyclerLoadMoreAdapter getAdapter() {
        return (AbsRecyclerLoadMoreAdapter) mAdapter;
    }

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    protected void setRefreshing(boolean value) {
        mSwipeRefreshLayout.setRefreshing(value);
    }

    //----------------------- Private Methed ----------------------------------

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}
