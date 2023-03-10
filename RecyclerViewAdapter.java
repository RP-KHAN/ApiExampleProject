package com.example.apiexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<GetModel> allApiData;
    RecyclerViewAdapter(Context context,ArrayList<GetModel> alApiData){
        this.context = context;
        this.allApiData = alApiData;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_for_api,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.userId.setText(allApiData.get(position).getUserId());
        holder.iD.setText(allApiData.get(position).getId());
        holder.titleUser.setText(allApiData.get(position).getTitle());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
                apiInterface.deleteData(allApiData.get(position).getId());

                allApiData.remove(position);
                Toast.makeText(context,"Deleted data",Toast.LENGTH_LONG);
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public int getItemCount() {
        return allApiData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId,iD,titleUser;
        Button deleteButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.user_id);
            iD = itemView.findViewById(R.id.id);
            titleUser = itemView.findViewById(R.id.title);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
