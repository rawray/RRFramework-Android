package com.rawray.rrframework.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.rawray.rrframework.R;
import com.rawray.rrframework.ui.common.base.activity.AbsStatusActivity;
import com.rawray.rrframework.ui.test.TestMultiFragmentActivity;
import com.rawray.rrframework.ui.test.TestStatusActivity;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AbsStatusActivity {

    @OnClick(R.id.btn_test_status)
    void onClickTestStatus() {
        startActivity(new Intent(this, TestStatusActivity.class));
    }

    @OnClick(R.id.btn_test_multi_fragment)
    void onClickTestMultiFragment() {
        startActivity(new Intent(this, TestMultiFragmentActivity.class));
    }

    //----------------------- Override Methed ---------------------------------

    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(R.layout.activity_main)
                .emptyDataView(R.layout.rl_empty_data)
                .errorView(R.layout.rl_error)
                .loadingView(R.layout.rl_loading)
                .netWorkErrorView(R.layout.rl_network_error)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    //----------------------- Private Methed ----------------------------------

    private void initView() {
        setTitle(R.string.activity_main_title);
    }

    private void loadData() {
        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000); //  imitate expensive computation
                return "Done";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        showContent();
                    }
                });
    }

}
