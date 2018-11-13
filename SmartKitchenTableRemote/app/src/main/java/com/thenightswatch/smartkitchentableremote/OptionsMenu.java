package com.thenightswatch.smartkitchentableremote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class OptionsMenu extends AppCompatActivity {

    Switch themeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_options_menu);


        // Initiate switch views
        themeSwitch = findViewById(R.id.contrast);

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Do something. isChecked == true if switch is in ON position
                if (!isChecked) {
                    Utils.changeToTheme(OptionsMenu.this, Utils.THEME_DARK);
                }
                else {
                    Utils.changeToTheme(OptionsMenu.this, Utils.THEME_WHITE);
                }
            }
        });

        Button backButton = findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
