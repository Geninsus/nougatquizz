package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        buttonTheme1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                startActivity(instructionsActivity);
            }
        });
        final Button buttonTheme3 = (Button) findViewById(R.id.activity_game_theme3_btn);
        buttonTheme1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instructionsActivity = new Intent(theme.this, instructions.class);
                startActivity(instructionsActivity);
            }
        });
    }
}
