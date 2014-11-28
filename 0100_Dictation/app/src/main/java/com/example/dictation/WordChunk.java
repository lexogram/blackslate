package com.example.dictation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A word chunk is a single word, possibly followed by punctuation, and ending
 * with a space, a hard line break or EOF. It can be spoken before it is typed,
 * complete with trailing punctuation, but without mention of a space. It can
 * also be spoken after all the word characters have been typed (including
 * hyphens)
 *
 * It breaks the word down into individual LetterCells, SymbolCells and
 * SymbolCellGroups.
 */

public class WordChunk {

    private String wordChunk;
    private int index = 0;
    private final Pattern PATTERN = Pattern.compile("(ch|.)");
    private Matcher matcher;

    public WordChunk(String wordChunk) {
        this.wordChunk = wordChunk; // "backache"
        matcher = PATTERN.matcher(wordChunk);
    }

    public Cell getNextCell() {
        Cell cell = null;
        String substring = null;
        boolean found = matcher.find();

        if (found) {
            substring = matcher.group();
            cell = new Cell(substring);
        }

        return cell;
    }

    public String getWordChunk() {
        return wordChunk;
    }
}
