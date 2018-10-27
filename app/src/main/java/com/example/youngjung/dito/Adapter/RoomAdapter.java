package com.example.youngjung.dito.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.Info;
import com.example.youngjung.dito.R;
import com.example.youngjung.dito.View.Study1Activity;

import java.net.URI;
import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Info> roomList;
    Context context ;

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
        context = viewGroup.getContext();
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        String uri1 = roomList.get(i).getImg1();
        String uri2 = roomList.get(i).getImg2();
        String uri3 = roomList.get(i).getImg3();

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.r_name.setText(roomList.get(i).getR_name());
        myViewHolder.s_name.setText(roomList.get(i).getS_name());
        myViewHolder.cnt.setText(roomList.get(i).getCnt());

        if(roomList.get(i).getImg_master()==1) myViewHolder.img_master.setImageResource(R.drawable.icn_leader);
        else myViewHolder.img_master.setVisibility(View.GONE);
      //  myViewHolder.img1.setImageResource(roomList.get(i).getImg1());
      //  myViewHolder.img2.setImageResource(roomList.get(i).getImg1());
      //  myViewHolder.img3.setImageResource(roomList.get(i).getImg1());
        if(uri2==null) myViewHolder.img2.setVisibility(View.GONE);
        else myViewHolder.img2.setVisibility(View.VISIBLE);
        if(uri3==null) myViewHolder.img3.setVisibility(View.GONE);
        else myViewHolder.img3.setVisibility(View.VISIBLE);

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.icn_leader).centerCrop().circleCrop();
        Glide.with(context).load(uri1).transition(DrawableTransitionOptions.withCrossFade()).apply(requestOptions).into(myViewHolder.img1);
        Glide.with(context).load(uri2).transition(DrawableTransitionOptions.withCrossFade()).apply(requestOptions).into(myViewHolder.img2);
        Glide.with(context).load(uri3).transition(DrawableTransitionOptions.withCrossFade()).apply(requestOptions).into(myViewHolder.img3);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Study1Activity.class);
                context.startActivity(i);
            }
        });
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
