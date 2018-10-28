package com.example.youngjung.dito.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.Info;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;

import java.util.ArrayList;

public class Study1Activity extends BaseActivity {
    Toolbar toolbar;
    ImageView img1, img2, img3, img_m;
    TextView tv_add, tv_add2;
    TextView tv_t1, tv_t2;
    ArrayList<member> m_list = new ArrayList<>();
    Info fo = new Info();
    Context context;
    Button btn_finish;
    ImageButton btn_profile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study1);
        statusbar();

        Intent i = getIntent();
        m_list = (ArrayList<member>) i.getExtras().get("member");
        fo = (Info) i.getExtras().get("info");

        context = getApplicationContext();
        View include = findViewById(R.id.include_layout);
        toolbar = include.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.yellow);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        tv_add = findViewById(R.id.tv_add);
        tv_add2 = findViewById(R.id.tv_add2);
        tv_t1 = findViewById(R.id.tv_t1);
        tv_t2 = findViewById(R.id.tv_t2);
        img_m = findViewById(R.id.img_m);
        btn_finish = findViewById(R.id.btn_finish);
        btn_profile = findViewById(R.id.btn_profile);

        tv_add2.setPadding(0,DefaultAppliction.dpToPx(35),0,0);
        tv_add.setPadding(0,0,0,DefaultAppliction.dpToPx(34));
        tv_t1.setText(fo.getR_name());
        tv_t2.setText(fo.getS_name());
        if(fo.getImg2()==null) img2.setVisibility(View.GONE);
        else img2.setVisibility(View.VISIBLE);
        if(fo.getImg3()==null) img3.setVisibility(View.GONE);
        else img3.setVisibility(View.VISIBLE);

        if(fo.getImg_master()==0) img_m.setVisibility(View.GONE);

        DefaultAppliction.img_glide(context, fo.getImg1(), img1);
        DefaultAppliction.img_glide(context,fo.getImg2(),img2);
        DefaultAppliction.img_glide(context,fo.getImg3(),img3);


        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Study1Activity.this, AddhwActivity.class);
                i.putExtra("member",m_list);
                startActivity(i);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Study1Activity.this, ProfileActivity.class);
                i.putExtra("member",m_list);
                startActivity(i);
            }
        });
        //Log.e("m_list", "" + m_list.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.study_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;

            case R.id.ic_done:
                redirectMainActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

