package com.example.youngjung.dito.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.youngjung.dito.Adapter.Add1Adapter;
import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;

public class AddPersonActivity extends BaseActivity{
    Toolbar toolbar;
    RecyclerView h_view;
    RecyclerView v_view;
    RecyclerView.LayoutManager mlayoutManager;
    ArrayList<member> members = new ArrayList<>();

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

        mlayoutManager = new LinearLayoutManager(this);
        v_view.setLayoutManager(mlayoutManager);

        final Add1Adapter add1Adapter = new Add1Adapter(members,getApplicationContext());
        v_view.setAdapter(add1Adapter);

        add1Adapter.find();
    }


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
