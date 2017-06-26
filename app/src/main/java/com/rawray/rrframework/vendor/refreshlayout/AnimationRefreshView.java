package com.rawray.rrframework.vendor.refreshlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rawray.rrframework.R;

import java.util.Locale;

/**
 * Created by rawray on 17-6-23.
 */

public class AnimationRefreshView extends FrameLayout implements IRefresh {

    private ImageView imgLoading;
    private TextView tvRefresh;
    private AnimationDrawable animationDrawable;

    private CharSequence pullStr = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage()) ? "下拉刷新" : "PULL TO REFRESH";
    private CharSequence releaseStr = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage()) ? "释放刷新" : "RELEASE TO REFRESH";

    public AnimationRefreshView(Context context) {
        this(context, null);
    }

    public AnimationRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_animation_refresh, null);
        addView(v);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imgLoading = (ImageView) findViewById(R.id.img_loading);
        tvRefresh = (TextView) findViewById(R.id.tv_refresh);
        animationDrawable = (AnimationDrawable) imgLoading.getDrawable();
    }

    @Override
    public void onReset() {
        animationDrawable.stop();
        imgLoading.setVisibility(GONE);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onRelease() {
        animationDrawable.start();
        imgLoading.setVisibility(VISIBLE);
        tvRefresh.setVisibility(GONE);
    }

    @Override
    public void onComplete() {
        animationDrawable.stop();
        imgLoading.setVisibility(GONE);
        tvRefresh.setVisibility(VISIBLE);
        tvRefresh.setVisibility(GONE);
    }

    @Override
    public void onPositionChange(float currentPercent) {
        imgLoading.setVisibility(GONE);
        tvRefresh.setVisibility(VISIBLE);
        if (currentPercent < 1) {
            tvRefresh.setText(pullStr);
        } else {
            tvRefresh.setText(releaseStr);
        }
    }

    @Override
    public void setIsHeaderOrFooter(boolean isHeader) {
        if (!isHeader) {
            pullStr = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage()) ? "上拉加载更多" : "PULL TO REFRESH";
            releaseStr = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage()) ? "释放加载更多" : "RELEASE TO REFRESH";
        }
    }

    public CharSequence getReleaseStr() {
        return releaseStr;
    }

    public void setReleaseStr(CharSequence releaseStr) {
        this.releaseStr = releaseStr;
    }

    public CharSequence getPullStr() {
        return pullStr;
    }

    public void setPullStr(CharSequence pullStr) {
        this.pullStr = pullStr;
    }
}
