package com.example.major;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    public Button userbutton;
    public Button driverbutton,btnmap,btnhospital;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        userbutton=(Button) findViewById(R.id.btnUser);
        driverbutton=(Button)findViewById(R.id.btndriver);
        btnmap=(Button)findViewById(R.id.btnmap);
        btnhospital=(Button)findViewById(R.id.btnhospital);



        userbutton.setOnClickListener(view ->
        {
            final Intent i = new Intent(HomePageActivity.this,User_Login_Activity.class);
            startActivity(i);
            finish();
        });

        driverbutton.setOnClickListener(view ->
        {
            final Intent i = new Intent(HomePageActivity.this,Driver_Login_Activity.class);
            startActivity(i);
            finish();
        });

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(HomePageActivity.this,MapActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(HomePageActivity.this,Hospital_Login_Activity.class);
                startActivity(i);
                finish();
            }
        });


    }
}