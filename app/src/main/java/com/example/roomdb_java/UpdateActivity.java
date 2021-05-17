package com.example.roomdb_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdb_java.Database.UserDataBase;
import com.example.roomdb_java.Model.UserModel;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,phone,email;
    Button btn_updateUser;
    String strName,strPhone,strEmail;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        if (getIntent().hasExtra("id")) {
            String id = getIntent().getStringExtra("id");
            new UserDetailsTask(id).execute();
        }
        initView();

        btn_updateUser.setOnClickListener(this);
    }

    private void initView() {
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        btn_updateUser = findViewById(R.id.btn_updateUser);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_updateUser:
                    new UpdateUserTask().execute();
                break;
        }
    }

    private class UserDetailsTask extends AsyncTask<Void,Void,Void>{

        private String id;
        UserDetailsTask(String id){
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userModel = UserDataBase.getDatabase(UpdateActivity.this).userDAO().getSelectedUser(id);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setUserDetails();
        }
    }

    private class UpdateUserTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            userModel.setName(name.getText().toString().trim());
            userModel.setPhone(phone.getText().toString().trim());
            userModel.setEmail(email.getText().toString().trim());
            UserDataBase.getDatabase(UpdateActivity.this).userDAO().updateUser(userModel);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onBackPressed();
        }
    }

    private void setUserDetails() {
        name.setText(userModel.getName());
        phone.setText(userModel.getPhone());
        email.setText(userModel.getEmail());
    }
}