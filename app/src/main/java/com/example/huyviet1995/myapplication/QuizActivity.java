package com.example.huyviet1995.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;


    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.vietnam_question,false),
        new TrueFalse(R.string.vietnam_question2,false),
        new TrueFalse(R.string.vietnam_question3,true),
        new TrueFalse(R.string.vietnam_question4,true),
        new TrueFalse(R.string.vietnam_question5,false)
    };

    private int mCurrentIndex = 0;


    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId  = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else
        {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();


        mTrueButton = (Button)findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothing yet, but soon
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionTextView.length();
                updateQuestion();
            }
        });

        /*mPreviousButton = (Button)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionTextView.length();
                updateQuestion();
            }
        });*/

    }

}
