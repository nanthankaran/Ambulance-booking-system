package com.example.major;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class User_Reset_password extends AppCompatActivity {

    EditText email;
    Button reset;
    public ImageButton imageButton;
    AwesomeValidation awesomeValidation;

    DBHelper_User DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_forgotpassword_activity);
        awesomeValidation=new AwesomeValidation(BASIC);


        email =(EditText) findViewById(R.id.emailreset);
        reset =(Button) findViewById(R.id.btnreset);
        awesomeValidation.addValidation(User_Reset_password.this, R.id.emailreset, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);

        DB = new DBHelper_User(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {


                    String email_add = email.getText().toString();

                    Boolean checkemail = DB.checkemail(email_add);
                    if (checkemail = true) {
                        Intent intent = new Intent(getApplicationContext(), User_forgot_password.class);
                        intent.putExtra("email", email_add);
                        startActivity(intent);
                    } else {
                        Toast.makeText(User_Reset_password.this, "user does not exists", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "invalid ", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}