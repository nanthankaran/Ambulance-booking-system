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

public class DriverDashboardActivity extends AppCompatActivity {

    public Button driverhistorybutton;
    public Button driverlogoutbutton;
    public Button driverviewbutton;
    public ImageButton imageButton;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);

        driverhistorybutton=(Button) findViewById(R.id.driverbtnhistory);
        driverlogoutbutton=(Button)findViewById(R.id.driverlogout);
        driverviewbutton=(Button)findViewById(R.id.btnaccept);

        imageButton=(ImageButton) findViewById(R.id.imageButton);
        DB = new DBHelper(this);


        driverviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(DriverDashboardActivity.this, "No Ambulance Order", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Place :" + res.getString(1) + "\n");
                    buffer.append("Mobile :" + res.getString(2) + "\n");

                }
                AlertDialog.Builder bulider = new AlertDialog.Builder(DriverDashboardActivity.this);
                bulider.setCancelable(true);
                bulider.setTitle("Requests");
                bulider.setMessage(buffer.toString());
                bulider.show();
            }
        });

        driverlogoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverDashboardActivity.this,Driver_Login_Activity.class);
                startActivity(intent);
            }
        });

        driverhistorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(DriverDashboardActivity.this, "No Ambulance Order", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Place :" + res.getString(1) + "\n");
                    buffer.append("Mobile :" + res.getString(2) + "\n");

                }
                AlertDialog.Builder bulider = new AlertDialog.Builder(DriverDashboardActivity.this);
                bulider.setCancelable(true);
                bulider.setTitle("Driver History");
                bulider.setMessage(buffer.toString());
                bulider.show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverDashboardActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });



    }
}