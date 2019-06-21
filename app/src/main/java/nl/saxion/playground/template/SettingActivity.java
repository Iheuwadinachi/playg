package nl.saxion.playground.template;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.playground.template.platformer.Activity;

public class SettingActivity extends AppCompatActivity {
    private TextView settingsTextView, soundTextView, musicTextView;

    private Switch soundSwitch,musicSwitch, themeSwitch;
    private Button btnQuitGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);


        soundSwitch = findViewById(R.id.soundSwitch);


        musicSwitch = findViewById(R.id.musicSwitch);

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
                    alert.setTitle("" );
                    alert.show();
                }
            });
        }


    }


