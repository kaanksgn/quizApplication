package tr.edu.yildiz.kaankesgin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
        private EditText signUpName;
        private EditText signUpSurname;
        private EditText signUpEmail;
        private EditText signUpPhone;
        private EditText signUpDate;
        private EditText signUpPassw;
        private Button buttonSignUp;
        private ImageView avatar ;
        private ImageButton changeAvatar;
        private ImageView signUpPhoto;
         int resultFromPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        defineVariables();
        defineListeners();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 2 ) {
            if(resultCode == Activity.RESULT_OK){
                 resultFromPicker=data.getIntExtra("photoid",resultFromPicker);
                signUpPhoto.setImageResource(resultFromPicker);

            }

        }
    }

    public void defineVariables(){
        signUpName = findViewById(R.id.signUpName);
        signUpSurname = findViewById(R.id.signUpSurname);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPhone = findViewById(R.id.signUpPhone);
        signUpDate = findViewById(R.id.signUpDate);
        signUpPassw = findViewById(R.id.signUpPassw);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        avatar = findViewById(R.id.signUpPhoto);
        changeAvatar = findViewById(R.id.changePhoto);
        signUpPhoto = findViewById(R.id.signUpPhoto);


    }

    public boolean controller(){
        boolean tmp = true;
        if(signUpName.getText().toString().length()<3){
            signUpName.setError("Enter a valid name");
            tmp = false;
        }

        if(signUpSurname.getText().toString().length()<3){
            signUpSurname.setError("Enter a valid surname");
            tmp = false;

        }

        if(!signUpEmail.getText().toString().endsWith("@kaank.com")){
            signUpDate.setError("Enter a valid e-mail.");
            tmp = false;
        }
        if(signUpPhone.getText().toString().length()!=13){
            signUpPhone.setError("Enter a valid phone number.");
            tmp = false;
        }
        Pattern patterdate = Pattern.compile("^[0-9]{1,2}+/+[0-9]{1,2}+/+[0-9]{4,4}");
        if(!patterdate.matcher(signUpDate.getText().toString()).matches()){
            signUpDate.setError("Enter a valid date. \nDD/MM/YYYY");
            tmp = false;
        }
        if(signUpPassw.getText().toString().length()<3){
            signUpPassw.setError("Password must be longer than 3 letters");
            tmp = false;
        }
        DatabaseHelper db = new DatabaseHelper(SignUpActivity.this);
        String tmpmail = signUpEmail.getText().toString();
        if(!db.checkNewUser(tmpmail)){
            signUpEmail.setError("Bu Email zaten kayıtlı.");
            signUpEmail.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            tmp = false;
        }

        return tmp;

    }
    public void defineListeners(){

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controller()){
                DatabaseHelper db = new DatabaseHelper(SignUpActivity.this);
                boolean addResult = db.addUser(resultFromPicker,signUpName.getText().toString(),signUpSurname.getText().toString(),signUpEmail.getText().toString(),signUpPhone.getText().toString(),signUpDate.getText().toString(),signUpPassw.getText().toString());
                if (addResult)
                    onBackPressed();
                }
            }
        });

        signUpPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        changeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int activity = 2;
                Intent intent = new Intent(v.getContext(),photoPickerActivity.class);
                startActivityForResult(intent,activity);

            }


        });


    }

}