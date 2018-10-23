package com.example.youngjung.dito.View;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.R;

public class FinishActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        statusbar();
    }
}
