package com.example.administrator.attandancemgmtsys;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 25-Mar-18.
 */

public class Staff extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_page);
    }
    public void viewAttendance(View view){
        startActivity(new Intent(this,View_Attendance.class));
    }
    public void EditAttendance(View view){
        startActivity(new Intent(this,EditAtt.class));
    }
    public void takeAttendance(View view){
        startActivity(new Intent(this,TakeAtt.class));
    }

}
