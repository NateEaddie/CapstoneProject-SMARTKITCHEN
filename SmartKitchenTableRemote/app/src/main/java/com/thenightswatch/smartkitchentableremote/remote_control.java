package com.thenightswatch.smartkitchentableremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class remote_control extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);

        Button optionsButton = findViewById(R.id.button);
        optionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openOptionsMenu();
            }
        });

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logout();
            }
        });
    }

    /** Called when the user taps the Options button */
    public void openOptionsMenu() {
        Intent intent = new Intent(this, OptionsMenu.class);
        startActivity(intent);
    }

    /** Called when the user taps the Logout button */
    public void logout() {
        Intent intent = new Intent(remote_control.this, login_screen.class);
        startActivity(intent);
    }
}
