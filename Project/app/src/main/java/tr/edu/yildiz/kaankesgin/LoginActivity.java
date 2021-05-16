package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private ImageView LoggedInPhoto;
    private TextView LoggedInName;
    private ImageButton addQuestionButton;
    private ImageButton listQuestionsButton;
    private ImageButton settingsButton;
    private ImageButton makeQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        defineVariables();
        defineListeners();
        DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
        String tmp = getIntent().getStringExtra("userMail");
        LoggedInName.setText("Hoşgeldin "+db.getUserName(tmp)+" !");
        LoggedInPhoto.setImageResource(Integer.parseInt(db.getUserPhoto(tmp)));



    }

    public void defineVariables(){
        String LoggedUsername;
        int LoggedPhoto;
        Intent intent = getIntent();
        LoggedInPhoto = findViewById(R.id.LoggedInPhoto);
        LoggedInName = findViewById(R.id.LoggeInName);
        addQuestionButton = findViewById(R.id.addQuestionButton);
        listQuestionsButton = findViewById(R.id.listQuestionsButton);
        settingsButton = findViewById(R.id.settingsButton);
        makeQuizButton = findViewById(R.id.makeQuizButton);
        LoggedInName.setText("Welcome "+intent.getStringExtra("user")+"!");
        LoggedPhoto = intent.getIntExtra("photo",0);
        LoggedInPhoto.setImageResource(LoggedPhoto);



    }

    public void defineListeners(){
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent= new Intent(v.getContext(),addQuestionActivity.class);
                startActivity(intent);
            }
        });

        listQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),listQuestions.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),quizSettingsActivity.class);
                startActivity(intent);
            }
        });

        makeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),setExamActivity.class);
                startActivity(intent);
            }
        });

    }

    public void defineTables(){


    }

}