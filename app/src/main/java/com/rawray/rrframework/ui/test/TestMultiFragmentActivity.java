package com.rawray.rrframework.ui.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.rawray.rrframework.R;
import com.rawray.rrframework.ui.common.base.activity.AbsStatusActivity;
import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

/**
 * Created by rawray on 17-6-26.
 */

public class TestMultiFragmentActivity extends AbsStatusActivity {

    private LeftFragment leftFragment;
    private RightFragment rightFragment;

    //----------------------- Override Methed ---------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(R.layout.activity_multi_fragment)
                .emptyDataView(R.layout.rl_empty_data)
                .errorView(R.layout.rl_error)
                .loadingView(R.layout.rl_loading)
                .netWorkErrorView(R.layout.rl_network_error)
                .build();
    }

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------

    private void initView() {
        setTitle(R.string.activity_setting_title);
        showContent();

        leftFragment = new LeftFragment();
        rightFragment = new RightFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_left, leftFragment, "1");
        fragmentTransaction.add(R.id.layout_right, rightFragment, "2");
        fragmentTransaction.commit();

    }

}
