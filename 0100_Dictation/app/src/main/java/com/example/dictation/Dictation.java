package com.example.dictation;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Dictation object receives a chunk of text to dictate. This may consist of
 * formatted headers, paragraphs, sentences, words and individual characters.
 * The Dictation object does not provide any direct interaction with the user.
 * It breaks the text chunk down into sentences, and delegates interaction to
 *  sentence objects.
 *
 *  The end of a "sentence" is identified by:
 *  - A hard line break, such as at the end of a title or header
 *  - EOF
 *  - A period, question mark, exclamation mark, possibly followed by
 *    quotation marks or parentheses, followed by a space, hard line break or
 *    EOF
 * Other languages may have different definitions of a sentence, so this class
 * is only considered valid for English.
 */
public class Dictation implements StateArray {

    private HashMap<Integer, State> states = new HashMap<Integer, State>();
    private int index;
    private State current;

    //private final Pattern PATTERN = Pattern.compile("\\b(\\w+?\\b[\\. ]?)");
    private final Pattern PATTERN = Pattern.compile("(ch|.)");
    private Matcher matcher;


    public Dictation(String input) {
        matcher = PATTERN.matcher(input);
        prepareStates();
    }

    @Override
    public State getState(int index) {
        return null;
    }

    @Override
    public void setNextState() {
        if (index < states.size()) {
            current = states.get(index++);
            current.start(this);
        } else {
            Log.d("DICT", "Dictation complete");
        }
    }

    private void prepareStates() {
        String chunk;
        State state;
        int index;

        while (matcher.find()) {
            chunk = matcher.group();
            Log.d("regex", "*" + chunk + "*");

            index = states.size();
            state = new Cell(chunk);
            states.put(index, state);
        }
    }
}
