package com.rawray.rrframework.ui.test;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rawray.rrframework.R;
import com.rawray.rrframework.ui.common.base.activity.AbsStatusActivity;
import com.rawray.rrframework.vendor.statuslayout.OnShowHideViewListener;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.rawray.rrframework.vendor.statuslayout.RootFrameLayout.LAYOUT_LOADING_ID;

/**
 * Created by rawray on 17-6-20.
 */

public class TestStatusActivity extends AbsStatusActivity implements OnShowHideViewListener {

    //----------------------- Override Methed ---------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_loading:
                showLoading();
                break;
            case R.id.item_content:
                showContent();
                break;
            case R.id.item_empty_data:
                showEmptyData();
                break;
            case R.id.item_network_error:
                showNetworkError();
                break;
            case R.id.item_error:
                showError();
                break;
        }
        return true;
    }

    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(R.layout.activity_test_status)
                .emptyDataView(R.layout.rl_empty_data)
                .errorView(R.layout.rl_error)
                .loadingView(R.layout.rl_loading)
                .netWorkErrorView(R.layout.rl_network_error)
                .onShowHideViewListener(this)
                .build();
    }

   @Override
    public void onShowView(View view, int id) {
        if (LAYOUT_LOADING_ID == id) {

        }
    }

    @Override
    public void onHideView(View view, int id) {

    }

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------

    private void loadData() {
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
                    }
                });
    }


    private void initView() {
        setTitle(R.string.activity_test_status_title);
    }


 }
