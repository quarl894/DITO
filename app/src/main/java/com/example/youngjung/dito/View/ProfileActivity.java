package com.example.youngjung.dito.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.youngjung.dito.Adapter.ProfileAdapter;
import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;

public class ProfileActivity extends BaseActivity{
    ArrayList<member> members = new ArrayList<>();
    RecyclerView r_view;
    RecyclerView.LayoutManager mlayoutManager;
    Context context;
    Toolbar toolbar;
    TextView title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        statusbar();

        View include = findViewById(R.id.include_layout);

        toolbar = include.findViewById(R.id.toolbar);
        title_bar = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);


        r_view = findViewById(R.id.r_view);
        r_view.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        r_view.setLayoutManager(mlayoutManager);
        context = getApplicationContext();

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        members = (ArrayList<member>) i.getExtras().get("member");

        ProfileAdapter profileAdapter = new ProfileAdapter(members,getApplicationContext());
        r_view.setAdapter(profileAdapter);
        r_view.setAdapter(new ProfileAdapter(members,getApplicationContext()));
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
