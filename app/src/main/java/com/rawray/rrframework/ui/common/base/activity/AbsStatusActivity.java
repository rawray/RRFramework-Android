package com.rawray.rrframework.ui.common.base.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.rawray.rrframework.R;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;


/**
 * Created by duguo on 2017-6-9.
 */
public abstract class AbsStatusActivity extends AbsActivity {

    protected StatusLayoutManager statusLayoutManager;

    //----------------------- Override Methed ---------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initStatusLayout();

        LinearLayout mainLl = (LinearLayout) findViewById(R.id.main_rl);
        mainLl.addView(statusLayoutManager.getRootLayout());

        statusLayoutManager.showLoading();
    }


    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------
    protected abstract void initStatusLayout();

    protected void showLoading() {
        statusLayoutManager.showLoading();
    }

    protected void showNetworkError() {
        statusLayoutManager.showNetworkError();
    }

    protected void showEmptyData() {
        statusLayoutManager.showEmptyData();
    }

    protected void showError() {
        statusLayoutManager.showError();
    }

    protected void showContent() {
        statusLayoutManager.showContent();
    }

    //----------------------- Private Methed ----------------------------------


}
