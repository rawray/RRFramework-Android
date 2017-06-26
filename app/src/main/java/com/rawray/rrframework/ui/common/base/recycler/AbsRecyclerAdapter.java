package com.rawray.rrframework.ui.common.base.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.rawray.rrframework.ui.common.base.recycler.cell.AbsRecyclerCell;
import com.rawray.rrframework.ui.common.base.recycler.viewholder.RecyclerCellViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rawray on 17-5-27.
 */

public abstract class AbsRecyclerAdapter<C extends AbsRecyclerCell> extends RecyclerView.Adapter<RecyclerCellViewHolder> {

    protected Context mContext;
    protected List<C> mData;

    //----------------------- Absolute Methed ---------------------------------

    //----------------------- Override Methed ---------------------------------

    @Override
    public RecyclerCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for(int i=0;i<getItemCount();i++){
            if(viewType == getItemViewType(i)){
                return mData.get(i).onCreateViewHolder(mContext, parent,viewType);
            }
        }

        throw new RuntimeException("wrong viewType");
    }

    @Override
    public void onBindViewHolder(RecyclerCellViewHolder holder, int position) {
        mData.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerCellViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //释放资源
        int position = holder.getPosition();
        //越界检查
        if(position<0 || position>=mData.size()){
            return;
        }
        mData.get(position).releaseResource();
    }

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    public AbsRecyclerAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    public void setData(List<C> data) {
        addAll(data);
        notifyDataSetChanged();
    }

    public List<C> getData() {
        return mData;
    }

    public void add(C cell){
        mData.add(cell);
        int index = mData.indexOf(cell);
        notifyItemChanged(index);
    }

    public void add(int index,C cell){
        mData.add(index,cell);
        notifyItemChanged(index);
    }

    public void remove(C cell){
        int indexOfCell = mData.indexOf(cell);
        remove(indexOfCell);
    }

    public void remove(int index){
        mData.remove(index);
        notifyItemRemoved(index);
    }

    public void remove(int start,int count){
        if((start +count) > mData.size()){
            return;
        }

        mData.subList(start,start+count).clear();

        notifyItemRangeRemoved(start,count);
    }

    public void addAll(List<C> cells){
        if(cells == null || cells.size() == 0){
            return;
        }
        mData.addAll(cells);
        notifyItemRangeChanged(mData.size()-cells.size(),mData.size());
    }

    public void addAll(int index,List<C> cells){
        if(cells == null || cells.size() == 0){
            return;
        }
        mData.addAll(index,cells);
        notifyItemRangeChanged(index,index+cells.size());
    }

    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------

}
