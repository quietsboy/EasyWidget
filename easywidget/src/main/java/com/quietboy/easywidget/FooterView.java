package com.quietboy.easywidget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO
 *
 * @Author JiangYY
 * @Date 2017/3/13
 */

public class FooterView extends LinearLayout {

    private TextView tvContent;
    private String message = "";
    private OnLoadListener onLoadListener;

    public FooterView(Context context) {
        this(context, null);
    }

    public FooterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setPadding(20, 20, 20, 20);
        initView();
    }

    private void initView() {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tvContent = new TextView(getContext());
        tvContent.setLayoutParams(params);
        tvContent.setText(message);
        tvContent.setPadding(10, 10, 10, 10);
        addView(tvContent);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isProgress();
                if (onLoadListener != null)
                    onLoadListener.onLoading();
            }
        });

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        tvContent.setText(message);
    }

    public void isProgress() {
        setMessage("正在加载...");
        setEnabled(false);
    }

    public void isFinish() {
        setMessage("已加载全部");
        setEnabled(false);
    }

    public void isHasMore() {
        setMessage("加载更多");
        setEnabled(true);
    }

    public void isHide() {
        setMessage("");
        setEnabled(false);
    }

    public void isLoadWrror(String message) {
        setMessage(message + "，点击重试");
        setEnabled(true);
    }

    public interface OnLoadListener {
        void onLoading();
    }

    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }
}
