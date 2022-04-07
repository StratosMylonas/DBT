package com.example.stratos.dbtv02;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class NewMeasurement extends Activity{
    Button saveButton;
    EditText txtNewMeasurement;

    @Override
    public void onCreate(Bundle mSavedInstanceState) {
        super.onCreate(mSavedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_measurement);

        txtNewMeasurement = findViewById(R.id.txtNewMeasurement);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> {
            String editText = txtNewMeasurement.getText().toString();
            if (editText.isEmpty()){
                Toast.makeText(this, "Please enter a new measurement", Toast.LENGTH_SHORT).show();
            }
            else if (editText.matches("[0-9]+") && editText.length() >= 2){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewMeasurement.this, R.style.MyAlertDialogStyle);
                alertDialogBuilder.setMessage("Confirm Measurement?");
                alertDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    DatabaseHelper myDb;
                    myDb = new DatabaseHelper(NewMeasurement.this);
                    boolean ret = myDb.insertData(Integer.parseInt(editText), " ");
                    if (!ret){
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            else{
                Toast.makeText(this, "Invalid value entered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}