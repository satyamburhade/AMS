package com.example.administrator.attandancemgmtsys;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 25-Mar-18.
 */

public class ClassTeacher extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_teacher);
    }
    public void takeAttendance(View view){

        startActivity(new Intent(this,TakeAtt.class));
    }
    public void editAttendance(View view){

        startActivity(new Intent(this,EditAtt.class));
    }
    public void viewAttendance(View view){

        startActivity(new Intent(this,View_Attendance.class));
    }
    public void StudInfo(View view){

        startActivity(new Intent(this,StudentsInfo.class));
    }

}
//              FUTURE SCOPE
/*Student sign up so class teacher work will be reduced
  * Student can only view his attendance of all subject
   * class teacher can edit student information same like hod does*/


    /*          WORKING OF CURRENT

   * while taking attendance we can take sub + prac at a time
   * for prac attendance no need of batch column
   * for view attendance batch column is useful
   * check sub while taking attendance so we need to compare user_table values with takeAtt spinner values
   * same for prac
   * Add about us in right side upper corner menu*/
