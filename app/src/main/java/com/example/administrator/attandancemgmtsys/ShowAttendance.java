package com.example.administrator.attandancemgmtsys;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class ShowAttendance extends AppCompatActivity {
    DBHelper myDb;
    String su,pr,b,num,dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);
        myDb = new DBHelper(this);

        Bundle data=getIntent().getExtras();
        b=data.getString("batch");
        su=data.getString("subj");
        pr=data.getString("prac");
        num=data.getString("num");
        dat=data.getString("date");

        viewPoli();

    }

    public void viewPoli()
    {

        Cursor res=myDb.ShowAtt(su,pr,num,dat);//res have some property
        if(res.getCount()==0)
        {
            //message
            showMessage("Error","No data to show");
            return;
        }
        else
        {
            //create some string buffer
            StringBuffer buffer=new StringBuffer();
            while (res.moveToNext())
            {
                buffer.append("RollNo:"+res.getString(0)+"\n");
                buffer.append("Attendance:"+res.getString(1)+"\n\n");
            }
            //show all data
            showMessage("Data ",buffer.toString());
        }
    }
    public void showMessage(String title,String msg)
    {
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);//can cancel it after use
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }
}
