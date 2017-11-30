package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        /* Button Navigation Settings */
        final ImageView buttonScore = (ImageView) findViewById(R.id.scoreIcon);
        buttonScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent scoreActivity = new Intent(home.this, score.class);
                startActivity(scoreActivity);
                finish();
            }
        });
        final ImageView buttonSettings = (ImageView) findViewById(R.id.settingsIcon);
        buttonScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* Implement fragment */
            }
        });
        /* End Button Navigation */



        /* Tap to play */
        final LinearLayout zoneTap1 = (LinearLayout) findViewById(R.id.zoneTap1);
        zoneTap1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent themeActivity = new Intent(home.this, theme.class);
                startActivity(themeActivity);
                finish();
            }
        });
        final LinearLayout zoneTap2 = (LinearLayout) findViewById(R.id.zoneTap2);
        zoneTap2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent themeActivity = new Intent(home.this, theme.class);
                startActivity(themeActivity);
                finish();
            }
        });
        final TextView tapToPlay = (TextView) findViewById(R.id.tapToPlay);
        tapToPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent themeActivity = new Intent(home.this, theme.class);
                startActivity(themeActivity);
                finish();
            }
        });
    }
}
