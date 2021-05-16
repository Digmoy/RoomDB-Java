package com.example.roomdb_java.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb_java.Model.UserModel;
import com.example.roomdb_java.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<UserModel> modelList;

    public UserAdapter(Context context, List<UserModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,phone,email;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            delete = itemView.findViewById(R.id.delete);
        }

        void setDataToFields(int position){
            name.setText(modelList.get(position).getName());
            phone.setText(modelList.get(position).getPhone());
            email.setText(modelList.get(position).getEmail());
        }
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.setDataToFields(position);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
