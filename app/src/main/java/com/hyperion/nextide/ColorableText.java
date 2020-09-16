package com.hyperion.nextide;
import android.graphics.Typeface;

/* An interface that represents colorable text */
public interface ColorableText
{
    
	
	/**
     * Sets the text color for a give region of our document.
     * 
     * @param first the position of the first character to color
     * @param length the number of characters to color with that color
     * @param color the with which to color the characters in the region
     * 
     * This method checks 'first' and 'length' for being out of bounds. If 'first' is negative,
     * it treats it as being zero.  If 'length' is large enough that it would color beyond the
     * end of the string, the effect is that the string is colored through the end of the
     * string.
     */
	public void setColor(int first, int length, int color);
	
    /**
     * Get the object's text.
     * 
     * @return The text associated with the object's document.
     */
	public String getText();
}
