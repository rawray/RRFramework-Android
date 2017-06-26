package com.rawray.rrframework.ui.common.base.recycler.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rawray.rrframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rawray on 17-6-16.
 */

public class LoadMoreCellViewHolder extends RecyclerCellViewHolder {

    @BindView(R.id.tv_content)
    TextView mTvContent;

    //----------------------- Public Methed -----------------------------------

    public LoadMoreCellViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public static LoadMoreCellViewHolder createInstance(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cell_loadmore, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        LoadMoreCellViewHolder viewHolder = new LoadMoreCellViewHolder(view);
        return viewHolder;
    }

    public void setContent(String text) {
        mTvContent.setText(text);
    }
}
