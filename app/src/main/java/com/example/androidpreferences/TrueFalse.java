package com.example.androidpreferences;

public class TrueFalse {
    private int questionID;
    private boolean answer;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public TrueFalse(int questionResourceID, boolean trueOrfalse){
        questionID = questionResourceID;
        answer = trueOrfalse;


    }
}
