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

public class UserDashboardActivity extends AppCompatActivity {

    public Button requestbutton;
    public Button historybutton;
    public Button logoutbutton;
    public ImageButton imageButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        requestbutton=(Button) findViewById(R.id.btnrequest);

        historybutton=(Button) findViewById(R.id.btnhistory);

        logoutbutton=(Button) findViewById(R.id.btnlogout);

        imageButton=(ImageButton) findViewById(R.id.imageButton);

        DB = new DBHelper(this);

        requestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboardActivity.this,UserBookingActivity.class);
                startActivity(intent);
            }
        });

        historybutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = DB.getdata();
                        if (res.getCount() == 0) {
                            Toast.makeText(UserDashboardActivity.this, "No Ambulance Order", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :" + res.getString(0) + "\n");
                            buffer.append("Place :" + res.getString(1) + "\n");
                            buffer.append("Mobile :" + res.getString(2) + "\n");

                        }
                        AlertDialog.Builder bulider = new AlertDialog.Builder(UserDashboardActivity.this);
                        bulider.setCancelable(true);
                        bulider.setTitle("User History");
                        bulider.setMessage(buffer.toString());
                        bulider.show();
                    }
                });

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboardActivity.this,User_Login_Activity.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboardActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });



    }
}