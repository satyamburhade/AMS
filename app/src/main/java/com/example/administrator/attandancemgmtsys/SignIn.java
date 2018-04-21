package com.example.administrator.attandancemgmtsys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Administrator on 19-Mar-18.
 */

public class SignIn extends AppCompatActivity {
    DBHelper myDb;
    String m_Text;
    EditText Fname,Lname,Contact,Uname,Passwd;
    /*String[] numbers;
    String list[]={"<Select input>","Present Numbers","Absent Numbers"};
    Spinner spinner;
    ArrayAdapter<String> adapter;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        myDb=new DBHelper(this);//create a new instance
        Fname=(EditText)findViewById(R.id.Fname);
        Lname=(EditText)findViewById(R.id.Lname);
        Contact=(EditText)findViewById(R.id.Cnum);

        Uname=(EditText)findViewById(R.id.Uname);
        Passwd=(EditText)findViewById(R.id.Password);
        /*adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);*/
    }
    public void AddData(View view)
    {
        String name = Fname.getText().toString() + " " +Lname.getText().toString();

        boolean isInserted= myDb.insertUserData(name,Contact.getText().toString(),Uname.getText().toString(),Passwd.getText().toString(),null,null,null,"no");
        if (isInserted==true){
            Toast.makeText(SignIn.this,"record inserted",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,LogInPage.class));
        }
        else {
            Toast.makeText(SignIn.this,"record not inserted",Toast.LENGTH_LONG).show();
        }
    }
}
