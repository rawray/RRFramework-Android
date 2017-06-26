package com.rawray.rrframework.ui.test;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rawray.rrframework.ui.common.base.fragment.AbsSwipeRecyclerStatusFragment;
import com.rawray.rrframework.ui.common.base.recycler.AbsRecyclerLoadMoreAdapter;
import com.rawray.rrframework.ui.common.base.recycler.cell.IRecyclerCell;
import com.rawray.rrframework.ui.common.base.recycler.cell.TextRecyclerCell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rawray on 17-5-23.
 */

public class RightFragment extends AbsSwipeRecyclerStatusFragment {

    private int mIndex = 0;

    //----------------------- AbsFragment -------------------------------------
    @Override
    public void initContentView(View view) {
        super.initContentView(view);
    }

    //----------------------- AbsStatusFragment -------------------------------
    @Override
    protected void initStatusLayout() {
        super.initStatusLayout();
    }

    //----------------------- AbsRecyclerStatusFragment -----------------------
    @Override
    protected AbsRecyclerLoadMoreAdapter onCreateRecyclerAdapter() {
        return super.onCreateRecyclerAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return super.onCreateLayoutManager();
    }

    //----------------------- AbsSwipeRecyclerStatusFragment ------------------
    @Override
    protected void onLoadData() {
        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000); //  imitate expensive computation
                return "Done";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        showContent();
                        getAdapter().addAll(getCells(getData()));
                    }
                });
    }

    @Override
    protected void onPullRefresh() {
        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000); //  imitate expensive computation
                mIndex = 0;
                return "Done";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        setRefreshing(false);
                        getAdapter().clear();
                        getAdapter().addAll(getCells(getData()));
                        getAdapter().setHasMoreData(true);
                    }
                });
    }

    @Override
    protected void onLoadMore() {
        getAdapter().loadMoreStart();
        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000); //  imitate expensive computation
                return "Done";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        getAdapter().loadMoreComplete();
                        getAdapter().addAll(getCells(getData()));
                        getAdapter().setHasMoreData(true);
                    }
                });
    }

    @Override
    protected void onAutoLoadMore() {
        getAdapter().loadMoreStart();
        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000); //  imitate expensive computation
                return "Done";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        getAdapter().loadMoreComplete();
                        getAdapter().addAll(getCells(getData()));
                        getAdapter().setHasMoreData(true);
                    }
                });
    }

    //----------------------- Override Methed ---------------------------------

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------

    private List<IRecyclerCell> getCells(List<String> entries){
        //根据实体生成Cell
        List<IRecyclerCell> cells = new ArrayList<>();
        for (int i=0;i<entries.size();i++){
            String entry = entries.get(i);
            TextRecyclerCell recyclerCell = new TextRecyclerCell(entry);
            cells.add(recyclerCell);
        }
        return cells;
    }

    private List<String> getData(){
        List<String> data = new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add("标题-" + (mIndex + i));
        }
        mIndex += 20;
        return data;
    }
}
