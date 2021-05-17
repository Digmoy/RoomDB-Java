package com.example.roomdb_java.RoomLivedataViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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

import java.util.Objects;

public class UpdateSkillActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText skill_name,skill_experience;
    private Button btn_updateSkill;
    private SkillViewModel skillViewModel;
    private LiveData<SkillModel> skillModelLiveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_skill);

        initView();

        skillViewModel  = ViewModelProviders.of(UpdateSkillActivity.this).get(SkillViewModel.class);

        if(getIntent().hasExtra("id")){
            int todoId = getIntent().getIntExtra("id", -1);
            skillModelLiveData = skillViewModel.getSkill(todoId);

            skillModelLiveData.observe(UpdateSkillActivity.this, new Observer<SkillModel>() {
                @Override
                public void onChanged(@Nullable SkillModel skillModel) {
                    if (skillModel != null) {
                        skill_name.setText(skillModel.getSkill_name());
                        skill_experience.setText(skillModel.getExperience());
                    }
                }
            });
        }

        btn_updateSkill.setOnClickListener(this);
    }

    private void initView() {
        skill_name = findViewById(R.id.skill_name);
        skill_experience = findViewById(R.id.skill_experience);
        btn_updateSkill = findViewById(R.id.btn_updateSkill);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_updateSkill:
                    String name = skill_name.getText().toString().trim();
                    String exp = skill_experience.getText().toString().trim();

                    Objects.requireNonNull(skillModelLiveData.getValue()).setSkill_name(name);
                    Objects.requireNonNull(skillModelLiveData.getValue()).setExperience(exp);
                    skillViewModel.update(skillModelLiveData.getValue()).observe(UpdateSkillActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(UpdateSkillActivity.this, s, Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    });

                break;
        }
    }
}