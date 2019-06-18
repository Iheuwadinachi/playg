 package nl.saxion.playground.template.spaceshooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.playground.template.R;

 public class SettingsMenu {


    private  String music;
    private  String sounds;
    private  String settings;
    private  boolean quitGame;


     public SettingsMenu(String music, String sounds, String settings, boolean quitGame) {

         this.music = music;
         this.sounds = sounds;
         this.settings = settings;
         this.quitGame = quitGame;


     }
public SettingsMenu(){

}
     public String getMusic() {
         return music;
     }

     public void setMusic(String music) {
         this.music = music;
     }

     public String getSounds() {
         return sounds;
     }

     public void setSounds(String sounds) {
         this.sounds = sounds;
     }

     public String getSettings() {
         return settings;
     }

     public void setSettings(String settings) {
         this.settings = settings;
     }}

















