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

public class passwordActivity extends AppCompatActivity {

    EditText username;
    Button reset;
    DBHelper DB;
    TextView home, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_password);

        username = (EditText) findViewById(R.id.username_reset);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new DBHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                Boolean checkuser = DB.checkusername(user);
                if (checkuser == true) {
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(passwordActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });


        home = findViewById(R.id.homey);
        home.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(passwordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login= findViewById(R.id.logie);
        login.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(passwordActivity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });
    }
}