package com.thenightswatch.smartkitchentableremote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OptionsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_options_menu);


        Button themeButton = findViewById(R.id.themeButton);
        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.sTheme == Utils.THEME_DARK) {
                    Utils.changeToTheme(OptionsMenu.this, Utils.THEME_WHITE);
                }
                else {
                    Utils.changeToTheme(OptionsMenu.this, Utils.THEME_DARK);
                }
            }
        });

//        Button textSizeButton = findViewById(R.id.textSizeButton);
//        textSizeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                changeTextSize();
//            }
//        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRemoteControl();
            }
        });
    }

    public void openRemoteControl() {
        Intent intent = new Intent(OptionsMenu.this, remote_control.class);
        startActivity(intent);
    }

//    public void changeTextSize() {
//
//    }
}
