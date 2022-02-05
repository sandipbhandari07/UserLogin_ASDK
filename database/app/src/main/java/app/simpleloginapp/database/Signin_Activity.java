package app.simpleloginapp.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn_Activity extends AppCompatActivity {

    Button sn,forgot ;
    TextView dnt,home,register;
    DBHelper DB;
    EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_sign_in);




        sn =(Button) findViewById(R.id.signin);


        username = (EditText) findViewById(R.id.et_username2);
        password = (EditText) findViewById(R.id.et_password2);
        forgot = (Button) findViewById(R.id.btn_forget);


        DB = new DBHelper(this);
        sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("")||pass.equals(""))
                    Toast.makeText(SignIn_Activity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass= DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(SignIn_Activity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn_Activity.this, finalhome.class);
                        startActivity(intent); }
                    else{
                        Toast.makeText(SignIn_Activity.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        dnt = findViewById(R.id.dont);
        dnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignIn_Activity.this, "Register Here", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignIn_Activity.this, signUp_Activity.class);
                startActivity(intent);
            }
        });



        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), passwordActivity.class);
                startActivity(intent);
            }
        });

        home =findViewById(R.id.textView16);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        register=findViewById(R.id.textView15);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignIn_Activity.this,signUp_Activity.class);
                startActivity(intent);
            }
        });




    }
}