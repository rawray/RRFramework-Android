package com.rawray.rrframework.ui.common.base.recycler.cell;

/**
 * Created by rawray on 17-5-27.
 */

public abstract class AbsRecyclerCell<T> implements IRecyclerCell {

    public AbsRecyclerCell(T t){
        mData = t;
    }
    public T mData;

    //----------------------- Override Methed ---------------------------------

    @Override
    public void releaseResource() {
        // 如果有需要回收的资源，子类自己实现
    }

    @Override
    public int getItemType() {
        return RecyclerCellType.typeFromClass(this.getClass());
    }
}
