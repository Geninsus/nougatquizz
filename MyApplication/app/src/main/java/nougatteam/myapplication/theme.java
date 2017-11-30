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


        /* Button Navigation Settings */
        final ImageView buttonHome = (ImageView) findViewById(R.id.homeIcon);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent homeActivity = new Intent(theme.this, home.class);
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

        /* Button choose theme */
        final Button buttonTheme1 = (Button) findViewById(R.id.activity_game_theme1_btn);
        final Button buttonTheme2 = (Button) findViewById(R.id.activity_game_theme2_btn);
        final Button buttonTheme3 = (Button) findViewById(R.id.activity_game_theme3_btn);

        /* Connection to DB */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.122.15.0:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /* Getting 3 random themes */
        GameService service = retrofit.create(GameService.class);
        Call<GetThemesPojo> themes = service.getThemes(3);
        themes.enqueue(new Callback<GetThemesPojo>() {
            @Override
            public void onResponse(Call<GetThemesPojo> call, Response<GetThemesPojo> response) {
                if (response.isSuccessful()) {
                    /* Setting the theme in the button */
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

        /* Implement action on buttons */
        buttonTheme1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                instructionsActivity.putExtra("theme", buttonTheme1.getText().toString());
                startActivity(instructionsActivity);
                finish();
            }
        });

        buttonTheme2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                instructionsActivity.putExtra("theme", buttonTheme2.getText().toString());
                startActivity(instructionsActivity);
                finish();
            }
        });

        buttonTheme3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                instructionsActivity.putExtra("theme", buttonTheme3.getText().toString());
                startActivity(instructionsActivity);
                finish();
            }
        });
    }
}
