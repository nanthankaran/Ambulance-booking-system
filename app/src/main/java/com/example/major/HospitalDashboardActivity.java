package com.example.major;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HospitalDashboardActivity extends AppCompatActivity {


    public Button btnView;
    public Button btnlogout;
    public ImageButton imageButton;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_dashboard);


        btnlogout=(Button)findViewById(R.id.btnlogout);
        btnView=(Button)findViewById(R.id.btnView);
        imageButton=(ImageButton) findViewById(R.id.imageButton);
        DB = new DBHelper(this);


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(HospitalDashboardActivity.this, "No Ambulance Order", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Place :" + res.getString(1) + "\n");
                    buffer.append("Mobile :" + res.getString(2) + "\n");

                }
                AlertDialog.Builder bulider = new AlertDialog.Builder(HospitalDashboardActivity.this);
                bulider.setCancelable(true);
                bulider.setTitle("User Entries");
                bulider.setMessage(buffer.toString());
                bulider.show();
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalDashboardActivity.this,Hospital_Login_Activity.class);
                startActivity(intent);
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalDashboardActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });





    }
}