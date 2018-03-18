package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
// current teacher

    EditText ET_EMAIL,ET_PHONE, ET_CODE;
    String save_email,save_phone,save_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ET_EMAIL = (EditText) findViewById(R.id.Temail);
        ET_PHONE = (EditText) findViewById(R.id.TPhone);
        ET_CODE = (EditText) findViewById(R.id.Tcode);

    }
    public void Tupdate(View view)
    {
        save_email = ET_EMAIL.getText().toString();
        save_phone  = ET_PHONE.getText().toString();
        save_code  =ET_CODE.getText().toString();


        String method = "update";
        Teacher1 teacher = new Teacher1(this);
        teacher.execute(method,save_email,save_phone,save_code);

    }

    }

