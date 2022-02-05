package app.simpleloginapp.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView username ,home, login;
    EditText pass,repass;
    Button btnconfirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_reset);

        username = findViewById(R.id.username_reset_text);
        pass= findViewById(R.id.password_reset);
        repass= findViewById(R.id.repassword_reset);
        btnconfirm= findViewById(R.id.btnconfirm);
        DB = new DBHelper(this);

        Intent intent= getIntent();
        username.setText(intent.getStringExtra("username"));

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword= repass.getText().toString();
                if (password.equals(repassword)){



                    Boolean checkpasswordupdate = DB.updatepassword(user,password);
                    if (checkpasswordupdate==true){
                        Intent intent = new Intent(getApplicationContext(),finalhome.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this,"Password Update Successfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ResetActivity.this,"Password NotUpdated",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ResetActivity.this,"Password Notmatching",Toast.LENGTH_SHORT).show();
                }

            }
        });
        home = findViewById(R.id.hm);
        home.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(ResetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login= findViewById(R.id.lg);
        login.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(ResetActivity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });
    }
}