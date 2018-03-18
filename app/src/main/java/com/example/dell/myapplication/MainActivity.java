package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

// new teacher
public class MainActivity extends AppCompatActivity {
    EditText ET_NAME,ET_PHONE, ET_DOF ,ET_CODE;
    String save_name,save_phone,save_dof,save_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_NAME = (EditText)findViewById(R.id.Tname);
        ET_PHONE = (EditText)findViewById(R.id.TPhone);
        ET_DOF = (EditText)findViewById(R.id.Tdof);
        ET_CODE = (EditText)findViewById(R.id.Tcode);

    }



    public void Tsave(View view)
    {
        save_name = ET_NAME.getText().toString();
        save_phone  = ET_PHONE.getText().toString();
        save_dof =ET_DOF.getText().toString();
        save_code  =ET_CODE.getText().toString();


        String method = "save";
        Teacher1 teacher = new Teacher1(this);
        teacher.execute(method,save_name,save_phone,save_dof,save_code);
    }
}



