package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class photoPickerActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton avatar1, avatar2, avatar3, avatar4, avatar5, avatar6, avatar7, avatar8, tmp;
    Button avatarPickButton;
    TextView avatarText;
    static int id;
    public static int avatarValue;
    public static int  photoID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);
        defineVariables();
        defineListeners();
    }

    void defineVariables() {
        avatar1 = findViewById(R.id.image1);
        avatar2 = findViewById(R.id.image2);
        avatar3 = findViewById(R.id.image3);
        avatar4 = findViewById(R.id.image4);
        avatar5 = findViewById(R.id.image5);
        avatar6 = findViewById(R.id.image6);
        avatar7 = findViewById(R.id.image7);
        avatar8 = findViewById(R.id.image8);
        avatarPickButton = findViewById(R.id.avatarPickButton);
        avatarText = findViewById(R.id.avatarText);
        tmp = avatar1;

    }

    @Override
    public void onClick(View v) {

        if (v instanceof ImageButton) {


            tmp.setBackgroundColor(Color.rgb(255, 255, 255));
            ((ImageButton) v).setBackgroundColor(Color.rgb(140, 190, 214));
            tmp = (ImageButton) v;

            if (avatar1.equals(((ImageButton) v))) {
                id = 1;
            } else if (avatar2.equals(((ImageButton) v))) {
                id = 2;
            } else if (avatar3.equals(((ImageButton) v))) {
                id = 3;
            } else if (avatar4.equals(((ImageButton) v))) {
                id = 4;
            } else if (avatar5.equals(((ImageButton) v))) {
                id = 5;
            } else if (avatar6.equals(((ImageButton) v))) {
                id = 6;
            } else if (avatar7.equals(((ImageButton) v))) {
                id = 7;
            } else if (avatar8.equals(((ImageButton) v))) {
                id = 8;
            }
            avatarText.setText(String.valueOf(id)+". Avatar Seçildi");
        }
    }

    void defineListeners() {
        avatar1.setOnClickListener(this);
        avatar2.setOnClickListener(this);
        avatar3.setOnClickListener(this);
        avatar4.setOnClickListener(this);
        avatar5.setOnClickListener(this);
        avatar6.setOnClickListener(this);
        avatar7.setOnClickListener(this);
        avatar8.setOnClickListener(this);
        avatarPickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id){
                    case 1:
                        photoID = R.drawable.avatar2;
                        break;
                    case 2:
                        photoID = R.drawable.avatar3;
                        break;
                    case 3:
                        photoID = R.drawable.avatar4;
                        break;
                    case 4:
                        photoID = R.drawable.avatar9;
                        break;
                    case 5:
                        photoID = R.drawable.avatar6;
                        break;
                    case 6:
                        photoID = R.drawable.avatar7;
                        break;
                    case 7:
                        photoID = R.drawable.avatar8;
                        break;
                    case 8:
                        photoID = R.drawable.avatar5;
                        break;
                }

                 Intent intent = new Intent();
                 intent.putExtra("photoid",photoID);
                 setResult(Activity.RESULT_OK,intent);
                 finish();
            }
        });
    }
}