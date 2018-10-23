package com.example.youngjung.dito.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {
    FloatingActionMenu fab_btn;
    FloatingActionButton fab_join; //참여하기
    FloatingActionButton fab_create; //생성하기
    View back;
    boolean open = true;
    Toolbar toolbar;
    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_btn = findViewById(R.id.fab_btn);
        fab_join = findViewById(R.id.fab_item1);
        fab_create = findViewById(R.id.fab_item2);
        back = findViewById(R.id.back);
        tv_main = findViewById(R.id.tv_main);

        View incloude1 = findViewById(R.id.include_layout);
        toolbar = incloude1.findViewById(R.id.toolbar);

        tv_main.setText("팀플방에 참여하거나 \n 새로운 팀플방을 직접 만들어보세요!");
        tv_main.setGravity(View.TEXT_ALIGNMENT_CENTER);

        //toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        toolbar.setLogo(R.drawable.logo);

        toolbar.bringToFront();

        fab_btn.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab();
//                fab(fab_btn.isOpened());
//                chk = true;
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
          //  fab_btn.setClickable(false);
            back.setVisibility(View.VISIBLE);
            fab_btn.bringToFront();
            fab_create.setVisibility(View.VISIBLE);
            fab_join.setVisibility(View.VISIBLE);
            open = false;
        }else{
           // fab_btn.setClickable(true);
            fab_btn.bringToFront();
            back.setVisibility(View.GONE);
            fab_create.setVisibility(View.INVISIBLE);
            fab_join.setVisibility(View.INVISIBLE);
            open = true;
        }
    }
}
