package com.example.youngjung.dito.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;

public class Add1Adapter extends RecyclerView.Adapter<Add1Adapter.MyViewHolder> {
    private ArrayList<member> item;
    Context context;
    //아이템 클릭시 실행 함수
    private ItemClick itemClick;
    public interface ItemClick {
        public void onClick(View view,int position);
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public Add1Adapter() {
    }

    public Add1Adapter(ArrayList<member> item, Context context) {
        this.item = item;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;
        CheckBox btn_chk;

        public MyViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            img = view.findViewById(R.id.img);
            btn_chk = view.findViewById(R.id.btn_chk);

        }
    }


    @NonNull
    @Override
    public Add1Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_profile,parent,false);
        //context = viewGroup.getContext();
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        String name = item.get(i).getName();
        String img_url = item.get(i).getSubnail();
        final int pos = i;
        DefaultAppliction.img_glide(context,img_url,myViewHolder.img);
        myViewHolder.name.setText(name);

        myViewHolder.btn_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    myViewHolder.btn_chk.setButtonDrawable(R.drawable.check);
                }
                else myViewHolder.btn_chk.setButtonDrawable(R.drawable.btn_close);
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

    //아오 여기 size 안주고 기본값 0으로 줘서 계속 안나왔음. ㅅㅂ....
    @Override
    public int getItemCount() {
        return item.size();
    }
}
