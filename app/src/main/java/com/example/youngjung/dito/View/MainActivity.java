package com.example.youngjung.dito.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youngjung.dito.Adapter.RoomAdapter;
import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.Info;
import com.example.youngjung.dito.Model.member;
import com.example.youngjung.dito.Model.room;
import com.example.youngjung.dito.R;
import com.example.youngjung.dito.ui.CustomDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

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
    ImageView back;
    RecyclerView main_room;
    RecyclerView.LayoutManager mlayoutManager;
    //firebase
    DatabaseReference databaseReference;
    ArrayList<Info> test = new ArrayList<>();
    ArrayList<ArrayList<member>> mem = new ArrayList<>();
    String a = "aaa";
    static ArrayList<Info> tmp = new ArrayList<>();
    int count = 0;
    boolean chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //status bar text 색 변경
        statusbar();

        fab_btn = findViewById(R.id.fab_btn);
        fab_join = findViewById(R.id.fab_item1);
        fab_create = findViewById(R.id.fab_item2);
        tv_main = findViewById(R.id.tv_main);
        tv_main2 = findViewById(R.id.tv_main2);
        main_frame = findViewById(R.id.main_frame);
        back = findViewById(R.id.back);

        View incloude1 = findViewById(R.id.include_layout);
        toolbar = incloude1.findViewById(R.id.toolbar);
        linear = findViewById(R.id.linear);

      //  View main_room = findViewById(R.id.true_room);
        main_room = findViewById(R.id.true_room);
        main_room.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        main_room.setLayoutManager(mlayoutManager);


        //DB 불러오기
        databaseReference = FirebaseDatabase.getInstance().getReference();
       // DatabaseReference ref = databaseReference.child("room");


       // linear.setPadding(0,120,0,0);
        tv_main.setText("팀플방에 참여하거나");
        tv_main2.setText("새로운 팀플방을 직접 만들어보세요!");
        // chk는 DB에 방이 있는지 없는지 확인 작업.
        boolean chk = true;

        get_data();
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
        super.onResume();
        open = false;
        fab();

        if(test.size() == 0) {
            Toast.makeText(getApplicationContext(),"데이터를 읽어오고 있습니다.",Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(test.size()==0 && count<3){
                        onResume();
                        count++;
                    }else if(test.size()==0 && count>=3){
                        Toast.makeText(getApplicationContext(),"데이터가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else if(test.size()!=0 && count<=3){
                  //      get_View();
                        Toast.makeText(getApplicationContext(),"데이터 완료.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }, 2000);
        }else{
            Log.e("what the size: ", "" + test.size());
        }
    }

    public void get_data(){
        // model 생성자 초기화 안만들어주면 firebase 에러남. 꼭 만들어주기.(빈 생성자)
        Query ref = databaseReference.child("room").child(DefaultAppliction.name());
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //내가 방장인 방 탐색
                //Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                   if(dataSnapshot.getKey().toString().equals("own")){
                Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                for (DataSnapshot contact : child) {
                    room rf = contact.getValue(room.class);
                    ArrayList<member> arr = new ArrayList<>();
                    String img1 = null;
                    String img2 = null;
                    String img3 = null;
                    Iterable<DataSnapshot> child2 = contact.child("member").getChildren();
                    // 팀원들 탐색
                    for (DataSnapshot contact2 : child2) {
                        member p = contact2.getValue(member.class);
                        arr.add(p);
                    }
                    for (int i = 0; i < arr.size(); i++) {
                        if (i == 0) img1 = arr.get(i).getSubnail();
                        else if (i == 1) img2 = arr.get(i).getSubnail();
                        else if (i == 2) img3 = arr.get(i).getSubnail();
                        else break;
                    }
                    // Log.e("what:: ", rf.getR_name());
                    long count = contact.child("member").getChildrenCount(); // 팀원 수
                    String cnt = "+" + count;
                    //내가 방장일 때
                    test.add(new Info(rf.getR_name(), rf.getS_name(), cnt, 1, img1, img2, img3));
                    mem.add(arr);
                }

                    // 내가 방장이 아닌 방 탐색
                }else{
                Iterable<DataSnapshot> nowon = dataSnapshot.getChildren();
                for(DataSnapshot data : nowon) {
                    String id = data.getKey();
                    Iterable<DataSnapshot> child3 = dataSnapshot.child(id).getChildren();
                    for (DataSnapshot contact3 : child3) {
                        String key = contact3.getKey();
                        DatabaseReference q = databaseReference.child("room").child(id).child("own");
                        q.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                room r1 = dataSnapshot.getValue(room.class);
                                Iterable<DataSnapshot> child2 = dataSnapshot.child("member").getChildren();
                                ArrayList<member> arr = new ArrayList<>();
                                String img1 = null;
                                String img2 = null;
                                String img3 = null;
                                // 팀원들 탐색
                                for (DataSnapshot contact2 : child2) {
                                    member p = contact2.getValue(member.class);
                                    arr.add(p);
                                }
                                for (int i = 0; i < arr.size(); i++) {
                                    if (i == 0) img1 = arr.get(i).getSubnail();
                                    else if (i == 1) img2 = arr.get(i).getSubnail();
                                    else if (i == 2) img3 = arr.get(i).getSubnail();
                                    else break;
                                }
                                // Log.e("what:: ", rf.getR_name());
                                int count = arr.size(); // 팀원 수
                                String cnt = "+" + count;
                                //내가 방장일 때
                                test.add(new Info(r1.getR_name(), r1.getS_name(), cnt, 0, img1, img2, img3));
                                mem.add(arr);
                                get_View();
                                chk = true;
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
                    }
                }
                }
                get_View();
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
    }

    public void get_View(){
            RoomAdapter roomAdapter = new RoomAdapter(test,mem,getApplicationContext());
            main_room.setAdapter(roomAdapter);

        if(test.size()!=0){
            main_room.setVisibility(View.VISIBLE);
            tv_main.setVisibility(View.GONE);
            tv_main2.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
        }else{
            main_room.setVisibility(View.GONE);
            tv_main.setVisibility(View.VISIBLE);
            tv_main2.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }
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
