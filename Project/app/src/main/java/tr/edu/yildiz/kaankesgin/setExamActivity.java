package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class setExamActivity extends AppCompatActivity {
    TextView quizDurationDisplayer,quizDifficultyDisplayer,quizPointDisplayer;
    Button quizChangeSettingsButton,quizAddQuestions,quizSetExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exam);
        defineVariables();
        defineListeners();
    }

    void defineVariables(){
        quizDurationDisplayer = findViewById(R.id.quizDurationDisplayer);
        quizDifficultyDisplayer = findViewById(R.id.quizDifficultyDisplayer);
        quizPointDisplayer = findViewById(R.id.quizPointDisplayer);

        quizChangeSettingsButton = findViewById(R.id.quizChangeSettingsButton);
        quizAddQuestions = findViewById(R.id.quizAddQuestionsButton);
        quizSetExam = findViewById(R.id.quizSetExamButton);

        SharedPreferences shared = getSharedPreferences("tr.edu.yildiz.kaankesgin.shared",MODE_PRIVATE);
        quizDurationDisplayer.setText(shared.getString("duration","20"));
        quizDifficultyDisplayer.setText(shared.getString("difficulty","2"));
        quizPointDisplayer.setText(shared.getString("questionPoint","1"));

    }

    void defineListeners(){

        quizChangeSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),quizSettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}