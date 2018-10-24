package com.example.youngjung.dito.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.R;
import com.example.youngjung.dito.ui.CustomDialog;
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
    TextView tv_main, tv_main2;
    FrameLayout main_frame;
    LinearLayout linear;
    CustomDialog cd;
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
        tv_main2 = findViewById(R.id.tv_main2);
        main_frame = findViewById(R.id.main_frame);


        View incloude1 = findViewById(R.id.include_layout);
        toolbar = incloude1.findViewById(R.id.toolbar);
        linear = findViewById(R.id.linear);

       // linear.setPadding(0,120,0,0);

        tv_main.setText("팀플방에 참여하거나");
        tv_main2.setText("새로운 팀플방을 직접 만들어보세요!");

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

        fab_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
                fab();
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

    public void Dialog(){
//        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
//        int width = dm.widthPixels; //디바이스 화면 너비
//        int height = dm.heightPixels; //디바이스 화면 높이

        cd = new CustomDialog(this);
        WindowManager.LayoutParams wm = cd.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(cd.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미

        wm.x = View.TEXT_ALIGNMENT_CENTER;
        wm.y = View.TEXT_ALIGNMENT_CENTER;
        cd.show();
    }


}
