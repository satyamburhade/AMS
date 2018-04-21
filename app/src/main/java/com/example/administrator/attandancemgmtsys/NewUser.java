package com.example.administrator.attandancemgmtsys;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;


public class NewUser extends AppCompatActivity {

    Activity activity;
    DBHelper myDb;
    ListView listView;
    ArrayList<String> list= new ArrayList<String>(Arrays.asList("Users List"));
    ArrayAdapter<String> adapter;
    String userUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        listView= (ListView) findViewById(R.id.dynamic);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        viewAll();
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                userUpdate=listView.getItemAtPosition(position).toString();
                return false;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usermenu,menu);
    }
    public void accessPermission(MenuItem item){ // menu click method
        myDb = new DBHelper(this);

        boolean isInserted=myDb.access(userUpdate,"yes");//res have some property
        if (isInserted==true){
            Toast.makeText(NewUser.this,"record updated of user "+userUpdate+"",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(NewUser.this,"record not updated",Toast.LENGTH_LONG).show();
        }
    }
    public void denyAccess(MenuItem item){ // menu click method
        myDb = new DBHelper(this);

        boolean isInserted=myDb.access(userUpdate,"no");//res have some property
        if (isInserted==true){
            Toast.makeText(NewUser.this,"record updated",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(NewUser.this,"record not updated",Toast.LENGTH_LONG).show();
        }
    }
    public void AllocateSub(MenuItem item){ // menu click method
        myDb = new DBHelper(this);

        boolean isInserted=myDb.AssignSub(userUpdate,"no");//res have some property
        if (isInserted==true){
            Toast.makeText(NewUser.this,"record updated",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(NewUser.this,"record not updated",Toast.LENGTH_LONG).show();
        }
    }
    public void AllocatePrac(MenuItem item){ // menu click method
        myDb = new DBHelper(this);

        boolean isInserted=myDb.AssignPrac(userUpdate,"no");//res have some property
        if (isInserted==true){
            Toast.makeText(NewUser.this,"record updated",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(NewUser.this,"record not updated",Toast.LENGTH_LONG).show();
        }
    }

    public void classTeacher(MenuItem item){
        myDb = new DBHelper(this);

        boolean isInserted=myDb.rollUpdate(userUpdate,"Class Teacher");
        if (isInserted==true){
            Toast.makeText(NewUser.this,"record updated",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(NewUser.this,"record not updated",Toast.LENGTH_LONG).show();
        }
    }

    public void staff(MenuItem item){
        myDb = new DBHelper(this);

        boolean isInserted=myDb.rollUpdate(userUpdate,"Staff");
        if (isInserted==true){
            Toast.makeText(NewUser.this,"record updated",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(NewUser.this,"record not updated",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteUser(MenuItem item) {

        Integer deletedRows = myDb.deleteData(userUpdate);
        if (deletedRows > 0) {
            Toast.makeText(this, "record Deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "record not Deleted", Toast.LENGTH_LONG).show();

        }
    }
    public void viewAll()
    {
        myDb = new DBHelper(this);
        Cursor res=myDb.getData();//res have some property
        if(res.getCount()==0)
        {
            showMessage("!","No users show");
        }
        else
        {
            StringBuffer buffer=new StringBuffer();
            while (res.moveToNext())
            {
                adapter.notifyDataSetChanged();
                adapter.setNotifyOnChange(true);
                adapter.add(res.getString(0));
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
    public void viewPoli(View view)
    {

        Cursor res=myDb.DisplayAllUsers();//res have some property
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
                buffer.append("SrNo:"+res.getString(0)+"\n");
                buffer.append("Name:"+res.getString(1)+"\n");
                buffer.append("contact:"+res.getString(2)+"\n");
                buffer.append("user:"+res.getString(3)+"\n");
                buffer.append("pass:"+res.getString(4)+"\n");
                buffer.append("roll:"+res.getString(5)+"\n");
                buffer.append("subjects:"+res.getString(6)+"\n");
                buffer.append("practicals:"+res.getString(7)+"\n");
                buffer.append("status:"+res.getString(8)+"\n\n");
            }
            //show all data
            showMessage("Data ",buffer.toString());
        }
    }

}