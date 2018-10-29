package com.example.youngjung.dito.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.youngjung.dito.Adapter.Add1Adapter;
import com.example.youngjung.dito.Adapter.Add2Adapter;
import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;

public class AddPersonActivity extends BaseActivity implements Add1Adapter.ItemClickListener{
    Toolbar toolbar;
    RecyclerView h_view;
    RecyclerView v_view;
    RecyclerView.LayoutManager mlayoutManager, mlayoutManager2;
    ArrayList<member> members = new ArrayList<>();
    ArrayList<member> show_member = new ArrayList<>();
    member[] arr;
    Add1Adapter add1Adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        statusbar();
        View include = findViewById(R.id.include_layout);
        toolbar = include.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.yellow);

        h_view = findViewById(R.id.hor_rview);
        v_view = findViewById(R.id.ver_rview);

        Intent i = getIntent();
        members = (ArrayList<member>) i.getExtras().get("mem");
        arr = new member[members.size()+1];

        mlayoutManager = new LinearLayoutManager(this);
        mlayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        v_view.setLayoutManager(mlayoutManager);
        h_view.setLayoutManager(mlayoutManager2);

        add1Adapter = new Add1Adapter(members,getApplicationContext());
        add1Adapter.setClickListener(this);
        v_view.setAdapter(add1Adapter);


    }

    @Override
    public void onItemClick(int pos, boolean chk, member person) {
        ArrayList<member> m = new ArrayList<>();
        if(chk){
            arr[pos] = person;
        }else{
            arr[pos] = null;
        }
        for(member n : arr) {
            if(n!=null){
                m.add(n);
                Log.e("add_arr", "" + n.getName());
            }
        }
        Add2Adapter add2Adapter = new Add2Adapter(m,getApplicationContext());
        //add2Adapter.setClickListener(this);
        h_view.setAdapter(add2Adapter);
    }

    //add2adapter click event 위에 리사이클뷰 클릭시 삭제 보류
//    @Override
//    public void onItemClick(int pos, member person) {
//        ArrayList<member> m = new ArrayList<>();
//        arr[pos] = null;
//        for(member n : arr) {
//            if(n!=null){
//                m.add(n);
//               // Log.e("add_arr", "" + n.getName());
//            }
//        }
//
//        Add2Adapter add2Adapter = new Add2Adapter(m,getApplicationContext());
//        add2Adapter.setClickListener(this);
//        h_view.setAdapter(add2Adapter);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
