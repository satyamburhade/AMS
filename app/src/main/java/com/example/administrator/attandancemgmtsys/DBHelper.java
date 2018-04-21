package com.example.administrator.attandancemgmtsys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="AMS.db";

    // USER TABLE COLUMNS
    public static final String USER_TABLE="UserDetails";
    public static final String SrNo="SrNo";
    public static final String Name="Name";
    public static final String Contact="Contact";
    public static final String Username="UserName";
    public static final String Pass="Password";
    public static final String isActive="Active";
    public static final String Rolls="Rolls";
    public static final String Subjects="Subjects";
    public static final String Practicals="Practicals";

    // STUDENT TABLE COLUMNS of one class only
    public static final String STUDENT_TABLE="StudentAtt";
    public static final String RollNo="RollNo";
    public static final String Stud_Name="Name";
    public static final String Batchh="Batch";


    public static final String Subject1_Table="Sub1";
    public static final String RollNO1="RollNo";
    public static final String Stud_name1="Name";

    public static final String Subject2_Table="Sub2";
    public static final String RollNO2="RollNo";
    public static final String Stud_name2="Name";

    public static final String Subject3_Table="Sub3";
    public static final String RollNO3="RollNo";
    public static final String Stud_name3="Name";

    public static final String Practical1_Table="Prac1";
    public static final String PRollNO1="RollNo";
    public static final String PStud_name1="Name";

    public static final String Practical2_Table="Prac2";
    public static final String PRollNO2="RollNo";
    public static final String PStud_name2="Name";

    public static final String Practical3_Table="Prac3";
    public static final String PRollNO3="RollNo";
    public static final String PStud_name3="Name";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+USER_TABLE+" ("+SrNo+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Name+" text,"+Contact+" integer," +
                ""+Username+" text,"+Pass+" text,"+Rolls+" text,"+Subjects+" text,"+Practicals+" text,"+isActive+" text)");


        db.execSQL("create table "+STUDENT_TABLE+" ("+RollNo+" INTEGER primary key,"+Stud_Name+" text," +
                ""+Batchh+" text)");

        db.execSQL("create table "+Subject1_Table+" ("+RollNO1+" Inreger,"+Stud_name1+" text)");

        db.execSQL("create table "+Subject2_Table+" ("+RollNO2+" Inreger,"+Stud_name2+" text)");

        db.execSQL("create table "+Subject3_Table+" ("+RollNO3+" Inreger,"+Stud_name3+" text)");

        db.execSQL("create table "+Practical1_Table+" ("+PRollNO1+" Inreger,"+PStud_name1+" text)");

        db.execSQL("create table "+Practical2_Table+" ("+PRollNO2+" Inreger,"+PStud_name2+" text)");

        db.execSQL("create table "+Practical3_Table+" ("+PRollNO3+" Inreger,"+PStud_name3+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Subject1_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Subject2_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Subject3_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Practical1_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Practical2_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Practical3_Table);
        onCreate(db);
    }
    public boolean insertUserData(String name,String contact,String uname,String pass,String rolls,String sub,String prac,String active){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Contact,contact);
        contentValues.put(Username,uname);
        contentValues.put(Pass,pass);
        contentValues.put(Rolls,rolls);
        contentValues.put(Subjects,sub);
        contentValues.put(Practicals,prac);
        contentValues.put(isActive,active);
        long result= db.insert(USER_TABLE,null,contentValues);//this method will return rows affected
        //if no rows affected it will return -1
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData(String uname)//cursor class provied random read write
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+USER_TABLE+" where "+Username+" = '"+uname+"' and "+isActive+" = 'yes' ",null);
        return res;
    }
    public Cursor getData()//cursor class provied random read write
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select "+Username+" from "+USER_TABLE+" ",null);
        return res;
    }
    public Cursor DisplayAllUsers()//cursor class provied random read write
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+USER_TABLE+" ",null);
        return res;
    }

    public boolean access(String who,String change)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(isActive,change);

        db.update(USER_TABLE,contentValues,""+Username+" = '"+who+"'",null);
        return true;
    }

    public boolean rollUpdate(String who,String change)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Rolls,change);

        db.update(USER_TABLE,contentValues,""+Username+"='"+who+"'",null);
        return true;
    }


    public boolean AssignSub(String who,String change)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(Subjects,change);

        db.update(USER_TABLE,contentValues,""+Username+"='"+who+"'",null);
        return true;
    }
    public boolean AssignPrac(String who,String change)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Practicals,change);

        db.update(USER_TABLE,contentValues,""+Username+"='"+who+"'",null);
        return true;
    }
    public Integer  deleteData(String who)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(USER_TABLE,""+Username+"='"+who+"'", null );

    }



    //              STUDENTS DATABASE



    public boolean insertStudentData(String rollnum,String studname,String batch){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(RollNo,rollnum);
        contentValues.put(Stud_Name,studname);
        contentValues.put(Batchh,batch);
        try {
            long result = db.insert(STUDENT_TABLE, null, contentValues);//this method will return rows affected
        //if no rows affected it will return -1
        ContentValues c=new ContentValues();
        c.put("RollNo",rollnum);
        c.put("Name",studname);
        long sub1= db.insert(Subject1_Table,null,c);
        long sub2= db.insert(Subject2_Table,null,c);
        long sub3= db.insert(Subject3_Table,null,c);
        long prac1= db.insert(Practical1_Table,null,c);
        long prac2= db.insert(Practical2_Table,null,c);
        long prac3= db.insert(Practical3_Table,null,c);
        if (result==-1 && sub1==-1 && sub2==-1 && sub3==-1 && prac1==-1 && prac2==-1 && prac3==-1){
            return false;
        }
        else {
            return true;
        }
        }catch (Exception e){return false;}
    }

    public Cursor DisplayStudInfo()//cursor class provied random read write
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+STUDENT_TABLE+" ",null);
        return res;
    }
   /* public boolean TakepracPAt(String num,String prac,String date) // for present student input
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues absent=new ContentValues();
        ContentValues present=new ContentValues();

        if (prac.contentEquals("Prac1")) {
            try {
                db.execSQL("Alter table " + Practical1_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical1_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Practical1_Table, absent, "" + RollNo + "!='" + num + "'", null);
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac2")) {
            try {
                db.execSQL("Alter table " + Practical2_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical2_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Practical2_Table, absent, "" + RollNo + "!='" + num + "'", null);
            }catch (SQLiteException e){
                return false;
            }
        }
        else {
            try {
                db.execSQL("Alter table " + Practical3_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical3_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Practical3_Table, absent, "" + RollNo + "!='" + num + "'", null);
            }catch (SQLiteException e){
                return false;
            }
        }
        return true;
    }*/
    public boolean TakesubPAt(String num,String sub,String prac,String date)//for present student input
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues absent=new ContentValues();
        ContentValues present=new ContentValues();

        if (sub.contentEquals("Sub1")) {
            try {
                db.execSQL("Alter table " + Subject1_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Subject1_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Subject1_Table, absent, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (sub.contentEquals("Sub2")) {
            try {
                db.execSQL("Alter table " + Subject2_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Subject2_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Subject2_Table, absent, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (sub.contentEquals("Sub3")) {
            try {
                db.execSQL("Alter table " + Subject3_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Subject3_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Subject3_Table, absent, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        if (prac.contentEquals("Prac1")) {
            try {
                db.execSQL("Alter table " + Practical1_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical1_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Practical1_Table, absent, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac2")) {
            try {
                db.execSQL("Alter table " + Practical2_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical2_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Practical2_Table, absent, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac3")){
            try {
                db.execSQL("Alter table " + Practical3_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical3_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Practical3_Table, absent, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        return false;
    }

    /*public boolean TakepracAAt(String num,String prac,String date) // for absent student input
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues absent=new ContentValues();
        ContentValues present=new ContentValues();

        if (prac.contentEquals("Prac1")) {
            try {
                db.execSQL("Alter table " + Practical1_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical1_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Practical1_Table, present, "" + RollNo + "!='" + num + "'", null);
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac2")) {
            try {
                db.execSQL("Alter table " + Practical2_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical2_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Practical2_Table, present, "" + RollNo + "!='" + num + "'", null);
            }catch (SQLiteException e){
                return false;
            }
        }
        else {
            try {
                db.execSQL("Alter table " + Practical3_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical3_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Practical3_Table, present, "" + RollNo + "!='" + num + "'", null);
            }catch (SQLiteException e){
                return false;
            }
        }
        return true;
    }*/
    public boolean TakesubAAt(String num,String sub,String prac,String date)//for absent student input
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues absent=new ContentValues();
        ContentValues present=new ContentValues();

        if (sub.contentEquals("Sub1")) {
            try {
                db.execSQL("Alter table " + Subject1_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Subject1_Table, present, "" + RollNo + "='" + num + "'", null);
                db.update(Subject1_Table, present, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (sub.contentEquals("Sub2")) {
            try {
                db.execSQL("Alter table " + Subject2_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Subject2_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Subject2_Table, present, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (sub.contentEquals("Sub3")){
            try {
                db.execSQL("Alter table " + Subject3_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Subject3_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Subject3_Table, present, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac1")) {
            try {
                db.execSQL("Alter table " + Practical1_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical1_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Practical1_Table, present, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac2")) {
            try {
                db.execSQL("Alter table " + Practical2_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical2_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Practical2_Table, present, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        else if (prac.contentEquals("Prac3")){
            try {
                db.execSQL("Alter table " + Practical3_Table + " add column '" + date + "' text default null");
                absent.put("'" + date + "'", "0");
                present.put("'" + date + "'", "1");
                db.update(Practical3_Table, absent, "" + RollNo + "='" + num + "'", null);
                db.update(Practical3_Table, present, "" + RollNo + "!='" + num + "'", null);
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        return false;
    }
    public Cursor ShowAtt(String sub,String prac,String num,String date)//cursor class provied random read write
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = null;
        if (num.contentEquals("0")) {
            if (sub.contentEquals("Sub1")) {
                try {
                     res=db.rawQuery("select RollNo,"+date+" from "+Subject1_Table+" ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (sub.contentEquals("Sub2")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Subject2_Table+" ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (sub.contentEquals("Sub3")){
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Subject3_Table+" ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (prac.contentEquals("Prac1")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Practical1_Table+" ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (prac.contentEquals("Prac2")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Practical2_Table+" ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (prac.contentEquals("Prac3")){
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Practical3_Table+" ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
        }
        else
        {
            if (sub.contentEquals("Sub1")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Subject1_Table+" where RollNo = '"+num+"' ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (sub.contentEquals("Sub2")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Subject2_Table+" where RollNo = '"+num+"' ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (sub.contentEquals("Sub3")){
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Subject3_Table+" where RollNo = '"+num+"' ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (prac.contentEquals("Prac1")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Practical1_Table+" where RollNo = '"+num+"' ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (prac.contentEquals("Prac2")) {
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Practical2_Table+" where RollNo = '"+num+"' ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
            else if (prac.contentEquals("Prac3")){
                try {
                    res=db.rawQuery("select RollNo,"+date+" from "+Practical3_Table+" where RollNo = '"+num+"' ",null);
                }catch (SQLiteException e){
                    return res;
                }
            }
        }
        return res;
    }

}
