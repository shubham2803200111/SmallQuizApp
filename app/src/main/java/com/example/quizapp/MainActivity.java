package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerOptionsRadioGroup;
    private Button nextButton;

    private String[] questions = {
            "Who is the captain of Indian Cricket Team in CWC23?",
            "Who won the gold medal in tokyo olympic in jevelin throw?",
            "What is 3*9?",
            "Who is the CM of Uttar Pradesh?"

    };

    private String[][] answerOptions = {
            {"Virat", "Rohit", "Hardik"},
            {"Gagan Narang", "Neeraj Chopra", "Bajrang punia"},
            {"56", "27", "78"},
            {"Yogi Adityanath","Akhilesh Yadav","Kejariwal"}
    };

    private int[] correctAnswers = {1, 1, 1,0}; // Index of the correct answer for each question
    private int currentQuestion = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerOptionsRadioGroup = findViewById(R.id.answerOptionsRadioGroup);
        nextButton = findViewById(R.id.nextButton);

        displayQuestion(currentQuestion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedAnswerId = answerOptionsRadioGroup.getCheckedRadioButtonId();
                if (selectedAnswerId != -1) {
                    RadioButton selectedAnswer = findViewById(selectedAnswerId);
                    int selectedAnswerIndex = answerOptionsRadioGroup.indexOfChild(selectedAnswer);
                    checkAnswer(selectedAnswerIndex);
                    currentQuestion++;

                    if (currentQuestion < questions.length) {
                        displayQuestion(currentQuestion);
                    } else {
                        // Quiz is complete, show the final score or a completion message.
                        showFinalScore();
                    }
                }
            }
        });
    }

    private void displayQuestion(int questionIndex) {
        questionTextView.setText(questions[questionIndex]);
        answerOptionsRadioGroup.clearCheck();

        for (int i = 0; i < answerOptions[questionIndex].length; i++) {
            RadioButton radioButton = (RadioButton) answerOptionsRadioGroup.getChildAt(i);
            radioButton.setText(answerOptions[questionIndex][i]);
        }
    }

    private void checkAnswer(int selectedAnswerIndex) {
        if (selectedAnswerIndex == correctAnswers[currentQuestion]) {
            score++;
        }
    }

    private void showFinalScore() {
        // Display the final score or a completion message.
        questionTextView.setText("Quiz completed! Your score: " + score + " out of " + questions.length);
        answerOptionsRadioGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
    }
}