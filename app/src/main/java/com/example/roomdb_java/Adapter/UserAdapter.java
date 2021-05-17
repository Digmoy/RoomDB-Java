package com.example.roomdb_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb_java.Database.UserDataBase;
import com.example.roomdb_java.Model.UserModel;
import com.example.roomdb_java.R;
import com.example.roomdb_java.UpdateActivity;

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
        RelativeLayout ll_mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            delete = itemView.findViewById(R.id.delete);
            ll_mainLayout = itemView.findViewById(R.id.ll_mainLayout);
        }

        void setDataToFields(int position){
            name.setText(modelList.get(position).getName());
            phone.setText(modelList.get(position).getPhone());
            email.setText(modelList.get(position).getEmail());
        }

        void setOnClickListener(){
            ll_mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context != null)
                    {
                        String id = String.valueOf(modelList.get(getAdapterPosition()).getId());
                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("id",id);
                        context.startActivity(intent);
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DeleteUserTask(getAdapterPosition()).execute();
                }
            });
        }
    }

    private class DeleteUserTask extends AsyncTask<Void,Void,Void>{
        private int position;

        DeleteUserTask(int position) {
            this.position = position;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            UserDataBase.getDatabase(context).userDAO().deleteUser(modelList.get(position));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            modelList.remove(position);
            notifyItemRemoved(position);
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
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
