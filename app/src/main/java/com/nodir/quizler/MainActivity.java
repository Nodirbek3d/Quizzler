package com.nodir.quizler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button trueButton;
    private Button falseButton;
    private TextView questionView;
    private int qIndex;
    private int mScore;
    private TrueFalse[] questions =
    {
        new TrueFalse(R.string.question_1, false),
        new TrueFalse(R.string.question_2, true),
        new TrueFalse(R.string.question_3, false),
        new TrueFalse(R.string.question_4, true),
        new TrueFalse(R.string.question_5, true),
        new TrueFalse(R.string.question_6, false),
        new TrueFalse(R.string.question_7, false),
        new TrueFalse(R.string.question_8, true),
        new TrueFalse(R.string.question_9, false),
        new TrueFalse(R.string.question_10, true),
        new TrueFalse(R.string.question_11, true)
    };
    private final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / (questions.length + 1));
    ProgressBar mProgressBar;
    TextView mScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            mScore = savedInstanceState.getInt("Score");
            qIndex = savedInstanceState.getInt("IndexKey");
        } else {
            mScore = 0;
            qIndex = 0;
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        mProgressBar = findViewById(R.id.progressBar);
        mScoreTextView = findViewById(R.id.score);

        questionView = findViewById(R.id.question_text_view);
        questionView.setText(questions[qIndex].getmQuestionID());

        mScoreTextView.setText(getString(R.string.score) + " " + mScore + "/" + questions.length);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        qIndex = (qIndex + 1) % questions.length;
        if (qIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.alertTitle));
            alert.setCancelable(false);
            alert.setMessage("You Scored " + mScore + " points!");
            alert.setPositiveButton(getString(R.string.alertPositiveButton), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface anInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        int questionID = questions[qIndex].getmQuestionID();
        questionView.setText(questionID);
        mScoreTextView.setText(getString(R.string.score) + " " + mScore + "/" + questions.length);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    }

    private void checkAnswer(boolean userAnswer){
        boolean correctAnswer = questions[qIndex].ismAnswer();
        if (correctAnswer == userAnswer){
            ++mScore;
            Toast.makeText(getApplicationContext(),R.string.correctToast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),R.string.incorrectToast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Score", mScore);
        outState.putInt("IndexKey", qIndex);
    }
}
