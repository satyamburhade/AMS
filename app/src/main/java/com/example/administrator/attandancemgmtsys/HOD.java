package com.example.administrator.attandancemgmtsys;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 19-Mar-18.
 */

/*Add responsibility column in new user activity
* add responsibility column to login table and isActive column as int and check that column while login
*
* Add update user field in hod page*/

public class HOD extends AppCompatActivity {
    //Button New,Del,View1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hod_page);

    }
    /*public void setViews(){
        New = (Button)findViewById(R.id.New);
        Del = (Button)findViewById(R.id.Delet);
        View1 = (Button)findViewById(R.id.view_att);
    }*/
    public void click(View view)
    {
        startActivity(new Intent(this,Drawer.class));
    }

    public void forNewUser(View view){

        startActivity(new Intent(this,NewUser.class));
    }
    public void viewAttendance(View view){
        startActivity(new Intent(this,View_Attendance.class));
    }
}
