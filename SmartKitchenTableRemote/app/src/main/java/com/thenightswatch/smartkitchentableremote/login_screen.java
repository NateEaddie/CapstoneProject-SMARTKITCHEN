package com.thenightswatch.smartkitchentableremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class login_screen extends AppCompatActivity {

    private String setPassword;
    private Button submitButton;
    private EditText pw;

    public login_screen() {
        setPassword = "1234";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_login_screen);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                submitPassword();
            }
        });

        pw = (EditText) findViewById(R.id.passwordField);

        pw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    submitPassword();
                }
                return false;
            }
        });
    }

    /** Called when the user taps the Submit button */
    public void submitPassword() {
        final String enteredPassword = ((EditText)findViewById(R.id.passwordField)).getText().toString();
        submitButton = findViewById(R.id.submitButton);
        if (enteredPassword.equals(setPassword)) {
            Intent intent = new Intent(login_screen.this, loading_screen.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(login_screen.this, "Incorrect Password",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
