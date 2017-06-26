package com.rawray.rrframework.ui.common.base.recycler.cell;

import android.content.Context;
import android.view.ViewGroup;

import com.rawray.rrframework.ui.common.base.recycler.viewholder.RecyclerCellViewHolder;

/**
 * Created by rawray on 17-5-27.
 */

public interface IRecyclerCell {

    void releaseResource();
    int getItemType();
    RecyclerCellViewHolder onCreateViewHolder(Context context, ViewGroup parent, int viewType);
    void onBindViewHolder(RecyclerCellViewHolder holder, int position);

}
