package com.example.youngjung.dito.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.CalendarWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddhwActivity extends BaseActivity {
    Toolbar toolbar;
    ImageButton btn_action, btn_set;
    ArrayList<member> m_list = new ArrayList<>();
    com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;
    TextView tv_date;
    boolean open = true;
    TextView add_cnt;
    ArrayList<member> add_member = new ArrayList<>();

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
        btn_set = findViewById(R.id.btn_set);
        String set_date = null;
        add_cnt = findViewById(R.id.add_cnt);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);
        calendarView.setTopbarVisible(true);
        tv_date= findViewById(R.id.tv_date);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                tv_date.setText(calendarDay.getMonth()+"월 " + calendarDay.getDay()+"일");
            }
        });

        Intent get_i = getIntent();
        m_list = (ArrayList<member>) get_i.getExtras().get("member");
        add_member = (ArrayList<member>) get_i.getExtras().get("add_mem");
        if(add_member!=null) add_cnt.setText(add_member.size()+"명");

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(AddhwActivity.this, AddPersonActivity.class);
                i.putExtra("mem",m_list);
                startActivity(i);
            }
        });

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               calendar();
            }
        });
    }

    private void calendar(){
        if(open){
            btn_set.setImageResource(R.drawable.btn_close);
            calendarView.setVisibility(View.VISIBLE);
            open = false;
        }else{
            btn_set.setImageResource(R.drawable.btn_more);
            calendarView.setVisibility(View.GONE);
            open = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
