package com.example.youngjung.dito.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.Info;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder2> {
    private ArrayList<member> item;
    Context context;

    public ProfileAdapter() {
    }

    public ProfileAdapter(ArrayList<member> item, Context context) {
        this.item = item;
        this.context = context;
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;

        public MyViewHolder2(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            img = view.findViewById(R.id.img);
        }
    }


    @NonNull
    @Override
    public ProfileAdapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile,parent,false);
          //context = viewGroup.getContext();
          MyViewHolder2 vh = new MyViewHolder2(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int i) {
        MyViewHolder2 myViewHolder2 = (MyViewHolder2) holder;
        String name = item.get(i).getName();
        String img_url = item.get(i).getSubnail();

        DefaultAppliction.img_glide(context,img_url,myViewHolder2.img);
        myViewHolder2.name.setText(name);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //아오 여기 size 안주고 기본값 0으로 줘서 계속 안나왔음. ㅅㅂ....
    @Override
    public int getItemCount() {
        return item.size();
    }
}
