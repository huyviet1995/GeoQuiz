package com.example.huyviet1995.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private final String KEY_INDEX = "INDEX";
    private final String TAG = "QuizActivity";
    static final int REQUEST_CODE_CHEAT=0;
    //Create a string to store value of mIsCheater(or mCheatingBank[mCurrentIndex]



    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.vietnam_question,false),
        new TrueFalse(R.string.vietnam_question2,false),
        new TrueFalse(R.string.vietnam_question3,true),
        new TrueFalse(R.string.vietnam_question4,true),
        new TrueFalse(R.string.vietnam_question5,false)
    };

    /*Create an array mCheatingBank that holds the value of mIsCheater
    so that when user press Next to return to the current question, mIsCheater is still saved*/


    private int mCurrentIndex = 0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionTextView.length();
        if (mCurrentIndex == mQuestionBank.length) mCurrentIndex = 0;
        updateQuestion();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if (mCheatingBank[mCurrentIndex]) {
            messageResId =R.string.judgement_toast;

        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }

        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        //save the current value of mCurrentIndex after the activity is destroyed

        /*Save the boolean value of the mIsCheater inside the savedInstanceState
        so that activity is not destroyed when screen is rotated*/

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_CODE_CHEAT)
            if (data == null) return;
        /*save the the value of wasAnswerShown to mIsCheater in onSaveInstanceState
        so that when user rotate it when they get back, mIsCheater (mCheatingBank[mCurrentIndex])
        is not clearer out */

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            //retrieve the value of the index when activity is restored after being destroyed

            //get the value of mIsCheater back
            
        }

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        mNextButton = (ImageButton)findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionTextView.length();
                if (mCurrentIndex == -1) mCurrentIndex = mQuestionBank.length-1;
                updateQuestion();
            }
        });

        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //start cheatActivity
                boolean answerIsTrue =mQuestionBank[mCurrentIndex].isTrueQuestion();
                Intent i = CheatActivity.newIntent(QuizActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
    }

}
