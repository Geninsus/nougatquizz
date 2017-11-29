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
        setContentView(R.layout.activity_score);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.26:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ArrayList<ScorePojo> arrayOfScores = new ArrayList<ScorePojo>();
        GameService service = retrofit.create(GameService.class);
        Call<GetScoresPojo> themes = service.getScores(20);
        themes.enqueue(new Callback<GetScoresPojo>() {
            @Override
            public void onResponse(Call<GetScoresPojo> call, Response<GetScoresPojo> response) {
                if (response.isSuccessful()) {
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


        scoreAdapter adapter = new scoreAdapter(this, arrayOfScores);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.listScore);
        listView.setAdapter(adapter);




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);
        final ImageView buttonHome = (ImageView) findViewById(R.id.homeIcon);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent homeActivity = new Intent(score.this, home.class);
                startActivity(homeActivity);
                finish();
            }
        });




    }
}
