package nl.saxion.playground.template;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.playground.template.platformer.Activity;

public class SettingActivity extends AppCompatActivity {
    private TextView settingsTextView, soundTextView, musicTextView;

    private Switch soundSwitch;
    private Switch musicSwitch;
    private Switch themeSwitch;
    private Button btnQuitGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        soundSwitch = findViewById(R.id.soundSwitch);
        Switch toggle = (Switch) findViewById(R.id.soundSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Sound Switch on!", Toast.LENGTH_LONG).show();
                    } else {
                    Toast.makeText(getApplicationContext(), "Sound Switch off!", Toast.LENGTH_LONG).show();
                }
            }
        });

        musicSwitch = findViewById(R.id.musicSwitch);
        toggle = (Switch) findViewById(R.id.musicSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Music Switch on!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Music Switch off!", Toast.LENGTH_LONG).show();
                }
            }
        });




        themeSwitch = findViewById(R.id.themeSwitch);
    }
    public void nightMode(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "Nightmode Activated", Toast.LENGTH_SHORT);
        myToast.show();




                btnQuitGame = (Button) findViewById(R.id.quitButton);
                btnQuitGame.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder altquit = new AlertDialog.Builder(SettingActivity.this);
                        altquit.setMessage("Do you want to Quit this Game").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }

                                });

                        AlertDialog alert = altquit.create();
                        alert.setTitle("");
                        alert.show();
                    }
                });
            }

    public Switch getSoundSwitch() {
        return soundSwitch;
    }

    public void setSoundSwitch(Switch soundSwitch) {
        this.soundSwitch = soundSwitch;
    }

    public Switch getMusicSwitch() {
        return musicSwitch;
    }

    public void setMusicSwitch(Switch musicSwitch) {
        this.musicSwitch = musicSwitch;
    }

    public Switch getThemeSwitch() {
        return themeSwitch;
    }

    public void setThemeSwitch(Switch themeSwitch) {
        this.themeSwitch = themeSwitch;
    }
}