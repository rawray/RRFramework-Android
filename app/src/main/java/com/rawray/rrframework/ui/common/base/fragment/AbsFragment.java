package com.rawray.rrframework.ui.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rawray on 17-5-26.
 */

public abstract class AbsFragment extends Fragment {

    private Unbinder unbinder;

    //----------------------- Absolute Methed ---------------------------------

    public abstract void initContentView(View view);

    //----------------------- Override Methed ---------------------------------

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initContentView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
