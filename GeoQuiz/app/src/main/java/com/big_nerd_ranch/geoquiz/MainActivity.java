package com.big_nerd_ranch.geoquiz;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private final List<Question> questions = Arrays.asList(//
            new Question(R.string.question_capital, true),
            new Question(R.string.question_lakes, true),
            new Question(R.string.question_mountains, false)
    );

    private final List<Boolean> answered = questions.stream().map(q -> false).collect(Collectors.toList());

    private int correctAnswers = 0;
    private int currQuestion = 0;
    private Button trueButton;
    private Button falseButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        trueButton = this.findViewById(R.id.true_button);
        falseButton = this.findViewById(R.id.false_button);
        final ImageButton nextButton = this.findViewById(R.id.next_button);
        final ImageButton prevButton = this.findViewById(R.id.previous_button);
        final TextView textView = this.findViewById(R.id.question_text);
        this.setTextToCurrentQuestion(textView);

        trueButton.setOnClickListener(view -> {
                    this.checkAnswer(true);
                    this.setTextToNextQuestion(textView);
                    view.setEnabled(!answered.get(currQuestion));
                }
        );
        falseButton.setOnClickListener(view -> {
                    this.checkAnswer(false);
            this.setTextToNextQuestion(textView);
            view.setEnabled(!answered.get(currQuestion));
                }
        );

        nextButton.setOnClickListener(view -> this.setTextToNextQuestion(textView));
        prevButton.setOnClickListener(view -> this.setTextToPreviousQuestion(textView));

        textView.setOnClickListener(view -> this.setTextToNextQuestion((TextView) view));
    }

    private void setTextToNextQuestion(final TextView textView) {
        this.currQuestion = (this.currQuestion + 1) % questions.size();
        this.setTextToCurrentQuestion(textView);
    }

    private void setTextToPreviousQuestion(final TextView textView) {
        if (this.currQuestion == 0) this.currQuestion = questions.size() - 1;
        else
            this.currQuestion--;
        this.setTextToCurrentQuestion(textView);
    }

    private void setTextToCurrentQuestion(final TextView textView) {
        textView.setText(questions.get(this.currQuestion).getTextResId());
    }

    private void checkAnswer(final boolean buttonAnswer) {
        if (questions.get(this.currQuestion).isAnswerTrue() == buttonAnswer) {
            correctAnswers++;
            this.createNewToast(R.string.correct_answer);
        } else
            this.createNewToast(R.string.wrong_answer);
        answered.set(currQuestion, true);
        if (answered.stream().allMatch(a -> a)) {
            createNewToast(R.string.finish_toast, correctAnswers, questions.size());
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
        }
    }

    private void createNewToast(@StringRes final int id, Object... formatArgs) {
        final Toast toast = Toast.makeText(this, getString(id, formatArgs), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}