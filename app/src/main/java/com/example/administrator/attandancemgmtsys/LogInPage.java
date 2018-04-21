package com.example.administrator.attandancemgmtsys;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Administrator on 17-Mar-18.
 */

public class LogInPage extends AppCompatActivity {
    DBHelper myDb;
    String u,p;
    EditText uname,passwd;
    String list[]={"<Select User>","HOD","Class Teacher","Staff"};
    Spinner spinner;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_page);
        spinner = (Spinner)findViewById(R.id.spinner);
        myDb=new DBHelper(this);
        uname=(EditText)findViewById(R.id.Uname);
        passwd=(EditText)findViewById(R.id.Passwd);
        spinner = (Spinner)findViewById(R.id.spinner);
        p=passwd.getText().toString().trim();
        u=uname.getText().toString().trim();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);

    }
    public void signInClick(View view)
    {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }
    public void valid(String name,String pass,String sp,String roll)
    {
        if (u.contentEquals(name))
        {
            if (p.contentEquals(pass)){
                if(sp.contentEquals(roll)){
                    startActivity(new Intent(this,ClassTeacher.class));
                }
                else if(sp.contentEquals(roll)){
                    startActivity(new Intent(this,Staff.class));
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogInPage.this);
                    builder.setMessage("Your Not fall under "+spinner.getSelectedItem().toString()+" user type")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
            else{
                passwd.setError("Please Enter password");
                passwd.requestFocus();
            }
        }
        else{
            uname.setError("Please Enter valid username");
            uname.requestFocus();
        }
    }
    public void loginClick(View view)
    {
        String name;
        String pass;
        String roll;
        u=uname.getText().toString().trim();
        p=passwd.getText().toString().trim();
        String sp = spinner.getSelectedItem().toString();
        if (uname.getText().toString().trim().length() == 0)
        {
            uname.setError("Please Enter User Name");
            uname.requestFocus();
        }
        else if (passwd.getText().toString().trim().length() == 0){
            passwd.setError("Please Enter password");
            passwd.requestFocus();
        }
        else  if (sp.contentEquals("<Select User>"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(LogInPage.this);
            builder.setMessage("Please Select User type")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
            // Toast.makeText(this,"Please Select any option",Toast.LENGTH_LONG).show();
        }
        else if (sp.contentEquals("HOD"))
        {
            if(u.contentEquals("Admin")) {
                if (p.contentEquals("admin")){
                startActivity(new Intent(this, HOD.class));
                }
                else {
                    passwd.setError("Please Enter valid password");
                    passwd.requestFocus();
                }
            }
            else
            {
                uname.setError("Please Enter valid username");
                uname.requestFocus();
            }
        }
        else {
            Cursor res = myDb.getAllData(u);//res have some property
            if (res.getCount() == 0) {
                uname.setError("Please Enter Valid User Name");
                uname.requestFocus();
            } else {
                while (res.moveToNext()) {
                    name = res.getString(3);
                    pass = res.getString(4);
                    roll = res.getString(5);
                    valid(name, pass, sp,roll);
                }
            }
        }

    }
   /* public void viewPoli()
    {

        Cursor res=myDb.getData();//res have some property
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
                buffer.append("SrNo:"+res.getString(0)+"\n\n");
                buffer.append("Name:"+res.getString(1)+"\n");
                buffer.append("contact:"+res.getString(2)+"\n");
                buffer.append("user:"+res.getString(3)+"\n");
                buffer.append("pass:"+res.getString(4)+"\n");
                buffer.append("roll:"+res.getString(5)+"\n");
                buffer.append("status:"+res.getString(6)+"\n\n");
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

    }*/

}
