package com.example.texttospeech;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;


abstract class AccessibleActivity extends Activity {

    protected TTS tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TTS();
    }

    public void onResume() {
        super.onResume();
        tts.activate(this);
    }

    public void onPause() {
        super.onPause();
        tts.disactivate();
    }

    protected final TextWatcher textWatcher = new TextWatcher() {
        // TextWatcher requires these two methods, even if they are not used
        public void beforeTextChanged(CharSequence sequence, int start, int before, int after) {
        }

        public void onTextChanged(CharSequence sequence, int start, int before, int after) {
        }

        // Speak the entire new sequence, after the change
        public void afterTextChanged(Editable sequence) {
            String expression = sequence.toString();
            tts.say(expression);
        }
    };

    abstract public void TTSReady();
}