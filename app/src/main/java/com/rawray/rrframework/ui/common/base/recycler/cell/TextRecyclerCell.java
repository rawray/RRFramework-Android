package com.rawray.rrframework.ui.common.base.recycler.cell;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rawray.rrframework.ui.common.base.recycler.viewholder.RecyclerCellViewHolder;
import com.rawray.rrframework.ui.common.base.recycler.viewholder.TextRecyclerCellViewHolder;

/**
 * Created by rawray on 17-5-27.
 */

public class TextRecyclerCell extends AbsRecyclerCell<String> implements RecyclerCellViewHolder.OnCellClickListener {

    //----------------------- Override Methed ---------------------------------

    @Override
    public void releaseResource() {

    }

    @Override
    public RecyclerCellViewHolder onCreateViewHolder(final Context context, ViewGroup parent, int viewType) {
        final TextRecyclerCellViewHolder viewHolder = TextRecyclerCellViewHolder.createInstance(parent);
        viewHolder.setOnCellClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerCellViewHolder holder, int position) {

        TextRecyclerCellViewHolder viewHolder = (TextRecyclerCellViewHolder) holder;
        viewHolder.setContent(mData);
        viewHolder.setBadge("99+");

    }

    @Override
    public void onCellClick(RecyclerCellViewHolder holder) {
        Toast.makeText(holder.itemView.getContext(), "click cell", Toast.LENGTH_SHORT).show();
    }

    //----------------------- Public Methed -----------------------------------

    public TextRecyclerCell(String entry) {
        super(entry);
    }

}
