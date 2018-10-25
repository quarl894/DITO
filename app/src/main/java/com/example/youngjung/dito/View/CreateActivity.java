package com.example.youngjung.dito.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.Model.room;
import com.example.youngjung.dito.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CreateActivity extends BaseActivity {
    Toolbar toolbar;
    Button btn_finish;
    EditText r_name;
    EditText sub_name;
    TextView title_bar;
    int chk1 = 0;
    int chk2 = 0;
    boolean ok = false;
    room info;
    private DatabaseReference databaseReference;
    ArrayList<member> parti;
    ArrayList<String> hw;
    final String master = DefaultAppliction.name();
    String str ="no";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        statusbar();
        View include = findViewById(R.id.include_layout);

        toolbar = include.findViewById(R.id.toolbar);
        btn_finish = findViewById(R.id.btn_finish);
        r_name = findViewById(R.id.edit_room_name);
        sub_name = findViewById(R.id.edit_sub_name);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.yello);
        title_bar = findViewById(R.id.toolbar_title);
        parti = new ArrayList<>();
        hw = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_finish.setBackgroundColor(getResources().getColor(R.color.very_light_pink));


        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = r_name.getText().toString();
                String sub = sub_name.getText().toString();
                if(ok) {
                    // 방 생성
                    info = new room(r,sub,parti,hw);
                    databaseReference.child("room").child(master).push().setValue(new room(info.getR_name(), info.getS_name(),info.getParticipant(), info.getHomework()));
                    find();
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

    //초대링크 만들어서 저장 및 보내기.
    public String find(){
        // 방금 업뎃된거 가져오기
        Query q = databaseReference.child("room").child(master).limitToLast(1);

        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String k =dataSnapshot.getKey();
                Intent i = new Intent(CreateActivity.this, FinishActivity.class);

                i.putExtra("key",k);
                i.putExtra("r_name", info.getR_name());
                i.putExtra("s_name", info.getS_name());
                startActivity(i);
                finish();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return str;
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
