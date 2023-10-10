package com.example.major;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserBookingActivity extends AppCompatActivity {

    EditText name, place, mobile,hospital;
    Button order;
    public ImageButton imageButton;


    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);


        name = findViewById(R.id.name);
        place = findViewById(R.id.place);
        mobile = findViewById(R.id.mobile);
        hospital = findViewById(R.id.hospital);







        order = findViewById(R.id.btnorder);

        imageButton=(ImageButton) findViewById(R.id.imageButton);
        DB = new DBHelper(this);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nametxt = name.getText().toString();
                String placetxt = place.getText().toString();
                String mobiletxt = mobile.getText().toString();
                String hospitaltxt = hospital.getText().toString();
                validateinfo(mobiletxt);


                Boolean checkinsertdata = DB.insertAmbdata(nametxt, placetxt, mobiletxt, hospitaltxt);
                if (checkinsertdata=true) {
                    Toast.makeText(UserBookingActivity.this, "Your Ambulance is booked", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserBookingActivity.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
                }





            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBookingActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });

    }

    private Boolean validateinfo(String mobiletxt) {
        if(mobiletxt.length()==0){
            mobile.requestFocus();
            mobile.setError("Field Cannot Be Empty");
            return false;
        }
        else if(mobiletxt.length()>10){
            mobile.requestFocus();
            mobile.setError("mobile number should be in ten digits only");
            return false;
        }
        else if(mobiletxt.length()<10){
            mobile.requestFocus();
            mobile.setError("mobile number should be in ten digits only");
            return false;

        }

        else {
            return true;
        }
    }


}