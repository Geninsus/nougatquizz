package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nougatteam.myapplication.interfaces.GameService;
import nougatteam.myapplication.pojo.GetQuestionsPojo;
import nougatteam.myapplication.pojo.GetThemesPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class theme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_theme);
        final ImageView homeIcon = (ImageView) findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent homeActivity = new Intent(theme.this, home.class);
                startActivity(homeActivity);
            }
        });

        final Button buttonTheme1 = (Button) findViewById(R.id.activity_game_theme1_btn);
        buttonTheme1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                startActivity(instructionsActivity);
            }
        });
        final Button buttonTheme2 = (Button) findViewById(R.id.activity_game_theme2_btn);
        buttonTheme2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                startActivity(instructionsActivity);
            }
        });
        final Button buttonTheme3 = (Button) findViewById(R.id.activity_game_theme3_btn);
        buttonTheme3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                startActivity(instructionsActivity);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.50:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameService service = retrofit.create(GameService.class);
        Call<GetThemesPojo> themes = service.getThemes(3);
        themes.enqueue(new Callback<GetThemesPojo>() {
            @Override
            public void onResponse(Call<GetThemesPojo> call, Response<GetThemesPojo> response) {
                if (response.isSuccessful()) {
                    buttonTheme1.setText(response.body().themes[0]);
                    buttonTheme2.setText(response.body().themes[1]);
                    buttonTheme3.setText(response.body().themes[2]);
                }
            }

            @Override
            public void onFailure(Call<GetThemesPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
        // DEMO 3 QUESTIONS THEME HARRY POTTER
        Call<GetQuestionsPojo> questions = service.getQuestions("HarryPotter", 3);
        questions.enqueue(new Callback<GetQuestionsPojo>() {
            @Override
            public void onResponse(Call<GetQuestionsPojo> call, Response<GetQuestionsPojo> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().questions[0].question);
                }
            }

            @Override
            public void onFailure(Call<GetQuestionsPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }
}
