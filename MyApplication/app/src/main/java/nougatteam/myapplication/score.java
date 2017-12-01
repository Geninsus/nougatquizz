package nougatteam.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nougatteam.myapplication.interfaces.GameService;
import nougatteam.myapplication.pojo.GetScoresPojo;
import nougatteam.myapplication.pojo.GetThemesPojo;
import nougatteam.myapplication.pojo.ScorePojo;
import nougatteam.myapplication.statics.Generals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class score extends Activity {
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);


       /* Button Navigation Settings */
        final ImageView buttonHome = (ImageView) findViewById(R.id.homeIcon);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent homeActivity = new Intent(score.this, home.class);
                startActivity(homeActivity);
                finish();
            }
        });

        final ImageView buttonSettings = (ImageView) findViewById(R.id.settingsIcon);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* Implement fragment */
            }
        });
        /* End Button Navigation */

        /* Connection to the DB */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Generals.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /* Getting the scores */
        GameService service = retrofit.create(GameService.class);
        Call<GetScoresPojo> scores = service.getScores(5);
        scores.enqueue(new Callback<GetScoresPojo>() {
            @Override
            public void onResponse(Call<GetScoresPojo> call, Response<GetScoresPojo> response) {
                if (response.isSuccessful()) {
                    /* creating the array with ScorePojo */
                    TextView pseudoText = findViewById(R.id.firstPseudo);
                    TextView themeText = findViewById(R.id.firstTheme);
                    TextView scoreText = findViewById(R.id.firstPoint);

                    if (response.body().scores.length >= 1){
                        pseudoText.setText(response.body().scores[0].name);
                        themeText.setText(response.body().scores[0].theme);
                        scoreText.setText(""+response.body().scores[0].score);
                    }

                    if (response.body().scores.length >= 2){
                        pseudoText = findViewById(R.id.secondPseudo);
                        pseudoText.setText(response.body().scores[1].name);
                        themeText = findViewById(R.id.secondTheme);
                        themeText.setText(response.body().scores[1].theme);
                        scoreText = findViewById(R.id.secondPoint);
                        scoreText.setText(""+response.body().scores[1].score);
                    }

                    if (response.body().scores.length >= 3) {
                        pseudoText = findViewById(R.id.thirdPseudo);
                        pseudoText.setText(response.body().scores[2].name);
                        themeText = findViewById(R.id.thirdTheme);
                        themeText.setText(response.body().scores[2].theme);
                        scoreText = findViewById(R.id.thirdPoint);
                        scoreText.setText("" + response.body().scores[2].score);
                    }

                    if (response.body().scores.length >= 4) {
                        pseudoText = findViewById(R.id.forthPseudo);
                        pseudoText.setText(response.body().scores[3].name);
                        themeText = findViewById(R.id.forthTheme);
                        themeText.setText(response.body().scores[3].theme);
                        scoreText = findViewById(R.id.forthPoint);
                        scoreText.setText("" + response.body().scores[3].score);
                    }

                    if (response.body().scores.length >= 5) {
                        pseudoText = findViewById(R.id.fifthPseudo);
                        pseudoText.setText(response.body().scores[4].name);
                        themeText = findViewById(R.id.fifthTheme);
                        themeText.setText(response.body().scores[4].theme);
                        scoreText = findViewById(R.id.fifthPoint);
                        scoreText.setText("" + response.body().scores[4].score);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetScoresPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });

    }
}