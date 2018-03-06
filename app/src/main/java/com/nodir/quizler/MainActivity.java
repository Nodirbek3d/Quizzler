package com.nodir.quizler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button trueButton;
    private Button falseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Quizzler", "Button is clicked");
                Toast.makeText(getApplicationContext(), "True pressed", Toast.LENGTH_SHORT).show();
            }
        };

        trueButton.setOnClickListener(myListener);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Quizzler", "False button is clicked");
                Toast myToast = Toast.makeText(getApplicationContext(),"False pressed", Toast.LENGTH_SHORT);
                myToast.show();

            }
        });

    }
}
