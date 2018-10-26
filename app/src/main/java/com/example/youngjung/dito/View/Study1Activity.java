package com.example.youngjung.dito.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.R;
import com.bumptech.glide.*;
public class Study1Activity extends BaseActivity {
    Toolbar toolbar;
    ImageView img1;
    TextView tv_add, tv_add2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study1);
        statusbar();

        View include = findViewById(R.id.include_layout);
        toolbar = include.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.yellow);
        img1 = findViewById(R.id.img1);
        tv_add = findViewById(R.id.tv_add);
        tv_add2 = findViewById(R.id.tv_add2);

        tv_add2.setPadding(0,DefaultAppliction.dpToPx(35),0,0);
        tv_add.setPadding(0,0,0,DefaultAppliction.dpToPx(34));

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.icn_leader).centerCrop().circleCrop();
        Glide.with(this).load(DefaultAppliction.thumbnail()).transition(DrawableTransitionOptions.withCrossFade()).apply(requestOptions).into(img1);

    }

//    // For a simple image list:
//    @Override
//    public View getView(int position, View recycled, ViewGroup container) {
//        final ImageView myImageView;
//        if (recycled == null) {
//            myImageView = (ImageView) inflater.inflate(R.layout.my_image_view, container, false);
//        } else {
//            myImageView = (ImageView) recycled;
//        }
//
//        String url = myUrls.get(position);
//
//        GlideApp
//                .with(myFragment)
//                .load(url)
//                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
//                .into(myImageView);
//
//        return myImageView;
//    }



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

