package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nougatteam.myapplication.interfaces.GameService;
import nougatteam.myapplication.pojo.GetScoresPojo;
import nougatteam.myapplication.pojo.GetThemesPojo;
import nougatteam.myapplication.pojo.ScorePojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class endgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_endgame);

        Intent myIntent = getIntent();
        final int score = myIntent.getIntExtra("score", 0);
        final String theme = myIntent.getStringExtra("theme");
        final TextView scoreText = (TextView) findViewById(R.id.score);
        scoreText.setText(""+score);

        final Button replay = (Button) findViewById(R.id.restart);
        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pushInfos(theme, score);
                Intent themeActivity = new Intent(endgame.this, theme.class);
                startActivity(themeActivity);
                finish();
            }
        });

        final Button scores = (Button) findViewById(R.id.scores);
        scores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pushInfos(theme, score);
                Intent scoreActivity = new Intent(endgame.this, score.class);
                startActivity(scoreActivity);
                finish();
            }
        });
    }


    private void pushInfos(String theme, int score) {
        final EditText textPseudo = (EditText) findViewById(R.id.pseudo);
        final String pseudo = textPseudo.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.26:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameService service = retrofit.create(GameService.class);

        /*Call<ScorePojo> scorePojoCall = service.addScore(pseudo, theme, score);
        scorePojoCall.enqueue(new Callback<ScorePojo>() {
            @Override
            public void onResponse(Call<ScorePojo> call, Response<ScorePojo> response) {
                System.out.println("Score envoyé");
            }

            @Override
            public void onFailure(Call<ScorePojo> call, Throwable t) {

            }
        });*/
    }



}
