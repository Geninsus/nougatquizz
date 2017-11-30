package nougatteam.myapplication;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import nougatteam.myapplication.interfaces.GameService;
import nougatteam.myapplication.pojo.GetQuestionsPojo;
import nougatteam.myapplication.pojo.QuestionPojo;
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
                .baseUrl("http://10.122.15.0:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameService service = retrofit.create(GameService.class);

        final int quizzDuration = 30000;

        Intent myIntent = getIntent();
        final String theme = myIntent.getStringExtra("theme");

        final TextView scoreText = (TextView) findViewById(R.id.scoreLeft);
        scoreText.setText("Score : " + score);

        Call<GetQuestionsPojo> questions = service.getQuestions(theme, 0);
        questions.enqueue(new Callback<GetQuestionsPojo>() {
            @Override
            public void onResponse(Call<GetQuestionsPojo> call, final Response<GetQuestionsPojo> response) {
                if (response.isSuccessful()) {
                    final TextView mTextField = (TextView) findViewById(R.id.timeRemaining);

                    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animate towards that value
                    animation.setDuration (quizzDuration); //in milliseconds
                    animation.setInterpolator (new DecelerateInterpolator());
                    animation.start();

                    CountDownTimer theTimer = new CountDownTimer(quizzDuration, 1000) {

                        public void onTick(long millisUntilFinished) {
                            if (((millisUntilFinished / 1000)%60) < 10){
                                mTextField.setText((millisUntilFinished / 1000)/60 + ":0" + (millisUntilFinished / 1000)%60);
                            } else {
                                mTextField.setText((millisUntilFinished / 1000)/60 + ":" + (millisUntilFinished / 1000)%60);
                            }
                        }

                        public void onFinish() {
                            Intent endGameActivity = new Intent(quizz.this, endgame.class);
                            endGameActivity.putExtra("score", score);
                            endGameActivity.putExtra("theme", theme);
                            startActivity(endGameActivity);
                            finish();
                        }
                    }.start();

                    changeQuestion(response.body(), 0, theTimer, theme);
                }
            }

            @Override
            public void onFailure(Call<GetQuestionsPojo> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }

    public void checkAnswer(boolean answer, boolean real, GetQuestionsPojo questionObject, int i, CountDownTimer theTimer, String theme){
        if (answer == real){
            score++;
            final TextView scoreText = (TextView) findViewById(R.id.scoreLeft);
            scoreText.setText("Score : " + score);
        }
        if (i+1 <= questionObject.questions.length-1){
            System.out.println(questionObject.questions.length);
            changeQuestion(questionObject, i+1, theTimer, theme);
        } else {
            theTimer.cancel();
            Intent endGameActivity = new Intent(quizz.this, endgame.class);
            endGameActivity.putExtra("score",score);
            endGameActivity.putExtra("theme", theme);
            startActivity(endGameActivity);
            finish();
        }
    }

    public void changeQuestion(GetQuestionsPojo questionObjet, int j, final CountDownTimer theTimer, final  String theme){
        final GetQuestionsPojo questionObject = questionObjet;
        final int i = j;
        final Button trueButton = (Button) findViewById(R.id.buttonTrue);
        final Button falseButton = (Button) findViewById(R.id.buttonFalse);
        final TextView questionField = (TextView) findViewById(R.id.question);
        questionField.setText(questionObject.questions[i].question);

        if (questionObject.questions[i].answer){
            trueButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(true, true, questionObject, i, theTimer, theme);
                }
            });

            falseButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(false, true, questionObject, i, theTimer, theme);
                }
            });
        } else {
            trueButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(true, false, questionObject, i, theTimer, theme);
                }
            });

            falseButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(false, false, questionObject, i, theTimer, theme);
                }
            });
        }
    }
}
