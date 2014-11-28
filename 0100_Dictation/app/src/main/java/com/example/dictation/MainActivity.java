package com.example.dictation;

import android.app.Activity;
import android.os.Bundle;


/**
 * The MainActivity creates an object to deal with each of the features of the
 * app, and then connects them together so that they can talk to each other.
 *
 * - DrawView:       to show when the user is touching dot areas
 * - TouchActivity:  to detect touches on the touch points, and space swipes
 *
 * - TextToSpeech:   to give instructions and provide feedback on actions
 * - MIDI:           to generate touch tones on the fly
 *
 * - StateMachine:   to step the user through each activity
 * - Dictation:      to centralize the content for a game activity
 *
 * - SwipeDetection: to activate preferences
 * - ShakeDetection: to activate calibration
 * - Calibration:    to allow the user to define personalized touch points
 *
 * - Preferences:    to select the activity, and manage audio feedback
 * - GameLogic:      to control the player's path through the game
 *
 * Each Cell, WordChunk and Sentence object will need access to the TTS object.
 * Each Cell will need access to the TouchActivity instance and the DrawView.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String source = getString(R.string.dictation);
        StateArray dictation = new Dictation(source);
        new StateMachine(dictation);
    }
}
