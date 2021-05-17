package com.example.roomdb_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomdb_java.RoomLivedataViewModel.ListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_crud,btn_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btn_crud.setOnClickListener(this);
        btn_viewModel.setOnClickListener(this);
    }

    private void initView() {
        btn_crud = findViewById(R.id.btn_crud);
        btn_viewModel = findViewById(R.id.btn_viewModel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_crud:
                startActivity(new Intent(MainActivity.this,CrudActivity.class));
                break;

            case R.id.btn_viewModel:
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                break;
        }
    }
}