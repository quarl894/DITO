package com.example.youngjung.dito;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.example.youngjung.dito.Adapter.KakaoSDKAdapter;
import com.kakao.auth.KakaoSDK;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DefaultAppliction extends Application {
    private static DefaultAppliction instance = null;
    private static volatile Activity currentActivity = null;
    private static Context context;
    private static String master;
    private static String img_url;
    private static String nick;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());

        init();

    }

    public static DefaultAppliction getDefaultApplicationContext(){
        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    private void init() {
        initFont();
    }

    public void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSansCJKkr-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;

        int px = Math.round((float) dp * density);
        return px;
    }

    public static String m_name(String m){
        master = m;
        return master;
    }

    public static String s_img(String i){
        img_url = i;
        return img_url;
    }

    public static String s_nick(String n){
        nick = n;
        return nick;
    }

    public static String name(){
        return m_name(master);
    }

    public static String thumbnail(){
        return s_img(img_url);
    }

    public static String get_nick() { return s_nick(nick); }
}
