package com.rawray.rrframework.ui.common.base.recycler.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by rawray on 17-5-27.
 */

public class RecyclerCellViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    protected OnCellClickListener mOnCellClickListener;

    //----------------------- Public Methed -----------------------------------

    public RecyclerCellViewHolder(View view) {
        super(view);
        views = new SparseArray<>();
    }

    //@SuppressWarnings("unchecked")
    public  <V extends View> V retrieveView(int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (V) view;
    }

    public void setOnCellClickListener(OnCellClickListener listener) {
        mOnCellClickListener = listener;
    }

    public interface OnCellClickListener {
        void onCellClick(RecyclerCellViewHolder holder);
    }
}
