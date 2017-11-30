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

import java.util.ArrayList;

import nougatteam.myapplication.interfaces.GameService;
import nougatteam.myapplication.pojo.GetScoresPojo;
import nougatteam.myapplication.pojo.GetThemesPojo;
import nougatteam.myapplication.pojo.ScorePojo;
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
                .baseUrl("http://10.122.15.0:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /* Getting the scores */
        final ArrayList<ScorePojo> arrayOfScores = new ArrayList<ScorePojo>();
        GameService service = retrofit.create(GameService.class);
        Call<GetScoresPojo> scores = service.getScores(20);
        scores.enqueue(new Callback<GetScoresPojo>() {
            @Override
            public void onResponse(Call<GetScoresPojo> call, Response<GetScoresPojo> response) {
                if (response.isSuccessful()) {
                    /* creating the array with ScorePojo */
                    ScorePojo score = new ScorePojo();
                    for (int i =0; i<20;i++){
                        score.name = response.body().scores[i].name;
                        score.theme = response.body().scores[i].theme;
                        score.name = response.body().scores[i].name;
                        arrayOfScores.add(score);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetScoresPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });

        /* Create adapter with arrayOfScores */
        scoreAdapter adapter = new scoreAdapter(this, arrayOfScores);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.listScore);
        listView.setAdapter(adapter);

    }
}
