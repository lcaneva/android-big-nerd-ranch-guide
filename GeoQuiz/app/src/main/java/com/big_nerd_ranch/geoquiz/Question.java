package com.big_nerd_ranch.geoquiz;

import androidx.annotation.StringRes;

public class Question {

    private int textResId;
    private boolean answer;

    public Question(@StringRes int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public boolean isAnswerTrue() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
