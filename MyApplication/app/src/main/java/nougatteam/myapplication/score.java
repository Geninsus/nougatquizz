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

import nougatteam.myapplication.pojo.ScorePojo;

public class score extends Activity {
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        ArrayList<ScorePojo> arrayOfScores = new ArrayList<ScorePojo>();
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
