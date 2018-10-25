package com.example.youngjung.dito.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youngjung.dito.Model.Info;
import com.example.youngjung.dito.R;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Info> roomList;

    public RoomAdapter(ArrayList<Info> roomList) {
        this.roomList = roomList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView r_name;
        TextView s_name;
        TextView cnt;
        ImageView img_master;
        ImageView img1, img2, img3;

        public MyViewHolder(@NonNull View view) {
            super(view);
            r_name = view.findViewById(R.id.tv_rname);
            s_name = view.findViewById(R.id.tv_sname);
            cnt = view.findViewById(R.id.tv_count);
            img_master = view.findViewById(R.id.ic_master);
            img1 = view.findViewById(R.id.img1);
            img2 = view.findViewById(R.id.img2);
            img3 = view.findViewById(R.id.img3);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.room,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.r_name.setText(roomList.get(i).getR_name());
        myViewHolder.s_name.setText(roomList.get(i).getS_name());
        myViewHolder.cnt.setText(roomList.get(i).getCnt());
        myViewHolder.img_master.setImageResource(roomList.get(i).getImg_master());
        myViewHolder.img1.setImageResource(roomList.get(i).getImg1());
        myViewHolder.img2.setImageResource(roomList.get(i).getImg1());
        myViewHolder.img3.setImageResource(roomList.get(i).getImg1());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }
}
