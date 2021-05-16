package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText editQuestion,editOption1,editOption2,editOption3,editOption4,editOption5;
    String rightAnswer;
    Button updateButton,deleteButton;
    String question,o1,o2,o3,o4,o5,questionid;
    RadioButton editA,editB,editC,editD,editE,selected;
    RadioGroup radioGroup;
    int selectedLetter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        defineVariables();

        defineListeners();
        getSetIntentData();
    }

    void defineVariables(){

        editQuestion = findViewById(R.id.editQuestionText);
        editOption1 = findViewById(R.id.editOption1);
        editOption2 = findViewById(R.id.editOption2);
        editOption3 = findViewById(R.id.editOption3);
        editOption4 = findViewById(R.id.editOption4);
        editOption5 = findViewById(R.id.editOption5);
        updateButton = findViewById(R.id.questionEditButton);
        deleteButton = findViewById(R.id.questionDeleteButton);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editC = findViewById(R.id.editC);
        editD = findViewById(R.id.editD);
        editE = findViewById(R.id.editE);
        radioGroup = findViewById(R.id.editRightAnswer);

    }

    void defineListeners(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String query;
            DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
            selectedLetter = radioGroup.getCheckedRadioButtonId();
            selected = findViewById(selectedLetter);
           boolean updateResult =  db.editQuest(id,editQuestion.getText().toString(),editOption1.getText().toString(),editOption2.getText().toString(),editOption3.getText().toString(),editOption4.getText().toString(),editOption5.getText().toString(),selected.getText().toString());
           if (updateResult)
               onBackPressed();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Uyarı");
                builder.setMessage("Soruyu silmek istediğinize emin misiniz?");

                // add the buttons
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                        boolean deleteResult = db.deleteQuest(id);
                        if (deleteResult)
                            onBackPressed();
                    }


                });
                builder.setNegativeButton("Hayır", null);

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    void getSetIntentData(){

            editQuestion.setText(getIntent().getStringExtra("idofquestion"));
            editOption1.setText(getIntent().getStringExtra("idofo1"));
            editOption2.setText(getIntent().getStringExtra("idofo2"));
            editOption3.setText(getIntent().getStringExtra("idofo3"));
            editOption4.setText(getIntent().getStringExtra("idofo4"));
            editOption5.setText(getIntent().getStringExtra("idofo5"));
            rightAnswer = getIntent().getStringExtra("idofrightAnswer");
            id = getIntent().getStringExtra("idofquestid");

            if (rightAnswer.equals("A")){
                radioGroup.check(R.id.editA);
            }else if (rightAnswer.equals("B"))
                radioGroup.check(R.id.editB);
            else if (rightAnswer.equals("C"))
                radioGroup.check(R.id.editC);
            else if (rightAnswer.equals("D"))
                radioGroup.check(R.id.editD);
            else if (rightAnswer.equals("E"))
                radioGroup.check(R.id.editE);


    }
}