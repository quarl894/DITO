package com.example.youngjung.dito.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.R;

public class CreateActivity extends BaseActivity {
    Toolbar toolbar;
    Button btn_finish;
    EditText r_name;
    EditText sub_name;
    int chk1 = 0;
    int chk2 = 0;
    boolean ok = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        View include = findViewById(R.id.include_layout);

        toolbar = include.findViewById(R.id.toolbar);
        btn_finish = findViewById(R.id.btn_finish);
        r_name = findViewById(R.id.edit_room_name);
        sub_name = findViewById(R.id.edit_sub_name);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.yello);

        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_finish.setBackgroundColor(getResources().getColor(R.color.very_light_pink));

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room = r_name.getText().toString();
                String sub = sub_name.getText().toString();
                if(ok) {
                    Intent i = new Intent(CreateActivity.this, FinishActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"이름과 과목을 작성해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        r_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                chk1 = editable.length();
                if(chk1>0 && chk2>0){
                    ok = true;
                    btn_finish.setBackgroundColor(getResources().getColor(R.color.yello));
                }
                else{
                    ok = false;
                    btn_finish.setBackgroundColor(getResources().getColor(R.color.very_light_pink));
                }
            }
        });

        sub_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                chk2 = editable.length();
                if(chk1>0 && chk2>0){
                    ok = true;
                    btn_finish.setBackgroundColor(getResources().getColor(R.color.yello));
                }
                else{
                    ok = false;
                    btn_finish.setBackgroundColor(getResources().getColor(R.color.very_light_pink));
                }
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
