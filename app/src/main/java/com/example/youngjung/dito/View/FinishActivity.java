package com.example.youngjung.dito.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.R;

public class FinishActivity extends BaseActivity {
    TextView tv_key;
    Button btn_finish;
    String mykey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        statusbar();

        tv_key = findViewById(R.id.tv_key);
        btn_finish = findViewById(R.id.btn_finish);

        Intent i = getIntent();

        mykey = i.getExtras().getString("key");
        tv_key.setText(mykey);
        Log.e("key", mykey);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectMainActivity();
            }
        });
    }
}
