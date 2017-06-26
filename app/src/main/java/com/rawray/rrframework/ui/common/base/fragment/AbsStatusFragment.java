package com.rawray.rrframework.ui.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rawray.rrframework.vendor.statuslayout.StatusLayoutManager;

/**
 * Created by rawray on 17-5-26.
 */

public abstract class AbsStatusFragment extends AbsFragment {

    protected StatusLayoutManager statusLayoutManager;

    //----------------------- Abstract Methed ----------------------------------

    protected abstract void initStatusLayout();

    //----------------------- Override Methed ---------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        initStatusLayout();
        return statusLayoutManager.getRootLayout();
    }

    //----------------------- Protected Methed --------------------------------

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

}
