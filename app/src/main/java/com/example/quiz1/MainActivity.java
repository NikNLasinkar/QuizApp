package com.example.quiz1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView optionA,optionB,optionC,optionD;
    private TextView questionnumber,question,score;
    private TextView checkout1,checkout2;
    int currentIndex;
    int mscore=0;
    int qn=1;
    ProgressBar progressBar;

    int CurrentQuestion, CurrentOptionA,CurrentOptionB,CurrentOptionC,CurrentOptionD;


    private final answerclass[] questionBank = new answerclass[]
            {
                    new answerclass(R.string.question1,R.string.question1_A,R.string.question1_B,R.string.question1_C,R.string.question1_D,R.string.answer_1),
                    new answerclass(R.string.question2,R.string.question2_A,R.string.question2_B,R.string.question2_C,R.string.question2_D,R.string.answer_2),
                    new answerclass(R.string.question3,R.string.question3_A,R.string.question3_B,R.string.question3_C,R.string.question3_D,R.string.answer_3),
                    new answerclass(R.string.question4,R.string.question4_A,R.string.question4_B,R.string.question4_C,R.string.question4_D,R.string.answer_4),
                    new answerclass(R.string.question5,R.string.question5_A,R.string.question5_B,R.string.question5_C,R.string.question5_D,R.string.answer_5),
                    new answerclass(R.string.question6,R.string.question6_A,R.string.question6_B,R.string.question6_C,R.string.question6_D,R.string.answer_6),
                    new answerclass(R.string.question7,R.string.question7_A,R.string.question7_B,R.string.question7_C,R.string.question7_D,R.string.answer_7),
                    new answerclass(R.string.question8,R.string.question8_A,R.string.question8_B,R.string.question8_C,R.string.question8_D,R.string.answer_8),
                    new answerclass(R.string.question9,R.string.question9_A,R.string.question9_B,R.string.question9_C,R.string.question9_D,R.string.answer_9),
                    new answerclass(R.string.question10,R.string.question10_A,R.string.question10_B,R.string.question10_C,R.string.question10_D,R.string.answer_10)

            };



    final int PROGRESS_BAR = (int) Math.ceil(100/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        optionA=findViewById(R.id.optionA);
        optionB=findViewById(R.id.optionB);
        optionC=findViewById(R.id.optionC);
        optionD=findViewById(R.id.optionD);

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        questionnumber = findViewById(R.id.QuestionNumber);

        checkout1 = findViewById(R.id.selectoption);
        checkout2 = findViewById(R.id.CorrectAnswer);
        progressBar = findViewById(R.id.progressbar);

        CurrentQuestion =questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        CurrentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC=questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD=questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        optionA.setOnClickListener(v -> {
            checkAnswer(CurrentOptionA);
            updateQuestion();

        });

        optionB.setOnClickListener(v -> {

            checkAnswer(CurrentOptionB);
            updateQuestion();


        });

        optionC.setOnClickListener(v -> {

            checkAnswer(CurrentOptionC);
            updateQuestion();


        });

        optionD.setOnClickListener(v -> {

            checkAnswer(CurrentOptionD);
            updateQuestion();


        });




    }

    private void checkAnswer(int userSelection) {
        int correctanswer = questionBank[currentIndex].getAnswerid();

        checkout1.setText(userSelection);
        checkout2.setText(correctanswer);

        String m=checkout1.getText().toString().trim();
        String n=checkout2.getText().toString().trim();

        if(m.equals(n))
        {
            Toast.makeText(getApplicationContext(),"Right" ,Toast.LENGTH_SHORT).show();
            mscore=mscore+1;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong" ,Toast.LENGTH_SHORT).show();

        }




    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion() {

        currentIndex=(currentIndex+1)%questionBank.length;

        if(currentIndex==0)
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your Score"+ mscore +"Points");
            alert.setPositiveButton("Close Application", (dialog, which) -> finish());

            alert.setNegativeButton("NO", (dialog, which) -> {
                mscore=0;
                qn=1;
               progressBar.setProgress(0);
               score.setText("Score" +mscore +"/" +questionBank.length);
               questionnumber.setText(qn + "/" +questionBank.length + "Question");
            });
            alert.show();

        }


        CurrentQuestion =questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        CurrentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC=questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD=questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        qn = qn+1;

        if(qn<=questionBank.length)
        {
            questionnumber.setText(qn + "/" +questionBank.length + "Question");
        }
        score.setText("Score" +mscore +"/" +questionBank.length);
        progressBar.incrementProgressBy(PROGRESS_BAR);



    }
}