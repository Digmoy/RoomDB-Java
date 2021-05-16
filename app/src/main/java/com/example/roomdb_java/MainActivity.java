package com.example.roomdb_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btn_crud.setOnClickListener(this);
    }

    private void initView() {
        btn_crud = findViewById(R.id.btn_crud);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_crud:
                startActivity(new Intent(MainActivity.this,CrudActivity.class));
                break;
        }
    }
}