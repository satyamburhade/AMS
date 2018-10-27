package com.example.administrator.attandancemgmtsys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TakeAtt extends AppCompatActivity {


    DBHelper myDb;
    private DatePicker datePicker;

    private Calendar calendar;

    private TextView dateView;
    private int year, month, day;
    Spinner sublist,praclist,batchlist;
    String sl,pl,bl;

        //Note make seperate subject practical and extras
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_take_att);
            sublist=(Spinner)findViewById(R.id.subject);
            myDb= new DBHelper(this);
            praclist=(Spinner)findViewById(R.id.practical);
            batchlist=(Spinner)findViewById(R.id.Batch);
            dateView = (TextView)findViewById(R.id.DateAtt);
             calendar = Calendar.getInstance();
              year = calendar.get(Calendar.YEAR);
              month = calendar.get(Calendar.MONTH);
               day = calendar.get(Calendar.DAY_OF_MONTH);
             showDate(year, month+1, day);
            sl=sublist.getSelectedItem().toString();
            pl=praclist.getSelectedItem().toString();
            bl=batchlist.getSelectedItem().toString();
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
        dateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    public void take(View view){

        if ((sl != "select sub" && pl.contentEquals("select prac"))
                || (sl.contentEquals("select sub") && pl != "select prac")
                && (bl != "select batch" && pl != "select prac")) {

            final EditText present = new EditText(this);
            final EditText absent = new EditText(this);
            final LinearLayout linearLayout = new LinearLayout(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Numbers");


            present.setInputType(InputType.TYPE_CLASS_TEXT);
            present.setHint("Present Numbers separated space");
            //present.setHintTextColor(030303);

            absent.setInputType(InputType.TYPE_CLASS_TEXT);
            absent.setHint("Put Absent Numbers separated space  ");
            //absent.setHintTextColor(030303);
            linearLayout.setBackgroundColor(0xFFFFFFFF);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(present);
            linearLayout.addView(new TextView(this));
            linearLayout.addView(absent);
            builder.setView(linearLayout);


// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (present.getText().toString().length() == 0) {
                        present.setHint("Present Numbers separate space ");
                    } else if (absent.getText().toString().length() > 0 && present.getText().toString().length() > 0) {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TakeAtt.this);
                        builder.setMessage("Dont use both at a time")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    else {
                        if (pl.contentEquals("select prac")){
                            String numbers[] = present.getText().toString().split("\\s* \\s*");
                            for (int i = 0; i < numbers.length; i++) {
                                String a = numbers[i];
                                boolean isInserted = myDb.TakesubPAt(a, sublist.getSelectedItem().toString(), praclist.getSelectedItem().toString(),dateView.getText().toString());
                                if (isInserted == true) {
                                    Toast.makeText(TakeAtt.this, "record updated ", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(TakeAtt.this, "record not updated", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        /*else if (sl.contentEquals("select sub")) {
                            String numbers[] = present.getText().toString().split("\\s* \\s*");
                            for (int i = 0; i < numbers.length; i++) {
                                String a = numbers[i];
                                boolean isInserted = myDb.TakepracPAt(a, praclist.getSelectedItem().toString(), dateView.getText().toString());
                                if (isInserted == true) {
                                    Toast.makeText(TakeAtt.this, "record dsdsdsdsd updated", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(TakeAtt.this, "record not wrwer updated", Toast.LENGTH_LONG).show();
                                }
                            }

                        }*/
                    }
                    if (absent.getText().toString().length() == 0) {
                        absent.setHint("Absent Numbers separate space ");
                    } else if (absent.getText().toString().length() > 0 && present.getText().toString().length() > 0) {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TakeAtt.this);
                        builder.setMessage("Dont use both at a time")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    } else {
                        if (pl.contentEquals("select prac")){
                            String numbers[] = present.getText().toString().split("\\s* \\s*");
                            for (int i = 0; i < numbers.length; i++) {
                                String a = numbers[i];
                                boolean isInserted = myDb.TakesubAAt(a, sublist.getSelectedItem().toString(),praclist.getSelectedItem().toString(), dateView.getText().toString());
                                if (isInserted == true) {
                                    Toast.makeText(TakeAtt.this, "record sasa updated", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(TakeAtt.this, "record not vsdv updated", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        //trials
                       /* else if (sl.contentEquals("select sub")) {
                            String numbers[] = present.getText().toString().split("\\s* \\s*");
                            for (int i = 0; i < numbers.length; i++) {
                                String a = numbers[i];
                                boolean isInserted = myDb.TakepracAAt(a, praclist.getSelectedItem().toString(), dateView.getText().toString());
                                if (isInserted == true) {
                                    Toast.makeText(TakeAtt.this, "record kkk updated", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(TakeAtt.this, "record not kkzx updated", Toast.LENGTH_LONG).show();
                                }
                            }

                        }*/
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
        else if (bl.contentEquals("select batch") && pl != "select prac"){
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TakeAtt.this);
            builder.setMessage("Please Choose Batch If You Want To Take Practical Attendance")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
        else {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TakeAtt.this);
            builder.setMessage("Dont Take Subject And Practical Attendance At a Time")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
    }


}
