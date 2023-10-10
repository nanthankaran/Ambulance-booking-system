package com.example.major;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class driver_forgot_password extends AppCompatActivity {


    TextView email;
    EditText pass,repass;
    Button confirm;
    AwesomeValidation awesomeValidation;

    DBHelper_Driver DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_forgot_password);
        awesomeValidation=new AwesomeValidation(BASIC);



        email = (TextView) findViewById(R.id.emailreset_text);
        pass = (EditText) findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm = (Button) findViewById(R.id.btnconfirm);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(driver_forgot_password.this, R.id.emailreset_text, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(driver_forgot_password.this, R.id.password_reset,regexPassword,R.string.password);

        awesomeValidation.addValidation(driver_forgot_password.this, R.id.repassword_reset, R.id.password_reset, R.string.err_password_confirmation);

        DB = new DBHelper_Driver(this);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {


                    String email_add = email.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if(password.equals(repassword))
                {

                    Boolean checkpasswordupdate = DB.updatepassword(email_add,password);
                    if(checkpasswordupdate=true)
                    {
                        Intent intent = new Intent(getApplicationContext(),Driver_Login_Activity.class);
                        startActivity(intent);
                        Toast.makeText(driver_forgot_password.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(driver_forgot_password.this, "Password NotUpdated", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(driver_forgot_password.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
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