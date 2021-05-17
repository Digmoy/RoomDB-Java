package com.example.roomdb_java.RoomLivedataViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roomdb_java.R;
import com.example.roomdb_java.RoomLivedataViewModel.Adapter.SkillAdapter;
import com.example.roomdb_java.RoomLivedataViewModel.Model.SkillModel;
import com.example.roomdb_java.RoomLivedataViewModel.ViewModel.SkillViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rec_list;
    private FloatingActionButton fab;
    private SkillViewModel skillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initView();
        skillViewModel = ViewModelProviders.of(this).get(SkillViewModel.class);

        rec_list.setHasFixedSize(true);
        rec_list.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        skillViewModel.getAllSkill().observe(ListActivity.this, new Observer<List<SkillModel>>() {
            @Override
            public void onChanged(List<SkillModel> skillModels) {
                rec_list.setAdapter(new SkillAdapter(ListActivity.this,skillModels,skillViewModel));
            }
        });

        fab.setOnClickListener(this);
    }

    private void initView() {

        rec_list = findViewById(R.id.rec_list);
        fab = findViewById(R.id.fab);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab:
                startActivity(new Intent(ListActivity.this,AddSkillActivity.class));
                break;
        }
    }
}