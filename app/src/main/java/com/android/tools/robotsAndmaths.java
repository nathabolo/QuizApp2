package com.android.tools;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class RobotsAndmaths extends AppCompatActivity {

    private ViewGroup radioGroup;
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robots_andmaths);

        //Get a list of radio buttons in a radio group

        //A code for back button

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        DbHelper db = new DbHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.maths_robots1);
        rda = (RadioButton) findViewById(R.id.tech1);
        rdb = (RadioButton) findViewById(R.id.tech2);
        rdc = (RadioButton) findViewById(R.id.tech3);
        butNext = (Button) findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup2);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
                if (currentQ.getANSWER().equals(answer.getText())) {
                    score++;
                    Log.d("score", "Your score" + score);
                }
                if (qid < 5) {
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(RobotsAndmaths.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    //Get action bar into an activity

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}




