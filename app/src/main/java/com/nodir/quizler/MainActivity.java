package com.nodir.quizler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button trueButton;
    private Button falseButton;
    private TextView questionView;
    private int qIndex = 0;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionView = findViewById(R.id.question_text_view);
        questionView.setText(questions[qIndex].getmQuestionID());

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
                checkAnswer(true);
//                Toast.makeText(getApplicationContext(),"True Pressed", Toast.LENGTH_SHORT).show();

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
                checkAnswer(false);
//                Toast.makeText(getApplicationContext(),"False Pressed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateQuestion(){
        qIndex = (qIndex + 1) % questions.length;
        int questionID = questions[qIndex].getmQuestionID();
        questionView.setText(questionID);
    }

    private void checkAnswer(boolean userAnswer){
        boolean correctAnswer = questions[qIndex].ismAnswer();
        if (correctAnswer == userAnswer){
            Toast.makeText(getApplicationContext(),R.string.correctToast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),R.string.incorrectToast, Toast.LENGTH_SHORT).show();
        }
    }

}
