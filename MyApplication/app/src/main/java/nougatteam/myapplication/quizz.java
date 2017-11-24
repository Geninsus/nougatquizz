package nougatteam.myapplication;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class quizz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizz);

        final TextView mTextField = (TextView) findViewById(R.id.timeRemaining);
        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (((millisUntilFinished / 1000)%60) < 10){
                    mTextField.setText((millisUntilFinished / 1000)/60 + ":0" + (millisUntilFinished / 1000)%60);
                } else {
                    mTextField.setText((millisUntilFinished / 1000)/60 + ":" + (millisUntilFinished / 1000)%60);
                }
            }

            public void onFinish() {
                Intent scoreActivity = new Intent(quizz.this, score.class);
                startActivity(scoreActivity);
            }
        }.start();

    }
}
