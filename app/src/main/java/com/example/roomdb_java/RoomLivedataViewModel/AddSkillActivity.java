package com.example.roomdb_java.RoomLivedataViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdb_java.R;
import com.example.roomdb_java.RoomLivedataViewModel.Model.SkillModel;
import com.example.roomdb_java.RoomLivedataViewModel.ViewModel.SkillViewModel;

public class AddSkillActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText skill_name,skill_experience;
   private Button btn_addSkill;
   private SkillViewModel skillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);

        initView();

        skillViewModel = ViewModelProviders.of(AddSkillActivity.this).get(SkillViewModel.class);

        btn_addSkill.setOnClickListener(this);

    }

    private void initView() {
        skill_name = findViewById(R.id.skill_name);
        skill_experience = findViewById(R.id.skill_experience);
        btn_addSkill = findViewById(R.id.btn_addSkill);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_addSkill:

                String name = skill_name.getText().toString().trim();
                String exp = skill_experience.getText().toString().trim();

                skillViewModel.insert(new SkillModel(name,exp)).observe(AddSkillActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(AddSkillActivity.this, s, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });

                break;
        }
    }
}