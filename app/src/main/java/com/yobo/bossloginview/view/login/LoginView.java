package com.yobo.bossloginview.view.login;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;

import com.yobo.bossloginview.R;


/**
 * Created by yoBo on 2017/3/30.
 */

public class LoginView extends android.support.v7.widget.AppCompatButton {

    private Bitmap Icon;
    private Paint mPaint;
    private Rect mRect;

    private int mIconPadding;
    private int mIconSize;
    private int mRoundedCornerRadius;
    private boolean mIconCenterAligned;
    private boolean mTransparentBackground;
    private boolean mRoundedCorner;

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //为什么必须在这实例化
        initAttrs(context, attrs);
        setStyles(context);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        setStyles(context);

    }

    private void setStyles(Context context) {

        setTextColor(Color.WHITE);
        setBackgroundResource(R.drawable.round_corner);//必须存在,否则.RippleDrawable cannot be cast to android.graphics.drawable.GradientDrawable
        GradientDrawable drawable = (GradientDrawable) getBackground().mutate();
        drawable.setColor(Color.BLUE);
        drawable.setCornerRadius(0);
        if (mRoundedCorner) {
            drawable.setCornerRadius(mRoundedCornerRadius);
        }
        if (mTransparentBackground) {
            drawable.setColor(Color.TRANSPARENT);
            drawable.setStroke(4, Color.BLUE);
        }
        drawable.invalidateSelf();

        //setPadding((int)Utils.convertDpToPixel(30,context),0,(int)Utils.convertDpToPixel(30,context),0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //将icon和文字移到中间
        //说明cavas只代表icon和文字，背景由setstyle管理
        int shift = (mIconSize + mIconPadding) / 2;
        canvas.save();
        canvas.translate(shift, 0);

        super.onDraw(canvas);

        float textWidth = getPaint().measureText((String) getText());
        int left = (int) ((getWidth() / 2f) - (textWidth / 2f) - mIconSize - mIconPadding);
        int top = getHeight() / 2 - mIconSize / 2;
        if (!mIconCenterAligned) {
            left = 0;
        }
        //这个矩形好像只是绘制了icon的部分。
        Rect desRect = new Rect(left, top, left + mIconSize, top + mIconSize);
        if (desRect!=null){
            Log.e("小子++++++++4","desRect!=null");
        }
        canvas.drawBitmap(Icon, mRect, desRect, mPaint);//这里决定了画什么
       // canvas.restore();是和save一起使用的
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        Log.e("小子------------1","1");
        if (context!=null){
            Log.e("姑娘------------0","1");
        }else{
            Log.e("姑娘------------0","2");
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoginButtonView);

        if (array!=null){
            Log.e("姑娘------------0","3");
        }else
        {
            Log.e("姑娘------------0","4");
        }

        Log.e("小子------------1","2");
        setDefaultTypedArray(context);
        Log.e("小子------------1","3");
        if(attrs.getAttributeValue("http://schemas.android.com/apk/res/android","text")!=null){
            mIconPadding = (int)Utils.convertDpToPixel(20,context);
        }
        Log.e("小子------------1","4");
        for (int i = 0; i < array.getIndexCount(); ++i) {
            int attr = array.getIndex(i);
            Log.e("小子------------1","5");
            if (attr == R.styleable.LoginButtonView_iconSize) {
                mIconSize = array.getDimensionPixelSize(attr, (int) Utils.convertDpToPixel(20, context));
            } else if (attr == R.styleable.LoginButtonView_iconPadding) {
                mIconPadding = array.getDimensionPixelSize(attr, (int) Utils.convertDpToPixel(20, context));
            } else if (attr == R.styleable.LoginButtonView_roundedCornerRadius) {
                mRoundedCornerRadius = array.getDimensionPixelSize(attr, (int) Utils.convertDpToPixel(40, context));
            } else if (attr == R.styleable.LoginButtonView_roundedCorner) {
                mRoundedCorner = array.getBoolean(attr, false);
            } else if (attr == R.styleable.LoginButtonView_iconCenterAligned) {
                mIconCenterAligned = array.getBoolean(attr, true);
            } else if (attr == R.styleable.LoginButtonView_transparentBackground) {
                mTransparentBackground = array.getBoolean(attr, false);
            }
            Log.e("小子------------1","6");
        }
        array.recycle();

        if (Icon != null) {
            Log.e("小子++++++++1","Icon!=null");
            mPaint = new Paint();

            //mRect = new Rect(0, 0, Icon.getWidth(), Icon.getHeight());
        }else {
            Log.e("小子++++++++2","Icon==null");
        }
    }

    private void setDefaultTypedArray(Context context) {
        Icon = Utils.drawableToBitmap(getResources().getDrawable(R.drawable.fb_logo));
        mIconSize = (int) Utils.convertDpToPixel(30, context);
        mIconCenterAligned = false;
        mRoundedCorner = true;
        mRoundedCornerRadius = (int) Utils.convertDpToPixel(40, context);
        mTransparentBackground = true;

    }

}
