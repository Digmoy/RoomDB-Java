package com.example.roomdb_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdb_java.Database.UserDataBase;
import com.example.roomdb_java.Model.UserModel;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,phone,email;
    Button btn_addUser;
    String strName,strPhone,strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();

        btn_addUser.setOnClickListener(this);
    }

    private void initView() {
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        btn_addUser = findViewById(R.id.btn_addUser);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_addUser :

                strName = name.getText().toString().trim();
                strPhone = phone.getText().toString().trim();
                strEmail = email.getText().toString().trim();

                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(strName))
                {
                    Toast.makeText(UserActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                    focusView = name;
                    cancel = true;
                }
                else if (TextUtils.isEmpty(strPhone))
                {
                    Toast.makeText(UserActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                    focusView = phone;
                    cancel = true;
                }
                else if (!isPhoneValid(strPhone))
                {
                    Toast.makeText(UserActivity.this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                    focusView = phone;
                    cancel = true;
                }
                else if (TextUtils.isEmpty(strEmail))
                {
                    Toast.makeText(UserActivity.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                    focusView = email;
                    cancel = true;
                }
                else if (!isEmailValid(strEmail))
                {

                    Toast.makeText(UserActivity.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    focusView = email;
                    cancel = true;
                }
                if (cancel) {

                    if (focusView != null)
                        focusView.requestFocus();
                }
                else {
                        addUserIntoDataBase();
                }
                break;
        }
    }

    private void addUserIntoDataBase() {

        new InsertTask().execute();
    }

    private class InsertTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            UserDataBase.getDatabase(UserActivity.this).userDAO().addUser(new UserModel(strName,strPhone,strEmail));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onBackPressed();
        }
    }

    private boolean isPhoneValid(String phone) {

        return phone.length() > 9;
    }
    private boolean isEmailValid(String email) {

        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}