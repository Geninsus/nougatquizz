package nougatteam.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import nougatteam.myapplication.statics.Generals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class endgame extends Activity {

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

        EditText mPseudoEditText = (EditText) findViewById(R.id.pseudo);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        mPseudoEditText.setText(preferences.getString("pseudo", ""));

        /* Button Navigation Settings */
        final ImageView homeButton = (ImageView) findViewById(R.id.homeIcon);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pushInfos(theme, score, "home");
            }
        });

        final ImageView settingsButton = (ImageView) findViewById(R.id.settingsIcon);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { }
        });
        /* End Button Navigation */

        /* Go to score button */
        final Button scores = (Button) findViewById(R.id.scores);
        scores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pushInfos(theme, score, "scores");
            }
        });

        /* Play again button */
        final Button replay = (Button) findViewById(R.id.restart);
        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pushInfos(theme, score, "replay");
            }
        });
    }

    /* Save score, name and theme into the database */
    private void pushInfos(String theme, int score, final String nextActivity) {
        final EditText textPseudo = (EditText) findViewById(R.id.pseudo);
        final String pseudo = textPseudo.getText().toString();
        if (TextUtils.isEmpty(textPseudo.getText())){
            // Display a Toast to the user
            Toast.makeText(this, "Veuillez entrer un pseudo", Toast.LENGTH_LONG).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Generals.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameService service = retrofit.create(GameService.class);

        Call<GetScoresPojo> scorePojoCall = service.addScore(pseudo, theme, score, 1);
        scorePojoCall.enqueue(new Callback<GetScoresPojo>() {
            @Override
            public void onResponse(Call<GetScoresPojo> call, Response<GetScoresPojo> response) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("pseudo", pseudo);
                editor.commit();
                switch (nextActivity){
                    case "replay":  Intent themeActivity = new Intent(endgame.this, theme.class);
                                    startActivity(themeActivity);
                                    finish();
                                    break;

                    case "scores":  Intent scoreActivity = new Intent(endgame.this, score.class);
                                    startActivity(scoreActivity);
                                    finish();
                                    break;

                    case "home":    Intent homeActivity = new Intent(endgame.this, home.class);
                                    startActivity(homeActivity);
                                    finish();
                                    break;
                }
            }

            @Override
            public void onFailure(Call<GetScoresPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }



}
