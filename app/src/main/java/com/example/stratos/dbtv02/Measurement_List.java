package com.example.stratos.dbtv02;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Measurement_List extends AppCompatActivity {

    ListView mlist ;
    CustomListAdapter listadapter;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_measurment__list);

        myDb = new DatabaseHelper(this);

        mlist = (ListView)findViewById(R.id.list1);
        final String[] ids = get_col(0);
        String[] date = get_col(1);
        String[] mes = get_col(2);
        String[] types = get_col(3);

        if(date==null){
            return;

        }

        listadapter = new CustomListAdapter(this, mes,date, types);
        mlist.setAdapter(listadapter);
        mlist.setOnItemClickListener((adapterView, view, position, id) -> {

            AlertDialog alertDialog = new AlertDialog.Builder(Measurement_List.this).create();
            alertDialog.setTitle("Delete?");
            //alertDialog.setMessage("Alert message to be shown");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                    (dialog, which) -> {
                    myDb.deleteData(ids[position]);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        dialog.dismiss();
                    });
            alertDialog.show();
        });



    }

    protected String[] get_col (int col){
     //   arrayList = new ArrayList<String>();
        String[] data = new String[20];

        for (int i =0; i<20; i++){
            data[i]= " ";
        }

        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(Measurement_List.this, "No Measurements", Toast.LENGTH_SHORT).show();

            return null;
        }



        res.moveToLast();

        if(col == 2) {
            data[0] = res.getString(col) + " mg/dl";
            int i = 1;
            while (res.moveToPrevious()) {

                data[i] = res.getString(col) + " mg/dl";

                i++;
                if (i == 20) break;
            }

        }else {
            data[0] = res.getString(col);
            int i = 1;
            while (res.moveToPrevious()) {

                data[i] = res.getString(col);

                i++;
                if (i == 20) break;
            }
        }
            return data;
    }

}
