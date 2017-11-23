package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        final Button buttonScore = (Button) findViewById(R.id.scoreButton);
        buttonScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent scoreActivity = new Intent(home.this, score.class);
                startActivity(scoreActivity);
            }
        });
        final TextView tapToPlay = (TextView) findViewById(R.id.tapToPlay);
        tapToPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent instructionsActivity = new Intent(home.this, instructions.class);
                startActivity(instructionsActivity);
            }
        });

    }
}
