package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class endgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_endgame);

        final Button replay = (Button) findViewById(R.id.restart);
        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent themeActivity = new Intent(endgame.this, theme.class);
                pushInfos();
                startActivity(themeActivity);
                finish();
            }
        });

        final Button scores = (Button) findViewById(R.id.scores);
        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent scoreActivity = new Intent(endgame.this, score.class);
                startActivity(scoreActivity);
                finish();
            }
        });
    }
    Intent myIntent = getIntent();
    final int score = Integer.parseInt(myIntent.getStringExtra("score"));
    final String theme = myIntent.getStringExtra("theme");


    private void pushInfos() {
        final EditText textPseudo = (EditText) findViewById(R.id.pseudo);
        final String pseudo = textPseudo.getText().toString();
        /* PUSH IT */
    }



}
