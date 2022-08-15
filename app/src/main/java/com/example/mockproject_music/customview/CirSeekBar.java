package com.example.mockproject_music.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import com.example.mockproject_music.R;

public class CirSeekBar extends View {

    private final float mRadius;
    private Paint mPaintBackGround;
    private Paint mPaintProgress;
    private Paint mPaintThumb;
    private final float mBackgroundWidth;
    private final float mCircleWidth;
    private final float mProgressWidth;
    private final float mIndicatorRadius;

    private float mMaxX;
    private float mMaxY;
    private float cx;
    private float cy;
    private float mPos;
    private float x1, y1;
    private boolean mCanSeek;
    private RectF mBackgroundRing;
    private RectF mBackgroundCircleThumb;
    private RectF mProgressRing;
    private int mPadding;
    private CallBackProgress mCallBack;
    private Context mContext;

    public CirSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mBackgroundWidth = 5;
        mCircleWidth = 20;
        mRadius = 450 / 2f;
        mIndicatorRadius = mProgressWidth = 10;
        mPadding = 40;
        mCanSeek = false;
        initBaseDimes();
        initPaint();
    }

    public void setCallBack(CallBackProgress callBack) {
        this.mCallBack = callBack;
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) Math.ceil(mMaxX + mPadding), (int) Math.ceil(mMaxY + mPadding));
    }

    public void setData(float progress) {
        mPos = progress;
        handleData();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintBackGround(canvas);
        paintProgress(canvas);
        drawCircleMini(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                float distanceTouchToCenter = (float) Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));

                //near circle
                if (distanceTouchToCenter > mRadius - mBackgroundWidth * 2
                        && distanceTouchToCenter < mRadius + mBackgroundWidth * 2) {
                    Log.d("ptit", "onTouchEvent: go inside");
                    mCanSeek = true;
                } else {
                    Log.d("ptit", "onTouchEvent: go outside");
                    mCanSeek = false;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if (mCanSeek) {
                    mPos = XYtoDegree(event.getX(), event.getY());
                    handleData();
                    mCallBack.onSeek(mPos);
                }
                break;
        }
        invalidate();
        return true;
    }

    private void initBaseDimes() {
        cx = mRadius + (mPadding / 2);
        cy = mRadius + (mPadding / 2);
        x1 = cx;
        y1 = mProgressWidth / 2;
        mMaxX = mRadius * 2;
        mMaxY = mRadius * 2;
        mPos = 240;

        handleData();
    }


    private void handleData() {

        float sinValue;
        float cosValue;
        float gocLech = 0;
        if (mPos <= 90) {
            gocLech = 90 - mPos;
            cosValue = (float) Math.cos(gocLech / 180 * Math.PI);
            sinValue = (float) Math.sin(gocLech / 180 * Math.PI);

            x1 = cx + Math.abs(cosValue * mRadius);
            y1 = cy - Math.abs(sinValue * mRadius);

        } else if (mPos <= 180) {
            Log.d("ptit", "vao day: ");
            gocLech = mPos - 90;

            cosValue = (float) Math.cos(gocLech / 180 * Math.PI);
            sinValue = (float) Math.sin(gocLech / 180 * Math.PI);

            x1 = cx + Math.abs(cosValue * mRadius);
            y1 = cy + Math.abs(sinValue * mRadius);
        } else if (mPos <= 270) {
            gocLech = 270 - mPos;


            cosValue = (float) Math.cos(gocLech / 180 * Math.PI);
            sinValue = (float) Math.sin(gocLech / 180 * Math.PI);

            x1 = cx - Math.abs(cosValue * mRadius);
            y1 = cy + Math.abs(sinValue * mRadius);
        } else {
            gocLech = mPos - 270;
            cosValue = (float) Math.cos(gocLech / 180 * Math.PI);
            sinValue = (float) Math.sin(gocLech / 180 * Math.PI);

            x1 = cx - Math.abs(cosValue * mRadius);
            y1 = cy - Math.abs(sinValue * mRadius);

        }

    }


    private void drawCircleMini(Canvas canvas) {
        mBackgroundCircleThumb.set(x1 - mCircleWidth, y1 - mCircleWidth, x1 + mCircleWidth, y1 + mCircleWidth);
        canvas.drawArc(mBackgroundCircleThumb, 0, 360, true, mPaintThumb);
    }

    private void initPaint() {
        float endProgressRect = 2 * mRadius - mBackgroundWidth;
        float startBackgroundRect = mBackgroundWidth;
        mProgressRing = new RectF(
                startBackgroundRect + (int) (mPadding / 2),
                startBackgroundRect + (int) (mPadding / 2),
                endProgressRect + (int) (mPadding / 2),
                endProgressRect + (int) (mPadding / 2)
        );
        mBackgroundRing = new RectF(
                startBackgroundRect + (int) (mPadding / 2),
                startBackgroundRect + (int) (mPadding / 2),
                endProgressRect + (int) (mPadding / 2),
                endProgressRect + (int) (mPadding / 2)
        );

        mBackgroundCircleThumb = new RectF(
                x1 - mCircleWidth, y1 - mCircleWidth, x1 + mCircleWidth, y1 + mCircleWidth
        );

        mPaintThumb = new Paint();
        mPaintThumb.setAntiAlias(true);
        mPaintThumb.setStyle(Style.FILL_AND_STROKE);
        mPaintThumb.setColor(ContextCompat.getColor(mContext, R.color.color_bg_progress_and_thumb));


        mPaintBackGround = new Paint();
        mPaintBackGround.setAntiAlias(true);
        mPaintBackGround.setStyle(Style.STROKE);
        mPaintBackGround.setStrokeWidth(mBackgroundWidth);
        mPaintBackGround.setColor(ContextCompat.getColor(mContext, R.color.color_bg_circle));

        mPaintProgress = new Paint();
        mPaintProgress.setStyle(Style.STROKE);
        mPaintProgress.setStrokeWidth(10);
        mPaintProgress.setAntiAlias(true);
        mPaintProgress.setColor(ContextCompat.getColor(mContext, R.color.color_bg_progress_and_thumb));
    }

    private float XYtoDegree(float touchX, float touchY) {
        float x = cx - touchX;
        float y = cy - touchY;

        double v = Math.toDegrees(Math.atan(y / x));
        return (float) (x > 0 ? v + 270 : v + 90);
    }

    private void paintBackGround(Canvas canvas) {
        canvas.drawArc(mBackgroundRing, 0, 360, true, mPaintBackGround);
    }

    private void paintProgress(Canvas canvas) {
        canvas.drawArc(mProgressRing, 270, mPos, false, mPaintProgress);
    }

    public interface CallBackProgress {
        // call on action up to notify progress has changed
        void onSeek(float progress);
    }

}
