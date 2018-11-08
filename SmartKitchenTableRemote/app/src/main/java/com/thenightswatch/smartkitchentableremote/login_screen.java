package com.thenightswatch.smartkitchentableremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_screen extends AppCompatActivity {

    private String setPassword;
    private Button submitButton;

    public login_screen() {
        setPassword = getString(R.string.requested_password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                submitPassword();
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
