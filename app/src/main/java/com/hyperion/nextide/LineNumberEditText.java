package com.hyperion.nextide;

import android.widget.*;
import android.util.*;
import android.content.*;
import android.graphics.*;

/**
 * the simple implementation of an EditText where each line is numbered on the left
 */
public class LineNumberEditText extends EditText {

    /** whether to set the lines visible or not */
    private boolean lineNumberVisible = true;

    private Rect rect;
    private Paint paint;
    private final Context context;
    /** the gap between the line number and the left margin of the text */
    private int lineNumberMarginGap = 2;

    /**
     *the difference between line text size and the normal text size.
     * line text size is preferabl smaller than the normal text size
     */
    protected int LINE_NUMBER_TEXTSIZE_GAP = 2;

    public LineNumberEditText(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public LineNumberEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LineNumberEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context=context;
        init();
    }

    private void init()
    {
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(getTextSize() - LINE_NUMBER_TEXTSIZE_GAP);
    }

    public void setLineNumberMarginGap(int lineNumberMarginGap) {
        this.lineNumberMarginGap = lineNumberMarginGap;
    }

    public int getLineNumberMarginGap() {
        return lineNumberMarginGap;
    }

    public void setLineNumberVisible(boolean lineNumberVisible) {
        this.lineNumberVisible = lineNumberVisible;
    }

    public boolean isLineNumberVisible() {
        return lineNumberVisible;
    }

    public void setLineNumberTextColor(int textColor) {
        paint.setColor(textColor);
    }

    public int getLineNumberTextColor() {
        return  paint.getColor();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        
        
        if (lineNumberVisible) {

//set the size in case it changed after the last update
            paint.setTextSize(getTextSize() - LINE_NUMBER_TEXTSIZE_GAP);

            int baseLine = getBaseline();
            String t = "";
            for (int i = 0; i < getLineCount(); i++) {
                t = "" + (i + 1);
                canvas.drawText(t, rect.left, baseLine, paint);
                baseLine += getLineHeight();
            }

// set padding again, adjusting only the left padding
            setPadding((int)paint.measureText(t) + lineNumberMarginGap, getPaddingTop(),
                       getPaddingRight(), getPaddingBottom());
        } 
        
        // for setting edittext start padding
        if(getLineCount()<100)
        {
            setPadding(140,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(getLineCount()>99 && getLineCount()<1000)
        {
            setPadding(140,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(getLineCount()>999 && getLineCount()<10000)
        {
            setPadding(140,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(getLineCount()>9999 && getLineCount()<100000)
        {
            setPadding(140,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }

        super.onDraw(canvas);
    }
    

}
