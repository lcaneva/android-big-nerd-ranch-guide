package com.big_nerd_ranch.geoquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);

        trueButton.setOnClickListener(view-> Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT));
        falseButton.setOnClickListener(view-> Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT));
    }
}