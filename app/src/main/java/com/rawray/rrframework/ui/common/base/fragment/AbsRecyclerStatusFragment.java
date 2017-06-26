package com.rawray.rrframework.ui.common.base.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rawray.rrframework.R;
import com.rawray.rrframework.ui.common.base.recycler.AbsRecyclerAdapter;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

import butterknife.BindView;

/**
 * Created by rawray on 17-5-26.
 */

public abstract class AbsRecyclerStatusFragment extends AbsStatusFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    protected AbsRecyclerAdapter mAdapter;

    //----------------------- Abstract Methed ----------------------------------
    protected abstract AbsRecyclerAdapter onCreateRecyclerAdapter();
    protected abstract RecyclerView.LayoutManager onCreateLayoutManager();
    protected abstract AbsRecyclerAdapter getAdapter();

    //----------------------- AbsFragment -------------------------------------

    @Override
    public void initContentView(View view) {
        mAdapter = onCreateRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(onCreateLayoutManager());
    }

    //----------------------- AbsStatusFragment -------------------------------

    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(getActivity())
                .contentView(R.layout.fragment_absrecycler)
                .emptyDataView(R.layout.rl_empty_data)
                .errorView(R.layout.rl_error)
                .loadingView(R.layout.rl_loading)
                .netWorkErrorView(R.layout.rl_network_error)
                .build();
    }

}
