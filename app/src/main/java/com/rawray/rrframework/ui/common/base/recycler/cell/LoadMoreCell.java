package com.rawray.rrframework.ui.common.base.recycler.cell;

import android.content.Context;
import android.view.ViewGroup;

import com.rawray.rrframework.ui.common.base.recycler.viewholder.LoadMoreCellViewHolder;
import com.rawray.rrframework.ui.common.base.recycler.viewholder.RecyclerCellViewHolder;

/**
 * Created by rawray on 17-6-16.
 */

public class LoadMoreCell extends AbsRecyclerCell<String> {

    //----------------------- Override Methed ---------------------------------

    @Override
    public void releaseResource() {
        super.releaseResource();
    }

    @Override
    public RecyclerCellViewHolder onCreateViewHolder(Context context, ViewGroup parent, int viewType) {
        LoadMoreCellViewHolder viewHolder = LoadMoreCellViewHolder.createInstance(parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerCellViewHolder holder, int position) {
        LoadMoreCellViewHolder viewHolder = (LoadMoreCellViewHolder) holder;
        viewHolder.setContent(mData);
        //TODO notify outside to fetch data

    }

    //----------------------- Public Methed -----------------------------------

    public LoadMoreCell(String s) {
        super(s);
    }


}
