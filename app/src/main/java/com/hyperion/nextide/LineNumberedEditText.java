package com.hyperion.nextide;

import android.widget.*;
import android.util.*;
import android.content.*;
import android.graphics.*;

/**
 * the simple implementation of an EditText where each line is numbered on the left
 */
public class LineNumberedEditText extends EditText
{
    private final Context context;
    private Rect rect;
    private Paint paint;

    public LineNumberedEditText(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public LineNumberedEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public LineNumberedEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context=context;
        init();
    }

    private void init()
    {
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(35);
        paint.setTypeface(Typeface.MONOSPACE);
    }


    
    @Override
    protected void onDraw(Canvas canvas) {

        int baseline;
        int lineCount = getLineCount();
        int lineNumber = 1;

        for (int i = 0; i < lineCount; ++i) 
        {
            baseline=getLineBounds(i, null);
            if (i == 0) 
            {
                canvas.drawText(""+lineNumber, rect.left,baseline, paint);
                ++lineNumber;
            } 
            else if (getText().charAt(getLayout().getLineStart(i) - 1) == '\n') 
            {
                canvas.drawText(""+lineNumber, rect.left,baseline, paint);
                ++lineNumber;
            }
        }   


// for setting edittext start padding
        if(lineCount<10)
        {
            setPadding(35,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(lineCount>9 && lineCount<99)
        {
            setPadding(55,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(lineCount>99 && lineCount<1000)
        {
            setPadding(75,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(lineCount>999 && lineCount<10000)
        {
            setPadding(95,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }
        else if(lineCount>9999 && lineCount<100000)
        {
            setPadding(115,getPaddingTop(),getPaddingRight(),getPaddingBottom());
        }

        super.onDraw(canvas);
    }
}
