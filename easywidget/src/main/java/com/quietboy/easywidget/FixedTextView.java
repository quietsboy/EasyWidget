package com.quietboy.easywidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * TODO
 *
 * @Author JiangYY
 * @Date 2017/3/21
 */

public class FixedTextView extends AppCompatTextView {

    private String fixedText;
    private Integer fixedWeight;

    public FixedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FixedTextView);
        fixedText = typedArray.getString(typedArray.getIndex(R.styleable.FixedTextView_fixedString));
        fixedWeight = typedArray.getInteger(typedArray.getIndex(R.styleable.FixedTextView_fixedWeights), 0);
        setFixedText(fixedText);
    }

    public void setFixedText(String text) {

        fixedText = text;
        String count = "";
        if (fixedText.length() < fixedWeight) {
            for (int index = 0; index < fixedWeight - fixedText.length(); index++) {
                count += "\\u";
            }
        }
        int left = (int) getPaint().measureText(fixedText + count) + getPaddingLeft() + 30;
        setPadding(left, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(fixedText)) {
            TextPaint paint = getPaint();
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            paint.setColor(ContextCompat.getColor(getContext(), android.R.color.black));
            canvas.drawText(fixedText, 30, getMeasuredHeight() / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent, paint);
        }
    }

}