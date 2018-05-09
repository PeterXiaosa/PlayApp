package com.example.peter.playapp.view;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;


/**
 * Created by chexiaopeng on 2017/12/8.
 * 自适应TextV
 */

public class EsFixTextView extends android.support.v7.widget.AppCompatTextView{

    private final static float DEFAULT_MIN_TEXT_SIZE = 28;
    private final static float DEFAULT_MAX_TEXT_SIZE = 50;
    private float minTextSize;
    private float maxTextSize;

    private Paint mTextPaint;
    private boolean mIsNewLine;
    public EsFixTextView(Context context) {
        super(context);
        initialise();
    }

    public EsFixTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public EsFixTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise();
    }

    public void setIsNewLine(boolean newLine){
        mIsNewLine = newLine;
    }

    private void initialise() {
        mTextPaint = new Paint(this.getPaint());
        maxTextSize = this.getTextSize();
        if (maxTextSize <= DEFAULT_MIN_TEXT_SIZE) {
            maxTextSize = DEFAULT_MAX_TEXT_SIZE;
        }
        mIsNewLine = false;
        minTextSize = DEFAULT_MIN_TEXT_SIZE;
    }

    private void refitText(String text, int textWidth, int textHeight){
        initialise();
        if (textWidth > 0) {

            boolean isAddLine = false;
            int availableWidth = textWidth - this.getPaddingLeft() -
                    this.getPaddingRight();
            int availableHeight = textHeight - this.getPaddingTop() - this.getPaddingBottom();



            float trySize = maxTextSize;
            mTextPaint.setTextSize(trySize);
            if(mIsNewLine){
                //先判断最新字体能否显示下
                boolean isNewLine = false;
                Rect  rect2 = new Rect();
                mTextPaint.getTextBounds(text, 0, text.length(), rect2);
                int height2 = rect2.height();
                while ((trySize > minTextSize) && ((mTextPaint.measureText(text) > availableWidth) || (height2 > availableHeight))) {
                    trySize--;
                    if (trySize <= minTextSize) {
                        trySize = maxTextSize;
                        isNewLine = true;
                        break;
                    }
                    mTextPaint.setTextSize(trySize);

                    mTextPaint.getTextBounds(text, 0, text.length(), rect2);
                    height2 = rect2.height();
                }
                if(isNewLine){
                    Rect  rect3 = new Rect();
                    mTextPaint.getTextBounds(text, 0, text.length(), rect2);
                    int height3 = rect3.height();
                    while ((trySize > minTextSize) && ((mTextPaint.measureText(text) > availableWidth * 1.5) || (height3 > availableHeight * 1.5))){
                        trySize--;
                        mTextPaint.setTextSize(trySize);

                        mTextPaint.getTextBounds(text, 0, text.length(), rect3);
                        height3 = rect3.height();
                    }
                    setLines(2);
                }
                setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);
            }else{
                Rect  rect1 = new Rect();
                mTextPaint.getTextBounds(text, 0, text.length(), rect1);
                int height1 = rect1.height();
                while ((trySize > minTextSize) && ((mTextPaint.measureText(text) > availableWidth) || (height1> availableHeight))) {
                    trySize--;
                    if (trySize <= minTextSize) {
                        trySize = minTextSize;
                        break;
                    }
                    mTextPaint.setTextSize(trySize);

                    mTextPaint.getTextBounds(text, 0, text.length(), rect1);
                    height1 = rect1.height();
                }
                setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);
            }
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
//        refitText(text.toString(), this.getWidth());
        refitText(text.toString(), this.getWidth(), this.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refitText(this.getText().toString(), w, h);
    }
}

