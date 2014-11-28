package com.example.texttospeech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;


public class TTS implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {

    private static final String LOGTAG = "TTS";
    private static final String INITTAG = "INIT";

    private TextToSpeech tts;
    private boolean ttsReady = false;
    private final int mode = TextToSpeech.QUEUE_FLUSH; // QUEUE_ADD; //
    private HashMap hashMap = new HashMap<String, String>();

    private AccessibleActivity activity;
    private String initMessage;

    public void activate(AccessibleActivity activity) {
        this.activity = activity;

        Context context = activity.getApplicationContext();

        initMessage = (String) activity.getTitle();
        tts = new TextToSpeech(context, this);
    }

    public void disactivate() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        ttsReady = false;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // After initialization, the tts object is ready to receive other settings...
            tts.setLanguage(Locale.UK);
            tts.setOnUtteranceCompletedListener(this);
            ttsReady = true;

            // ... and to say things.
            if (initMessage != null) {
                say(initMessage, INITTAG);
            }
        }
    }

    public void say(String expression) {
        if (ttsReady) {
            tts.speak(expression, mode, null);
        } else {
            Log.d(LOGTAG, "Not ready to say '" + expression + "'", new Exception());
        }
    }

    public void say(String expression, String id) {
        hashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, id);
        tts.speak(expression, mode, hashMap);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        if (utteranceId.equals(INITTAG)) {

            // The TextToSpeech engine runs in its own thread. It calls this method from within that
            // thread, but the MainActivity runs in the UI thread, so the call to it must be made
            // explicitly inside the UI thread. If TTSReady() only calls tts.say() then this is
            // an unnecessary precaution. However, if TTSReady needs to alter the User Interface
            // this is essential.
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.TTSReady();
                }
            });
        }
    }
}