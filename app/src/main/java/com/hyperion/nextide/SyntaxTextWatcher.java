package com.hyperion.nextide;

import java.util.Vector;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.graphics.Typeface;
import android.content.res.AssetManager;

public class SyntaxTextWatcher implements TextWatcher, ColorableText {

	/**
	 * stores a references to the editText as an Editable so we can add spans
	 * when setColor is called
	 */
	private Editable editText = null;

	/**
	 * we store a copy of the previous text seen to avoid an infinite loop that's
	 * caused when the afterTextChanged() event handler makes changes to the
	 * text (thus causing more text changed events).
	 */
	private String prevText = "";

	/**
	 * used to call the student's code. If the student modifies his/her
	 * constructor to take arguments, this is where the compiler will whine.
	 */
	private TextColorizer colorizer = new TextColorizer();

	/**
	 * whenever the content of the EditText changes, this method gets called.
	 * When that happens, the colorizer is asked to re-color the text it
	 * contains
	 */
	@Override
	public void afterTextChanged(Editable editText) {
		// record the current reference for later use
		this.editText = editText;

		// If the content of the EditText hasn't changed since the last time,
		// the event is ignored
		String currText = editText.toString();
		if (this.prevText.equals(currText)) 
			return;
		else 
			this.prevText = currText;
          
        
		// remove all existing spans in the text
		// (NOTE: editText.clearSpans() was too slow!)
		ForegroundColorSpan[] toRemoveSpans = editText.getSpans(0, currText.length(), ForegroundColorSpan.class);
		for (int i = 0; i < toRemoveSpans.length; i++)
		{
		    editText.removeSpan(toRemoveSpans[i]);
		}


		// Set the colorizer to work adding new spans
		colorizer.processText(this);

	}

    private AssetManager getAssets()
    {

        return null;
    }// afterTextChanged

	/** We don't care about these other two events below */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	/**
	 * the student calls this method to create a span. If invalid input is given
	 * it is corrected and the color is changed to a notable shade of magenta
	 */
	@Override
	public void setColor(int first, int length, int color) {
		// this should never be true but just in case
		if (this.editText == null)
			return;

		// check for illegal input and correct
		boolean valid = true;
		if (first < 0) {
			first = 0;
			valid = false;
		}
		if (length <= 0) {
			length = 1;
			valid = false;
		}
		
		int currLen = this.editText.toString().length();
		if (first + length > currLen) {
			length = currLen - first;
			valid = false;
		}

		// if illegal input was found, change the color to magenta
		if (!valid) {
			color = Color.MAGENTA;
		}

		editText.setSpan(new ForegroundColorSpan(color), first, first + length,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

	}

	/** @return the current text in the view */
	@Override
	public String getText() {
		return this.editText.toString();
	}

}
