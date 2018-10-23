package com.example.youngjung.dito.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity {
    FloatingActionMenu fab_btn;
    FloatingActionButton fab_join; //참여하기
    FloatingActionButton fab_create; //생성하기
    boolean open = true;
    Toolbar toolbar;
    TextView tv_main;
    FrameLayout main_frame;
    //firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //status bar text 색 변경
        statusbar();

        fab_btn = findViewById(R.id.fab_btn);
        fab_join = findViewById(R.id.fab_item1);
        fab_create = findViewById(R.id.fab_item2);
    //    back = findViewById(R.id.back);
        tv_main = findViewById(R.id.tv_main);
        main_frame = findViewById(R.id.main_frame);


        View incloude1 = findViewById(R.id.include_layout);
        toolbar = incloude1.findViewById(R.id.toolbar);

        tv_main.setText("팀플방에 참여하거나 \n 새로운 팀플방을 직접 만들어보세요!");
        tv_main.setGravity(View.TEXT_ALIGNMENT_CENTER);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setLogo(R.drawable.logo);
        toolbar.bringToFront();

        main_frame.setBackgroundResource(R.color.transport2);

        fab_btn.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab();
            }
        });

        fab_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, CreateActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        //뒤로 돌아왔을 때 fab 원복
        open = false;
        fab();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_setting:
                Intent i = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(i);
                return true;
                default:
                    return false;
        }
    }


    public void fab(){
        if(open){
            main_frame.setBackgroundResource(R.color.transport);
            fab_btn.bringToFront();
            fab_create.setVisibility(View.VISIBLE);
            fab_join.setVisibility(View.VISIBLE);
            open = false;
        }else{
            main_frame.setBackgroundResource(R.color.transport2);
            fab_btn.bringToFront();
            fab_create.setVisibility(View.INVISIBLE);
            fab_join.setVisibility(View.INVISIBLE);
            open = true;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
