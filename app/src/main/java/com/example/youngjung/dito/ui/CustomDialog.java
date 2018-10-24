package com.example.youngjung.dito.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.R;

public class CustomDialog extends android.app.Dialog {
    EditText edit;
    Button btn_can, btn_ok;
    LinearLayout dialog_linear;
    TextView tv;

    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.popup);     //다이얼로그에서 사용할 레이아웃입니다.
        edit = findViewById(R.id.edit);
        btn_can = findViewById(R.id.btn_cancle);
        btn_ok = findViewById(R.id.btn_ok);
        tv = findViewById(R.id.tv);

        dialog_linear = findViewById(R.id.dialog_linear);
        String str = "팀플<Strong>참여코드</Strong>를<br>입력하세요.";

        tv.setPadding(0,DefaultAppliction.dpToPx(48),0,0);
        tv.setText(Html.fromHtml(str));

   //     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DefaultAppliction.dpToPx(282),DefaultAppliction.dpToPx(245));
     //   dialog_linear.setLayoutParams(params);
       // dialog_linear.setPadding(DefaultAppliction.dpToPx(30),DefaultAppliction.dpToPx(48),DefaultAppliction.dpToPx(27),DefaultAppliction.dpToPx(24));

        btn_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();   //다이얼로그를 닫는 메소드입니다.
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(this,)
            }
        });

    }
}
