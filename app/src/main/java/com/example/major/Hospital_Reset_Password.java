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

public class Hospital_Reset_Password extends AppCompatActivity {

    EditText email;
    Button reset;
    public ImageButton imageButton;
    AwesomeValidation awesomeValidation;
    DBHelper_Hospital DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_reset_password);
        awesomeValidation=new AwesomeValidation(BASIC);


        email =(EditText) findViewById(R.id.emailreset);
        reset =(Button) findViewById(R.id.btnreset);
        awesomeValidation.addValidation(Hospital_Reset_Password.this, R.id.emailreset, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);

        DB = new DBHelper_Hospital(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {


                    String email_add =email.getText().toString();

                Boolean checkemail = DB.checkemail(email_add);
                if(checkemail=true){
                    Intent intent = new Intent(getApplicationContext(),hospital_forgot_password.class);
                    intent.putExtra("email",email_add);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Hospital_Reset_Password.this, "user does not exists", Toast.LENGTH_SHORT).show();
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