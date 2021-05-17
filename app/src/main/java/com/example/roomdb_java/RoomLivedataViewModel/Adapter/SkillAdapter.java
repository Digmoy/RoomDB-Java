package com.example.roomdb_java.RoomLivedataViewModel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb_java.R;
import com.example.roomdb_java.RoomLivedataViewModel.Model.SkillModel;
import com.example.roomdb_java.RoomLivedataViewModel.UpdateSkillActivity;
import com.example.roomdb_java.RoomLivedataViewModel.ViewModel.SkillViewModel;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.MyViewHolder> {

    private Context context;
    private List<SkillModel> modelList;
    private SkillViewModel skillViewModel;

    public SkillAdapter(Context context, List<SkillModel> modelList, SkillViewModel skillViewModel) {
        this.context = context;
        this.modelList = modelList;
        this.skillViewModel = skillViewModel;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView skill_name,skill_experience;
        ImageView delete;
        RelativeLayout ll_mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_mainLayout = itemView.findViewById(R.id.ll_mainLayout);
            delete = itemView.findViewById(R.id.delete);
            skill_experience = itemView.findViewById(R.id.skill_experience);
            skill_name = itemView.findViewById(R.id.skill_name);
        }

        void setData(int position){
            skill_name.setText(modelList.get(position).getSkill_name());
            skill_experience.setText(modelList.get(position).getExperience());
        }

        void onClickListener(){
            ll_mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context != null) {
                        int skillID = modelList.get(getAdapterPosition()).getId();
                        Intent intent = new Intent(context, UpdateSkillActivity.class);
                        intent.putExtra("id",skillID);
                        context.startActivity(intent);
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context != null) {
                        SkillModel skillModel = modelList.get(getAdapterPosition());
                        skillViewModel.delete(skillModel).observe((LifecycleOwner) context, new Observer<String>() {
                            @Override
                            public void onChanged(@Nullable String stringValue) {
                                Toast.makeText(context, stringValue, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public SkillAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skill_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.MyViewHolder holder, int position) {
        holder.setData(position);
        holder.onClickListener();
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
