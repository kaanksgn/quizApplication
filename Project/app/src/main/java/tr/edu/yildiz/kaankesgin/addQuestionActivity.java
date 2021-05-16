package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;

public class addQuestionActivity extends AppCompatActivity {
    EditText addQuestionText,option1,option2,option3,option4,option5;
    Button questionAddButton;
    TextView radioGroupText;
    RadioButton A,B,C,D,E,selected;
    String rightAnswerLetter;
    RadioGroup radioGroup;
    int selectedLetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        defineVariables();
        defineListeners();
    }

    public void defineVariables(){
       addQuestionText = findViewById(R.id.addQuestionText);
       option1 = findViewById(R.id.option1);
       option2 = findViewById(R.id.option2);
       option3 = findViewById(R.id.option3);
       option4 = findViewById(R.id.option4);
       option5 = findViewById(R.id.option5);
       questionAddButton = findViewById(R.id.questionAddButton);
       A = findViewById(R.id.A);
       B = findViewById(R.id.B);
       C = findViewById(R.id.C);
       D = findViewById(R.id.D);
       E = findViewById(R.id.E);
       radioGroup = findViewById(R.id.rightAnswer);
       radioGroupText = findViewById(R.id.radioGroupText);





    }



    public void defineListeners(){

        questionAddButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                boolean tmp =true;
                if (addQuestionText.getText().toString().length()==0){
                    addQuestionText.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    addQuestionText.setError("Soru kısmı boş bırakılamaz.");
                    tmp = false;
                }
                if(option1.getText().toString().length()==0){
                    option1.getBackground().setColorFilter(getResources().getColor(R.color.red),PorterDuff.Mode.SRC_ATOP);
                    option1.setError("İlk seçenek boş bırakılamaz.");
                    tmp = false;
                }
                if(option2.getText().toString().length()==0){
                    option2.getBackground().setColorFilter(getResources().getColor(R.color.red),PorterDuff.Mode.SRC_ATOP);
                    option2.setError("İkinci seçenek boş bırakılamaz.");
                    tmp = false;
                }
                if(radioGroup.getCheckedRadioButtonId()==-1){
                    radioGroupText.setText("Hata ! \nDoğru Seçenek boş bırakılamaz.");
                    //radioGroupText.setBackgroundColor(R.color.white);
                    radioGroupText.setTextColor(R.color.design_default_color_error);


                    tmp = false;
                }
                if (tmp) {
                    DatabaseHelper db = new DatabaseHelper(addQuestionActivity.this);
                    selectedLetter = radioGroup.getCheckedRadioButtonId();
                    selected = findViewById(selectedLetter);
                    boolean addresult = db.addQuest(addQuestionText.getText().toString(),option1.getText().toString(),option2.getText().toString(),option3.getText().toString(),option4.getText().toString(),option5.getText().toString(),selected.getText().toString());
                    if (addresult)
                         onBackPressed();}
            }
        });

        addQuestionText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addQuestionText.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
        option1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                option1.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
        option2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                option2.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
        option3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                option3.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
        option4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                option4.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
        option5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                option5.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
    }

}