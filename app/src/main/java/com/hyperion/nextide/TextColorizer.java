package com.hyperion.nextide;

import java.util.HashSet;
import java.util.Set;
import android.graphics.Color;
import android.util.Log;
import android.graphics.Typeface;

/**
 * 
 * @author **** Shamus Murray ****
 * @version **** 2/3/15 **** *
 */
public class TextColorizer extends MainActivity {

	// Create new hashSet.
	Set<String> reservedWordsHashSet = new HashSet<String>();

	// create ints used to hold indexes and lengths in process text
	int startComment;
	int endComment;
	int lengthWord;
	int startComment2;
	int endComment2;
	int lengthWord2;
	int startWord;
	int endWord;
	int wordLength;

	/**
	 * constructor, which presently has nothing to initialize
	 *
	 */
	public TextColorizer() {

		// go through reservedWords array and add each to hashset
		for (int i = 0; i < reservedWords.length; i++) {
			reservedWordsHashSet.add(reservedWords[i]);
		}

	}

   

	/**
	 * Color's the text in the object. This is an example of how to access the
	 * text and set color information.
	 * 
	 * @param colorableObject
	 *            the object to color
	 */
	public void processText(ColorableText colorableObject) {

		// The text in the window pane
		String theText = colorableObject.getText();

		// the length of that text
		int length = theText.length();

		// first, set everything to black
		colorableObject.setColor(0, length, Color.BLACK);

		// append blank character to beginning and end to avoid out of bounds
		// errors
		theText = " " + theText + " ";

		// for each to get all words in set
		for (String rWord : reservedWordsHashSet) {
			// initialize to 0 for each word
			startWord = 0;
			endWord = 0;
			wordLength = 0;
			// while there are still indexes
			while (startWord >= 0) {
				// get index and length of word, compute end index
				startWord = theText.indexOf(rWord, startWord + 1);
				wordLength = rWord.length();
				endWord = wordLength + startWord;
				// check for index errors
				if (!(startWord < 0 || startWord > theText.length())) {
					Log.i("word s", Integer.toString(startWord));
					Log.i("word e", Integer.toString(endWord));
					// make sure nothing is attached to word, color red if not
					if (!(theText.substring(startWord - 1, endWord + 1).trim()
							.length() > wordLength)) {
						colorableObject.setColor(startWord - 1, wordLength,
								Color.MAGENTA);
					}
				}
			}
		}

		// initialize all values to 0
		startComment = 0;
		endComment = 0;
		lengthWord = 0;
		// while there are still indexes
		while (startComment >= 0) {
			// get next index
			startComment = theText.indexOf("//", startComment + 1);
			// if index could be found
			if (startComment != -1) {
				// start at where // was found, go through each character until
				// \n is found. Assign that index to endComment
				for (int a = startComment; a < theText.length(); a++) {
					endComment = a + 1;
					// logcat values
					Log.i("startComment", Integer.toString(startComment));
					Log.i("endComment", Integer.toString(endComment));
					// get snippet of text from start to end index
					String test = theText.substring(startComment + 1,
							endComment);
					if (!(test.contains("\n"))) {
						// get word length
						lengthWord = endComment - startComment;
						// log length
						Log.i("commentLength", Integer.toString(lengthWord));
						// if length and endcomment are bigger than the text
						// length + 1
						// then fix length by adjusting with currentLength.
						// color blue after this is done, else just color blue
						if ((lengthWord + endComment) > (theText.length() + 1)) {
							lengthWord = theText.length() - startComment - 1;
							colorableObject.setColor(startComment - 1,
									lengthWord, Color.GREEN);
						} else {
							colorableObject.setColor(startComment - 1,
									lengthWord, Color.GREEN);
						}
					}
				}
			}
		}

		// initialize all values to 0
		startComment2 = 0;
		endComment2 = 0;
		lengthWord2 = 0;
		// while there are still indexes
		while (startComment2 >= 0) {
			// get next index
			startComment2 = theText.indexOf("/*", startComment2 + 1);
			// if index could be found
			if (startComment2 != -1) {
				// start at where comment symbol was found, go through each
				// character until closing comment symbol is found
				// assign that index to endComment
				for (int a = startComment2; a < theText.length(); a++) {
					endComment2 = a + 1;
					// log indexes
					Log.i("startComment2", Integer.toString(startComment2));
					Log.i("endComment2", Integer.toString(endComment2));
					// get snippet of text from start to end index
					String test = theText.substring(startComment2 + 1,
							endComment2);
					// if ending symbol found, calculate length from indexes and
					// color blue
					if (!(test.contains("*/"))) {

						lengthWord2 = endComment2 - startComment2;
						// log length
						Log.i("commentLength2", Integer.toString(lengthWord2));
						colorableObject.setColor(startComment2 - 1,
								lengthWord2 + 1, Color.GREEN);
					}
				}
			}
		}
	}
}
