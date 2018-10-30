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
import com.example.youngjung.dito.Model.Info;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.R;
import com.example.youngjung.dito.View.MainActivity;
import com.example.youngjung.dito.View.Study1Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomDialog extends android.app.Dialog {
    EditText edit;
    Button btn_can, btn_ok;
    LinearLayout dialog_linear;
    TextView tv;
    DatabaseReference databaseReference;
    Info fo = new Info();

    public CustomDialog(final Context context) {
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
        databaseReference = FirebaseDatabase.getInstance().getReference();

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
                String[] tmp = edit.getText().toString().split(" ");
                if(edit.getText().toString()==null || tmp[0]==null || tmp.length!=2){
                    Toast.makeText(getContext(),"코드가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                }else{
                    final String name = tmp[0];
                    final String key = tmp[1];
                    DatabaseReference data = databaseReference.child("room").child(name).child("own").child(key);
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue()!=null){
                                databaseReference.child("room").child(name).child("own").child(key).child("member").child(DefaultAppliction.name())
                                        .setValue(new member(DefaultAppliction.name(), DefaultAppliction.get_nick(),DefaultAppliction.thumbnail()));
                                databaseReference.child("room").child(DefaultAppliction.name()).child("nowon").child(name)
                                        .child(key).setValue(0);


                                ArrayList<member> arr = new ArrayList<>();
                                Iterable<DataSnapshot> item = dataSnapshot.child("member").getChildren();
                                int num =0;
                                for(DataSnapshot child2 : item){
                                    arr.add(child2.getValue(member.class));
                                    if(num==0){
                                        fo.setImg1(arr.get(num).getSubnail());
                                    }
                                    else if(num==1) fo.setImg2(arr.get(num).getSubnail());
                                    else fo.setImg3(arr.get(num).getSubnail());
                                }
                                fo.setCnt("+"+num);
                                fo.setR_name((String) dataSnapshot.child("r_name").getValue());
                                fo.setS_name((String) dataSnapshot.child("s_name").getValue());

                                Intent i = new Intent(getContext(), Study1Activity.class);
                                i.putExtra("member",arr);
                                i.putExtra("info",fo);
                                getContext().startActivity(i);
                            }else{
                                Toast.makeText(getContext(),"코드가 일치하지 않습니다",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
//                    if(databaseReference.child("room").child(name).child("own").child(key)!=null){
//                        databaseReference.child("room").child(name).child("own").child(key).child("member").child(DefaultAppliction.name())
//                                .setValue(new member(DefaultAppliction.name(), DefaultAppliction.get_nick(),DefaultAppliction.thumbnail()));
//                        Intent i = new Intent(getContext(), Study1Activity.class);
//                        getContext().startActivity(i);
//                    }else{
//                        Toast.makeText(getContext(),"코드가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                //}
                }

                dismiss();
            }
        });

    }
}
