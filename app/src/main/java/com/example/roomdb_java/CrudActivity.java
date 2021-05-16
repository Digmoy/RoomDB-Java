package com.example.roomdb_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.roomdb_java.Adapter.UserAdapter;
import com.example.roomdb_java.Database.UserDataBase;
import com.example.roomdb_java.Model.UserModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CrudActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    RecyclerView rec_crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        initView();
        fab.setOnClickListener(this);
    }

    private void initView() {
        fab = findViewById(R.id.fab);
        rec_crud = findViewById(R.id.rec_crud);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab:
                    startActivity(new Intent(CrudActivity.this,UserActivity.class));
                break;
        }
    }

    public class ExecuteTask extends AsyncTask<Void,Void,Void>{
        List<UserModel> modelList;
        @Override
        protected Void doInBackground(Void... voids) {

            modelList = UserDataBase.getDatabase(CrudActivity.this).userDAO().getAllUsers();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            rec_crud.setHasFixedSize(true);
            rec_crud.setLayoutManager(new LinearLayoutManager(CrudActivity.this));
            rec_crud.setAdapter(new UserAdapter(CrudActivity.this,modelList));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ExecuteTask().execute();
    }
}