package com.cilenco.ui.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.cilenco.testApplication.R;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class WholeRippleView extends LinearLayout implements AnimatorUpdateListener {
    private static final int DEFAULT_REPEAT_COUNT = 1;

    private static final float DEFAULT_ALPHA = 0.26f;
    private static final float ALPHA_MAX = 255;

    private Paint paint;
    private PointF position;
    private ValueAnimator animator;

    private float rippleRadius;
    private float animationValue;

    private int rippleDuration;
    private int repeatCount;

    private float rippleAlpha;
    private int rippleColor;

    public WholeRippleView(Context context) {
        this(context, null);
    }

    public WholeRippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WholeRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        paint = new Paint(ANTI_ALIAS_FLAG);
        position = new PointF();

        rippleDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        rippleColor = ContextCompat.getColor(context, R.color.ColorPrimary);

        repeatCount = DEFAULT_REPEAT_COUNT;
        rippleAlpha = DEFAULT_ALPHA;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        float x = position.x;
        float y = position.y;

        float paintAlpha = ALPHA_MAX * rippleAlpha;

        int alpha = Math.round(paintAlpha * (1f - animationValue));

        paint.setColor(rippleColor);
        paint.setAlpha(alpha);

        canvas.drawCircle(x, y, animationValue * rippleRadius, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.rippleRadius = Math.max(w, h);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animator) {
        animationValue = (float) animator.getAnimatedValue();
        invalidate(); // Force the Layout to redraw itself
    }

    private void createAnimation(float x, float y) {
        position.set(x, y);

        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(rippleDuration);
        animator.setRepeatCount(repeatCount);
        animator.addUpdateListener(this);

        animator.start();
    }

    /** Launch Ripple animation for the current view from the center */
    public void animateRipple() {
        float x = getMeasuredWidth() / 2;
        float y = getMeasuredHeight() / 2;

        createAnimation(x, y);
    }

    /** Launch ripple animation for the current view with a MotionEvent
     ** @param event MotionEvent registered by the Ripple gesture listener */
    public void animateRipple(MotionEvent event) {
        createAnimation(event.getX(), event.getY());
    }

    /** Launch ripple animation for the current view centered at x and y position
     ** @param x Horizontal position of the ripple center
     ** @param y Vertical position of the ripple center */
    public void animateRipple(float x, float y) {
        createAnimation(x, y);
    }

    /** Stop ripple animation and reset the View */
    public void stopRipple() {
        if(animator != null) animator.cancel();

        animationValue = 0f;
        clearAnimation();
        invalidate();
    }

    /** Set ripple alpha, default is #FFFFFF
     ** @param rippleAlpha New ripple alpha */
    public void setRippleAlpha(float rippleAlpha) {
        this.rippleAlpha = rippleAlpha;
    }

    /** Gets the alpha of the ripple animation. The default duration is 26 percent.
     ** @return The alpha of the animation, in percentage. */
    public float getRippleAlpha() {
        return rippleAlpha;
    }

    /** Set ripple color, default is PrimaryColor
     ** @param rippleColor New ripple color */
    public void setRippleColor(@ColorInt int rippleColor) {
        this.rippleColor = rippleColor;
    }

    /** Set ripple color, default is PrimaryColor
     ** @param rippleColorRes New ripple color resource */
    public void setRippleColorRes(@ColorRes int rippleColorRes) {
        Resources res = getResources();
        this.rippleColor = res.getColor(rippleColorRes);
    }

    /** Gets the color of the ripple animation. The default is PrimaryColor.
     ** @return The color of the ripple animation. */
    public int getRippleColor() {
        return rippleColor;
    }

    /** Sets the length of the ripple animation. The default duration is 300 milliseconds.
     ** @param rippleDuration The length of the ripple animation, in milliseconds.
     ** This value cannot be negative.  */
    public void setRippleDuration(int rippleDuration) {
        this.rippleDuration = rippleDuration;
    }

    /** Gets the length of the ripple animation. The default duration is 300 milliseconds.
     ** @return The length of the ripple animation, in milliseconds. */
    public int getRippleDuration() {
        return rippleDuration;
    }

    /** Sets how many times the animation should be repeated. If the repeat
     ** count is 0, the animation is never repeated. If the repeat count is
     ** greater than 0 or {@link android.animation.ValueAnimator#INFINITE},
     ** the repeat mode will be taken into account. The repeat count is 1 by default.
     *
     ** @param repeatCount The number of times the animation should be repeated */
    public void setRippleRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    /** Defines how many times the animation should repeat. The default value is 1.
     ** @return the number of times the animation should repeat, or {@link android.animation.ValueAnimator#INFINITE} */
    public int getRippleRepeatCount() {
        return repeatCount;
    }
}
