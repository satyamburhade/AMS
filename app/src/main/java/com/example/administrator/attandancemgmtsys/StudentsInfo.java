package com.example.administrator.attandancemgmtsys;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentsInfo extends AppCompatActivity {
    DBHelper myDb;
    EditText rnum,name;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_info);
        myDb = new DBHelper(this);
        rnum=(EditText)findViewById(R.id.RollNum);
        name = (EditText)findViewById(R.id.FullName);
        spinner=(Spinner)findViewById(R.id.AddBatch);
    }
    public void AddData(View view)
    {
        if (rnum.getText().toString().trim().length() == 0)
        {
            rnum.setError("Please Enter User Name");
            rnum.requestFocus();
        }
        else if (name.getText().toString().length() == 0){
            name.setError("Please Enter User Name");
            name.requestFocus();
        }
        else {
            boolean isInserted= myDb.insertStudentData(rnum.getText().toString(),name.getText().toString(),spinner.getSelectedItem().toString());
        if (isInserted==true){
            Toast.makeText(StudentsInfo.this,"record inserted",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(StudentsInfo.this,""+rnum.getText().toString()+" is already exists",Toast.LENGTH_LONG).show();
        }
        }
    }

    public void showMessage(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);//can cancel it after use
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();

    }
    public void ViewData(View view)
    {

        Cursor res=myDb.DisplayStudInfo();//res have some property
        if(res.getCount()==0)
        {
            //Error messagr
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
                buffer.append("Name:"+res.getString(1)+"\n");
                buffer.append("Batch:"+res.getString(2)+"\n");
            }
            //show all data
            showMessage("Data ",buffer.toString());
        }
    }
}
