package com.cookandroid.app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    ArrayList<MainData> items;

    public MainAdapter(ArrayList<MainData> items) {
        this.items = items;
    }

    public void addItem(MainData item) {
        items.add(item);
        notifyDataSetChanged();
    }
    public MainData getItem(int position){return items.get(position);}


    //뷰홀더
    @NotNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.items_list,parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
            MainData item = items.get(position);
            holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

               TextView names;
               TextView foods;

        public CustomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.name);
            foods = itemView.findViewById(R.id.food);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(itemView.getContext(),subActivity.class);
                        intent.putExtra("name",getItem(position).get_name());
                        intent.putExtra("food",getItem(position).get_food());
                        intent.putExtra("position", position);

                        MainActivity.launcher.launch(intent);// 되돌아올때 원래화면 수정하기 위해

                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    items.remove(position);
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
        public void setItem(MainData item){
            names.setText(item.get_name());
            foods.setText(item.get_food());
        }


    }


}
