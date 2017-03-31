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
    private int mIcon;
    private int mColor;
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

    //绘制background和stroke。
    private void setStyles(Context context) {

        //将background从RippleDrawable状态转换成GradientDrawable,
        // 否则不设置背景就会报错RippleDrawable cannot be cast to android.graphics.drawable.GradientDrawable
        setBackgroundResource(R.drawable.round_corner);

        GradientDrawable drawable = (GradientDrawable) getBackground().mutate();
        drawable.setColor(mColor);
        drawable.setCornerRadius(0);
        if (mRoundedCorner) {
            drawable.setCornerRadius(mRoundedCornerRadius);
        }
        if (mTransparentBackground) {
            drawable.setColor(Color.TRANSPARENT);
            drawable.setStroke(4, mColor);
        }
        //将background分离出来，这样在onDraw中就可以设置文字和icon的位置
        drawable.invalidateSelf();
        setPadding((int) Utils.convertDpToPixel(30, context), 0, (int) Utils.convertDpToPixel(30, context), 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //下面三行的作用就是将button非自定义的部分向左移动shift大小，
        // 因为上面已经把背景剥离了出去，所以移动的只有text的值
        int shift = (mIconSize + mIconPadding) / 2;
        canvas.save();
        canvas.translate(shift, 0);
        super.onDraw(canvas);
        //下面的代码就是绘制我们自定义的icon部分，
        float textWidth = getPaint().measureText((String) getText());
        int left = (int) ((getWidth() / 2f) - (textWidth / 2f) - mIconSize - mIconPadding);
        int top = getHeight() / 2 - mIconSize / 2;
        if (!mIconCenterAligned) {
            left = 0;
        }
        Rect desRect = new Rect(left, top, left + mIconSize, top + mIconSize);
        if (desRect != null) {
            Log.e("小子++++++++4", "desRect!=null");
        }
        canvas.drawBitmap(Icon, mRect, desRect, mPaint);//这里决定了画什么
        canvas.restore();//是和save一起使用的
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        //从attrs.xml中获取到自定义的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoginButtonView);
        setDefaultTypedArray(context);
        //判断是否有text
        if (attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "text") != null) {
            mIconPadding = (int) Utils.convertDpToPixel(20, context);
        }
        //从布局中获取自定义属性的值
        for (int i = 0; i < array.getIndexCount(); ++i) {
            int attr = array.getIndex(i);
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
            } else if (attr == R.styleable.LoginButtonView_color) {
                mColor = array.getColor(attr, Color.BLUE);
            } else if (attr == R.styleable.LoginButtonView_icon) {
                mIcon = array.getResourceId(attr, R.drawable.fb_logo);
            }
        }
        array.recycle();
        if (mIcon == 0) {
            Icon = Utils.drawableToBitmap(getResources().getDrawable(R.drawable.fb_logo));
        } else {
            Icon = Utils.drawableToBitmap(getResources().getDrawable(mIcon));
        }
        mPaint = new Paint();
    }

    private void setDefaultTypedArray(Context context) {
        Icon = Utils.drawableToBitmap(getResources().getDrawable(R.drawable.fb_logo));
        mIconSize = (int) Utils.convertDpToPixel(30, context);
        mIconCenterAligned = true;
        mRoundedCorner = true;
        mRoundedCornerRadius = (int) Utils.convertDpToPixel(40, context);
        mTransparentBackground = false;

    }

}
