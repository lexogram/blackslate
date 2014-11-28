package com.example.talkback;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView text_view;
    EditText edit_text;
    AccessibilityManager accessibilityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view = (TextView) findViewById(R.id.text_view);
        edit_text = (EditText) findViewById(R.id.edit_text);
        accessibilityManager = (AccessibilityManager) this.getSystemService(Context.ACCESSIBILITY_SERVICE);

        /*
        Button update_button = (Button) findViewById(R.id.update_button);

        update_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String input = edit_text.getText().toString();
                text_view.setText(input);
                text_view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
            }
        });
        */
    }

    public void updateText(View view) {
        String input = edit_text.getText().toString();
        text_view.setText(input);
        text_view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
    }
}