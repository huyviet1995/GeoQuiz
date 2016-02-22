package com.example.huyviet1995.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huyviet1995.myapplication.R;

/**
 * Created by huyviet1995 on 2/15/16.
 */
public class CheatActivity extends Activity{

    private static final String EXTRA_ANSWER_IS_TRUE ="com.example.huyviet1995.myApplication.answer_is_true";
    private static final String EXTRA_ANSWER_SHOW="com.example.huyviet1995.myapplication.answer_show";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    public static final String CHEAT_RETRIEVE = "com.example.huyviet1995.myApplication.cheat_retrieve";
    //check whether user has cheated
    private boolean isCheated;

    public CheatActivity() {
    }

    public static Intent newIntent (Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOW, isAnswerShown);
        setResult(RESULT_OK, data);
        /*Challenge 1: save the value of isAnswerShown to isCheated*/
        isCheated = isAnswerShown;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOW,false);
    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        /*Challenge 1: Save the value of isCheated before activity is destroyed when user rotate the screen*/
        savedInstanceState.putBoolean(CHEAT_RETRIEVE,isCheated);
    }
    @Override
    protected void onCreate (final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        /*Challenge 1: Return the value of isCheated back when the activity is restored*/
        if (savedInstanceState!=null) {
            isCheated = savedInstanceState.getBoolean(CHEAT_RETRIEVE);
            setAnswerShownResult(isCheated);
        }
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView)findViewById(R.id.answer_text_view);
        mShowAnswer =(Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAnswerIsTrue) {

                    mAnswerTextView.setText(R.string.true_button);

                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }

        });
    }
}
