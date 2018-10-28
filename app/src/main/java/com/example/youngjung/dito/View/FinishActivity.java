package com.example.youngjung.dito.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.R;

public class FinishActivity extends BaseActivity {
    TextView tv_key, tv_1, tv_2, tv_3;
    Button btn_finish, btn_invite;
    String mykey, r_name, s_name, invite_key;
    LinearLayout linear;
    ClipboardManager clipboardManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        statusbar();

        linear = findViewById(R.id.linear);
        tv_key = findViewById(R.id.tv_key);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        btn_finish = findViewById(R.id.btn_finish);
        btn_invite = findViewById(R.id.invite_copy);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        linear.setPadding(DefaultAppliction.dpToPx(25),DefaultAppliction.dpToPx(108),DefaultAppliction.dpToPx(25), 0);
       // tv_3.setPadding(0,0,DefaultAppliction.dpToPx(93), 0);
        Intent i = getIntent();
        r_name = i.getExtras().getString("r_name");
        s_name = i.getExtras().getString("s_name");

        mykey = i.getExtras().getString("key");
        invite_key = DefaultAppliction.name() + " " +mykey;
        tv_key.setText(invite_key);


        Log.e("key", invite_key);
        String str1 = "<strong>"+r_name+"</strong>"+"과목";
        String str2 = "<strong>"+s_name+"</strong>"+"방이 생성되었습니다!";
        String str3 = "팀원들에게 링크를 공유해주세요.";
        tv_1.setText(Html.fromHtml(str1));
        tv_2.setText(Html.fromHtml(str2));
        tv_3.setText(Html.fromHtml(str3));

        btn_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //클립보드 복사
                ClipData clipData = ClipData.newPlainText("초대 링크", invite_key);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplication(), "복사되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               redirectMainActivity();
            }
        });
    }

}
