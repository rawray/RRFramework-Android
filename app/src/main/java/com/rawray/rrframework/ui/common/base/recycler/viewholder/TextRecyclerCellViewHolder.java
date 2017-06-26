package com.rawray.rrframework.ui.common.base.recycler.viewholder;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rawray.rrframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rawray on 17-6-9.
 */

public class TextRecyclerCellViewHolder extends RecyclerCellViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_badge)
    TextView mTvBadge;

    @OnClick(R.id.tv_badge)
    public void onClickBadge() {
        mTvBadge.setText("5");
    }

    @OnClick(R.id.tv_content)
    public void onClickContent() {
        mTvContent.setText("content");
    }

    //----------------------- Override Methed ---------------------------------

    @Override
    public void onClick(View v) {
        if (mOnCellClickListener != null) {
            mOnCellClickListener.onCellClick(this);
        }
    }

    //----------------------- Static Methed -----------------------------------

    public static TextRecyclerCellViewHolder createInstance(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cell_text, null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        TextRecyclerCellViewHolder viewHolder = new TextRecyclerCellViewHolder(view);
        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    //----------------------- Public Methed -----------------------------------

    public TextRecyclerCellViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setContent(String text) {
        mTvContent.setText(text);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setBadge(String text) {

        TextPaint textPaint = mTvBadge.getPaint();
        float textWidth = textPaint.measureText(text);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textHeight = fontMetrics.bottom - fontMetrics.top;

        int width = (int) Math.max(textWidth, textHeight);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTvBadge.getLayoutParams();
        params.width = width;
        params.height = width;
        mTvBadge.setLayoutParams(params);
        mTvBadge.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        int radius = width / 2;

        RoundRectShape shape = new RoundRectShape(new float[] {radius, radius, radius, radius, radius, radius, radius, radius}, null, null);
        ShapeDrawable backgroundDrawable = new ShapeDrawable(shape);
        backgroundDrawable.getPaint().setColor(Color.parseColor("#ff0000"));
        backgroundDrawable.setAlpha(100);
        mTvBadge.setBackground(backgroundDrawable);

        mTvBadge.setText(text);

    }

}
