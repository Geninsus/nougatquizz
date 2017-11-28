package nougatteam.myapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import nougatteam.myapplication.interfaces.GameService;
import nougatteam.myapplication.pojo.GetQuestionsPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class quizz extends AppCompatActivity {
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizz);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.26:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameService service = retrofit.create(GameService.class);

        final int quizzDuration = 20000;

        final Button trueButton = (Button) findViewById(R.id.buttonTrue);
        final Button falseButton = (Button) findViewById(R.id.buttonFalse);

        Intent myIntent = getIntent();
        final String theme = myIntent.getStringExtra("theme");
        final TextView questionField = (TextView) findViewById(R.id.question);

        Call<GetQuestionsPojo> questions = service.getQuestions(theme, 3);
        questions.enqueue(new Callback<GetQuestionsPojo>() {
            @Override
            public void onResponse(Call<GetQuestionsPojo> call, final Response<GetQuestionsPojo> response) {
                if (response.isSuccessful()) {
                    questionField.setText(response.body().questions[0].question);
                    trueButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            checkAnswer(true, response.body().questions[0].answer, questionField);
                        }
                    });

                    falseButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            checkAnswer(false, response.body().questions[0].answer, questionField);
                        }
                    });

                    final TextView mTextField = (TextView) findViewById(R.id.timeRemaining);

                    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animate towards that value
                    animation.setDuration (quizzDuration); //in milliseconds
                    animation.setInterpolator (new DecelerateInterpolator());
                    animation.start();

                    new CountDownTimer(quizzDuration, 1000) {

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
                            finish();
                        }
                    }.start();
                }
            }

            @Override
            public void onFailure(Call<GetQuestionsPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }

    public void checkAnswer(boolean answer, boolean real, TextView questionText){
        if (answer == real){
            score++;
            questionText.setText("Bonne réponse +1");
        } else {
            score++;
            questionText.setText("Mauvaise réponse :/");
        }
    }
}
