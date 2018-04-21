package com.example.administrator.attandancemgmtsys;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class View_Attendance extends AppCompatActivity {



    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    Spinner sub,prac,batchh;
    EditText num;
    //Note make seperate sub prac and extra
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__attendance);

        dateView = (TextView)findViewById(R.id.DateAtt);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        sub = (Spinner)findViewById(R.id.subject);
        prac =(Spinner)findViewById(R.id.spinner3);
        batchh=(Spinner)findViewById(R.id.Batch);
        num=(EditText)findViewById(R.id.rollnum);



    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void gotoShow(View view){
        String bat=batchh.getSelectedItem().toString(),s=sub.getSelectedItem().toString(),p=prac.getSelectedItem().toString();
        String rnum=num.getText().toString(),date=dateView.getText().toString();
        Intent intent = new Intent(this,ShowAttendance.class);
        intent.putExtra("batch",bat);
        intent.putExtra("subj",s);
        intent.putExtra("prac",p);
        intent.putExtra("num",rnum);
        intent.putExtra("date",date);
        startActivity(intent);
    }

}