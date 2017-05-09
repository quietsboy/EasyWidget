package com.quietboy.easywidget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * TODO
 *
 * @Author JiangYY
 * @Date 2017/3/9
 */

public class StatusLayout extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;
    private ProgressBar mProgressBar;

    private String message = getContext().getString(R.string.loading);

    private OnRetryListener onRetryListener;
    public int state;
    private static final int ERROR = 0;
    private static final int LOADING = 1;
    private static final int FINISHING = 2;
    private static final int EMPTY = 3;
    private static final int NOT_LOGIN = 4;

    @DrawableRes
    private int image = R.drawable.ic_launcher;

    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setClickable(true);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mProgressBar = new ProgressBar(getContext());
        mProgressBar.setLayoutParams(layoutParams);
        addView(mProgressBar);

        mImageView = new ImageView(getContext());
        mImageView.setLayoutParams(layoutParams);
        mImageView.setImageResource(image);
        addView(mImageView);

        mTextView = new TextView(getContext());
        mTextView.setLayoutParams(layoutParams);
        mTextView.setText(message);
        mTextView.setPadding(10, 10, 10, 10);
        addView(mTextView);

        mTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoading();
                onRetryListener.onRetryClick(v);
            }
        });

    }

    /**
     * 操作完成
     */
    public void isFinished() {
//        state = FINISHING;
        if (state == EMPTY || state == ERROR) return;
        this.setVisibility(GONE);
    }

    /**
     * 正在加载
     */
    public void isLoading() {
        state = LOADING;
        mProgressBar.setVisibility(VISIBLE);
        mImageView.setVisibility(GONE);

        this.setVisibility(VISIBLE);
        setMessage("正在加载...");
    }

    public void isError() {
        state = ERROR;
        mProgressBar.setVisibility(GONE);
        mImageView.setVisibility(VISIBLE);
        this.setVisibility(VISIBLE);
        setMessage("加载失败，点击重试！");
    }

    public void isEmpty() {
        state = EMPTY;
        mProgressBar.setVisibility(GONE);
        mImageView.setVisibility(VISIBLE);
        this.setVisibility(VISIBLE);
        setMessage("暂无数据，点击重试！");
    }

    public void isNotLogin() {
        state = NOT_LOGIN;
        mProgressBar.setVisibility(GONE);
        mImageView.setVisibility(VISIBLE);
        this.setVisibility(VISIBLE);
        setMessage("无账号登陆");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        mTextView.setText(message);
    }

    public interface OnRetryListener {
        void onRetryClick(View view);
    }

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
    }
}
