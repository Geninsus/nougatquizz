package nougatteam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    }
}
