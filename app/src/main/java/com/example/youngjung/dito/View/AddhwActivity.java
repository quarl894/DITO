package com.example.youngjung.dito.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;

public class AddhwActivity extends BaseActivity {
    Toolbar toolbar;
    ImageButton btn_action;
    ArrayList<member> m_list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhw);
        statusbar();
        View include = findViewById(R.id.include_layout);
        toolbar = include.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.yellow);
        btn_action = findViewById(R.id.btn_action);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent get_i = getIntent();
        m_list = (ArrayList<member>) get_i.getExtras().get("member");

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(AddhwActivity.this, AddActivity.class);
                i.putExtra("mem",m_list);
                startActivity(i);
            }
        });
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
