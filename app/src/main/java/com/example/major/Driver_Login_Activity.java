package com.example.major;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class Driver_Login_Activity extends AppCompatActivity {
    EditText email,password;
    Button btnLogin;
    TextView forgot;
    TextView btnNewRegDvr;

    public ImageButton imageButton;
    AwesomeValidation awesomeValidation;
    DBHelper_Driver myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        awesomeValidation=new AwesomeValidation(BASIC);

        email=(EditText) findViewById(R.id.emaillogin);
        password=(EditText) findViewById(R.id.passwordlogin);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        forgot=(TextView) findViewById(R.id.btnforget);
        btnNewRegDvr=(TextView) findViewById(R.id.btnNewRegDvr);

        imageButton=(ImageButton) findViewById(R.id.imageButton);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(Driver_Login_Activity.this, R.id.emaillogin, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(Driver_Login_Activity.this, R.id.passwordlogin,regexPassword,R.string.password);


        myDB=new DBHelper_Driver(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {

                    String emaila = email.getText().toString();
                    String pass = password.getText().toString();
                    if (emaila.equals("") || pass.equals("")) {
                        Toast.makeText(Driver_Login_Activity.this, "Please enter the Credentials.", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean result = myDB.checkemailPassword(emaila, pass);
                        if (result) {
                            Intent intent = new Intent(getApplicationContext(), DriverDashboardActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Driver_Login_Activity.this, "Invalid Crediatials", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "invalid ", Toast.LENGTH_SHORT).show();
                }
            }

        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Driver_Reset_Password.class);
                startActivity(intent);
            }
        });

        btnNewRegDvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Driver_Registration_Activity.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Driver_Login_Activity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}