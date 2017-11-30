package nougatteam.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class instructions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instructions);

        /* Button Navigation Settings */
        final ImageView buttonHome = (ImageView) findViewById(R.id.homeIcon);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent homeActivity = new Intent(instructions.this, home.class);
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

        /* Get the theme choosen before */
        Intent myIntent = getIntent();
        final String theme = myIntent.getStringExtra("theme");


        /* Start game button */
        final Button buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent quizzActivity = new Intent(instructions.this, quizz.class);
                quizzActivity.putExtra("theme", theme);
                startActivity(quizzActivity);
                finish();
            }
        });
    }
}
