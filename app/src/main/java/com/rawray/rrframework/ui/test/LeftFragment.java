package com.rawray.rrframework.ui.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rawray.rrframework.R;
import com.rawray.rrframework.ui.common.base.fragment.AbsStatusFragment;
import com.rawray.rrframework.utils.NetworkUtils;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rawray on 17-5-23.
 */

public class LeftFragment extends AbsStatusFragment {

    //----------------------- AbsFragment -------------------------------------
    @Override
    public void initContentView(View view) {
        loadData();
    }

    //----------------------- AbsStatusFragment -------------------------------
    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(getActivity())
                .contentView(R.layout.fragment_left)
                .emptyDataView(R.layout.rl_empty_data)
                .errorView(R.layout.rl_error)
                .loadingView(R.layout.rl_loading)
                .netWorkErrorView(R.layout.rl_network_error)
                .build();
    }

    //----------------------- Override Methed ---------------------------------
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------
    private void loadData() {

        if (!NetworkUtils.avaliable()) {
            showNetworkError();
            return;
        }

        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000); //  imitate expensive computation
                return "Done";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i("LeftFragment", "data received");
                        showEmptyData();
                    }
                });
    }

}
