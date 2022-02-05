package app.simpleloginapp.database;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class signUp_Activity extends AppCompatActivity {

    Button register;
    TextView ald, login, home;
    EditText ed1, ed2, ed3, ed4, ed5;
    AwesomeValidation awesomeValidation;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);


        awesomeValidation = new AwesomeValidation(BASIC);
        ed1 = (EditText) findViewById(R.id.et_username);
        ed2 = (EditText) findViewById(R.id.et_email);
        ed3 = (EditText) findViewById(R.id.et_password);
        ed4 = (EditText) findViewById(R.id.et_confirm);
        ed5 = (EditText) findViewById(R.id.et_phone);
        register = (Button) findViewById(R.id.rg);
        DB = new DBHelper(this);


        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String regexPhone = "^\\+[0-9]{10,13}$";

        awesomeValidation.addValidation(signUp_Activity.this, R.id.et_username, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(signUp_Activity.this, R.id.et_phone, regexPhone, R.string.err_tel);
        awesomeValidation.addValidation(signUp_Activity.this, R.id.et_email, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(signUp_Activity.this, R.id.et_password, regexPassword, R.string.err_password);

        awesomeValidation.addValidation(signUp_Activity.this, R.id.et_confirm, R.id.et_password, R.string.err_password_confirmation);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = ed1.getText().toString();
                String email = ed2.getText().toString();
                String pass = ed3.getText().toString();
                String confirm = ed4.getText().toString();
                String phone = ed5.getText().toString();
                if (user.equals("") || pass.equals("") || confirm.equals(""))

                    Toast.makeText(signUp_Activity.this, "please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(confirm)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertdata(user, pass);
                            if (insert == true) {
                                Toast.makeText(signUp_Activity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signUp_Activity.this, finalhome.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signUp_Activity.this, "Registeration failed", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(signUp_Activity.this, "user already exist!please sign in", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(signUp_Activity.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        ald = findViewById(R.id.already);
        ald.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(signUp_Activity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });

        login = findViewById(R.id.lgn1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signUp_Activity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });
        home = findViewById(R.id.hme1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signUp_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}