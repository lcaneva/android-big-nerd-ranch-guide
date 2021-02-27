package com.big_nerd_ranch.geoquiz;

import android.os.Bundle;
import android.view.Gravity;
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

        trueButton.setOnClickListener(view -> createNewToast(R.string.correct_answer));
        falseButton.setOnClickListener(view -> createNewToast(R.string.wrong_answer));
    }

    private void createNewToast(int id) {
        Toast toast = Toast.makeText(this, id, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }
}