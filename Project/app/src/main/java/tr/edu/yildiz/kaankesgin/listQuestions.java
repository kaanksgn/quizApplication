package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class listQuestions extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> quest_id,question,o1,o2,o3,o4,o5,rightAnswer;
    DatabaseHelper db;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);



    }

    @Override
    protected void onResume() {
        super.onResume();
        defineVariables();
        storeData();
        customAdapter = new CustomAdapter(listQuestions.this,quest_id,question,o1,o2,o3,o4,o5,rightAnswer);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(listQuestions.this));
    }

    public void defineVariables() {
        recyclerView = findViewById(R.id.recyclerView);
        db = new DatabaseHelper(listQuestions.this);
        quest_id = new ArrayList<>();
        question = new ArrayList<>();
        o1 = new ArrayList<>();
        o2 = new ArrayList<>();
        o3 = new ArrayList<>();
        o4 = new ArrayList<>();
        o5 = new ArrayList<>();
        rightAnswer = new ArrayList<>();


    }

    void storeData(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount()== 0){
            Toast.makeText(listQuestions.this,"No Data.",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                quest_id.add(cursor.getString(0));
                question.add(cursor.getString(1));
                o1.add(cursor.getString(2));
                o2.add(cursor.getString(3));
                o3.add(cursor.getString(4));
                o4.add(cursor.getString(5));
                o5.add(cursor.getString(6));
                rightAnswer.add(cursor.getString(7));
            }
        }
    }

}