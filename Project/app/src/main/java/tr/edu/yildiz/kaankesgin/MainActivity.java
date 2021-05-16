package tr.edu.yildiz.kaankesgin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassw;
    private TextView userPasswError;
    private TextView userEmailError;
    private Button signInButton;
    private Button signUpButton;
    DatabaseHelper DB;
    static ArrayList<Person> persons;
    static Intent intent2;
    Person LoggedIn;
    int counter = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);

        //db.addUser(R.drawable.avatar2,"Kaan","Kesgin","kaank@kaank.com","5343980185","04/06/1999","123");
        defineVariables();
        defineListeners();
    }

    public void defineVariables() {
        userEmail = findViewById(R.id.userEmail);
        userPassw = findViewById(R.id.userPassw);
        userPasswError = findViewById(R.id.userPasswError);
        userEmailError = findViewById(R.id.userEmailError);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        persons = Person.getPersonList();

    }

    private boolean checkLogIn(){
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);


        /*for(Person x : persons){
            if(userEmail.getText().toString().equals(x.getEmail()) && userPassw.getText().toString().equals(x.getPassw())){
               LoggedIn = x;
                return true;

            }
        }*/
        boolean result = db.checkUser(userEmail.getText().toString(),userPassw.getText().toString());

        return result;

    }

    public void defineListeners(){

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper database = new DatabaseHelper(MainActivity.this);


                Intent intent;
                if(userEmail.getText().toString().isEmpty() || !(userEmail.getText().toString().endsWith("@kaank.com"))){
                    userEmail.setError("Unvalid E-mail");
                    userEmail.getBackground().setColorFilter(getResources().getColor(R.color.red),PorterDuff.Mode.SRC_ATOP);
                    userEmailError.setText("Please insert a valid e-mail.");

                }else if(userPassw.getText().toString().isEmpty() || userPassw.getText().toString().length()<3){
                    userPassw.setError("Unvalid Password");
                    userPassw.getBackground().setColorFilter(getResources().getColor(R.color.red),PorterDuff.Mode.SRC_ATOP);
                    userPasswError.setText("Please use a valid password.");

                }else{
                if(checkLogIn()){
                    intent = new Intent(v.getContext(),LoginActivity.class);
                    intent.putExtra("userMail",userEmail.getText().toString());
                    userEmail.setText("");
                    userPassw.setText("");
                    startActivity(intent);
                }else{
                    if(counter > 0) {

                        Toast.makeText(MainActivity.this,"Wrong e-mail or password. " + counter + " remain",Toast.LENGTH_SHORT).show();
                        counter = counter - 1;
                    }else{
                        //userEmail.setError("You will no longer able to try. Try Sign Up");
                        signInButton.setEnabled(false);
                        Toast.makeText(MainActivity.this,"Sign up button is no longer avaible.\n Try to Sign Up ",Toast.LENGTH_SHORT).show();


                    }
                }
                }
            }
        });



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent2 = new Intent(v.getContext(),SignUpActivity.class);
                startActivity(intent2);
                counter= 2;
                signInButton.setEnabled(true);
            }
        });

        userEmail.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                userEmail.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                userEmailError.setText("");
                userEmail.setError(null);
                return false;
            }
        });

        userPassw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userPassw.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                userPasswError.setText("");
                userPassw.setError(null);
                return false;
            }
        });

     /*   userEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_DEL){
                    userEmail.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                }
                return false;
            }
        });

        userPassw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_DEL){
                    userPassw.getBackground().setColorFilter(getResources().getColor(R.color.teal_700), PorterDuff.Mode.SRC_ATOP);
                }
                return false;
            }
        });*/
    }
}






