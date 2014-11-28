package com.example.dictation;

import android.util.Log;

/**
 * A Cell is a single 6-dot pattern cell. It may express a single letter, a
 * group of letters or a symbolic mark (punctuation, abbreviation, ...)
 */
public class Cell implements State {

    private StateArray owner;
    private String chunk;
    private int pattern;


    public Cell(String chunk) {
        this.chunk = chunk;
        setPatternForChunk();
    }

    public String getChunk() {
        return chunk;
    }

    public int getPattern() {
        return pattern;
    }

    private void setPatternForChunk() {
        pattern = 0;

        if (chunk.equals("a")) {
            pattern = 1;
        } else if (chunk.equals("b")) {
            pattern = 3;
        } else if (chunk.equals("c")) {
            pattern = 9;
        } else if (chunk.equals("e")) {
            pattern = 17;
        } else if (chunk.equals("k")) {
            pattern = 5;
        } else if (chunk.equals("ch")) {
            pattern = 33;
        }
    }

    @Override
    public void start(StateArray owner) {
        this.owner = owner;
        Log.d("CELL", chunk + " " + pattern);
        owner.setNextState();
    }
}
