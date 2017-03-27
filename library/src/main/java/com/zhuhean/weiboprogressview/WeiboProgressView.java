package com.zhuhean.weiboprogressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class WeiboProgressView extends View {

    public static final String TAG = "WeChatProgressView";

    private static final int PROGRESS_START_ANGEL = -90;
    private static final int PROGRESS_SWEEP_ANGEL = 0;

    private static final int RING_START_ANGEL = 0;
    private static final int RING_SWEEP_ANGEL = 360;

    private static final int DEFAULT_WIDTH_IN_DP = 56;
    private static final int DEFAULT_PADDING_IN_DP = 4;
    private static final int DEFAULT_RING_WIDTH_IN_DP = 2;
    private static final int DEFAULT_COLOR = Color.parseColor("#BBFFFFFF");

    private static final int MAX_ANGEL = 360;
    private static final int MAX_PROGRESS = 100;

    @ColorInt
    private int color;
    private int progressPadding;
    private int ringWidth;

    private Paint ringPaint, progressPaint;
    private RectF ringBounds, progressBounds;

    private int startAngel = PROGRESS_START_ANGEL;
    private int sweepAngel = PROGRESS_SWEEP_ANGEL;
    private int previousSweepAngel = PROGRESS_SWEEP_ANGEL;

    public WeiboProgressView(Context context) {
        this(context, null);
    }

    public WeiboProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attribute) {
        if (attribute != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attribute, R.styleable.WeiboProgressView);
            color = typedArray.getColor(R.styleable.WeiboProgressView_wpv_color, DEFAULT_COLOR);
            progressPadding = typedArray.getDimensionPixelSize(R.styleable.WeiboProgressView_wpv_padding, dpToPx(DEFAULT_PADDING_IN_DP));
            ringWidth = typedArray.getDimensionPixelSize(R.styleable.WeiboProgressView_wpv_ring_width, dpToPx(DEFAULT_RING_WIDTH_IN_DP));
            typedArray.recycle();
        } else {
            color = DEFAULT_COLOR;
            progressPadding = dpToPx(DEFAULT_PADDING_IN_DP);
            ringWidth = dpToPx(DEFAULT_RING_WIDTH_IN_DP);
        }

        ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setColor(color);
        ringPaint.setStrokeWidth(ringWidth);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setColor(color);

        ringBounds = new RectF();
        progressBounds = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(ringBounds, RING_START_ANGEL, RING_SWEEP_ANGEL, true, ringPaint);
        canvas.drawArc(progressBounds, startAngel, sweepAngel, true, progressPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getProperSize(widthMeasureSpec);
        int height = getProperSize(heightMeasureSpec);
        ringBounds.set(ringWidth, ringWidth, width - ringWidth, height - ringWidth);
        int innerPadding = progressPadding + ringWidth;
        progressBounds.set(innerPadding, innerPadding, width - innerPadding, height - innerPadding);
        setMeasuredDimension(width, height);
    }

    private int getProperSize(int measureSpec) {
        int size = dpToPx(DEFAULT_WIDTH_IN_DP);
        if (MeasureSpec.getMode(measureSpec) == MeasureSpec.EXACTLY)
            size = MeasureSpec.getSize(measureSpec);
        return size;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setProgressPadding(int paddingInDp) {
        this.progressPadding = dpToPx(paddingInDp);
    }

    public void setProgress(double progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("The range of progress is [0,100]");
        }
        progress = progress / MAX_PROGRESS;
        sweepAngel = (int) (MAX_ANGEL * progress);
        if (sweepAngel != previousSweepAngel) {
            invalidate();
            previousSweepAngel = sweepAngel;
        }
    }

    private int dpToPx(int dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
        return (int) px;
    }

}
