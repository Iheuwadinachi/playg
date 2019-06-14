package nl.saxion.playground.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lib.GameModel;


// Teacher: This class has to be removed from master branch.
public class MainActivity extends AppCompatActivity {

    GameView lumberjackCanvas;
    GameModel lumberjack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lumberjackCanvas = findViewById(R.id.platformer);
        lumberjack = new nl.saxion.playground.template.lumberjack_simulator.Game();


        startActivity(new Intent(MainActivity.this, nl.saxion.playground.template.lumberjack_simulator.Activity.class));


//        TextView lumberjackTV = findViewById(R.id.platformerText);
//        lumberjackTV.setText("LUMBERJACK !!!");
//        lumberjackTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, nl.saxion.playground.template.lumberjack_simulator.Activity.class));
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lumberjackCanvas.setGame(lumberjack);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lumberjackCanvas.setGame(null);
    }
}
