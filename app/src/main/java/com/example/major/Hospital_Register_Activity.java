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

public class Hospital_Register_Activity extends AppCompatActivity {

    EditText email,password,repassword;
    Button btnSignUp;
    public ImageButton imageButton;
    AwesomeValidation awesomeValidation;

    DBHelper_Hospital myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);
        awesomeValidation=new AwesomeValidation(BASIC);

        email = (EditText)findViewById(R.id.email);
        password=(EditText) findViewById(R.id.pass);
        repassword=(EditText) findViewById(R.id.repass);

        btnSignUp=(Button) findViewById(R.id.btnSignUp);

        imageButton=(ImageButton) findViewById(R.id.imageButton);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(Hospital_Register_Activity.this, R.id.email, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(Hospital_Register_Activity.this, R.id.pass,regexPassword,R.string.password);

        awesomeValidation.addValidation(Hospital_Register_Activity.this, R.id.repass, R.id.pass, R.string.err_password_confirmation);



        myDB=new DBHelper_Hospital(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {


                    String emailaddr = email.getText().toString();
                    String pass = password.getText().toString();
                    String repass = repassword.getText().toString();

                    if (emailaddr.equals("") || pass.equals("") || repass.equals("")) {
                        Toast.makeText(Hospital_Register_Activity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        if (pass.equals(repass)) {
                            Boolean usercheckResult = myDB.checkemail(emailaddr);
                            if (usercheckResult == false) {
                                Boolean regResult = myDB.insertData(emailaddr, pass);
                                if (regResult == true) {
                                    Toast.makeText(Hospital_Register_Activity.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Hospital_Login_Activity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Hospital_Register_Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(Hospital_Register_Activity.this, "User already Exists.\n Please Sign In", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Hospital_Register_Activity.this, "Password not Matching", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "invalid ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hospital_Register_Activity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });



    }
}