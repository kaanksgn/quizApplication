package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class quizSettingsActivity extends AppCompatActivity {
    SeekBar durationSeekBar,difficultySeekBar,questionPointSeekBar;
    TextView durationDisplayer,difficultyDisplayer,questionPointDisplayer;
    Button saveSettingsButton;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_settings);
        defineVariables();
    }

    void defineVariables(){
        shared = getSharedPreferences("tr.edu.yildiz.kaankesgin.shared",MODE_PRIVATE);
        editor = shared.edit();
        durationSeekBar = findViewById(R.id.durationSeekBar);
        durationDisplayer = findViewById(R.id.durationDisplayer);
        difficultySeekBar = findViewById(R.id.difficultySeekBar);
        difficultyDisplayer = findViewById(R.id.difficultyDisplayer);
        questionPointSeekBar = findViewById(R.id.questionPointSeekBar);
        questionPointDisplayer = findViewById(R.id.questionPointDisplayer);
        saveSettingsButton = findViewById(R.id.saveSettingsButton);
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                durationDisplayer.setText(Integer.toString(durationSeekBar.getProgress()));
                //durationSeekBar.getProgress()
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        difficultySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                difficultyDisplayer.setText(Integer.toString(difficultySeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        questionPointSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                questionPointDisplayer.setText(Integer.toString(questionPointSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("duration",Integer.toString(durationSeekBar.getProgress()));
                editor.putString("difficulty",Integer.toString(difficultySeekBar.getProgress()));
                editor.putString("questionPoint",Integer.toString(questionPointSeekBar.getProgress()));
                editor.commit();
                Toast.makeText(quizSettingsActivity.this, "Tercihleriniz Kaydedildi.", Toast.LENGTH_SHORT).show();

            }
        });


    }
}