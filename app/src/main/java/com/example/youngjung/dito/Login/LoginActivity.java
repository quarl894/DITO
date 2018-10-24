package com.example.youngjung.dito.Login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.R;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class LoginActivity extends BaseActivity {
    static String TAG ="KAKAO HASHKEY : ";
    Context context;
    private SessionCallback callback;
    boolean check_input_INFO = false;     // 로그인 기록이 있으면
    TextView tv_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_login);
        statusbar();
        context = getApplicationContext();
        String str =getKeyHash(context);
        Log.e("key: ", str);

        tv_login = findViewById(R.id.tv_login);

        tv_login.setHeight(DefaultAppliction.dpToPx(24));
        tv_login.setWidth(DefaultAppliction.dpToPx(182));


        googleBtnUi();
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            redirectSignupActivity();
            if (!check_input_INFO){
                AuthService.getInstance().requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        //redirectLoginActivity(self);
                    }
                    @Override
                    public void onNotSignedUp() {
                        // not happened
                    }
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Logger.e("failed to get access token info. msg=" + errorResult);
                    }
                    @Override
                    public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
                        final long userId = accessTokenInfoResponse.getUserId();
                        Log.i("tokenid!!!!!!!!!!!", Long.toString(userId));
                        check_input_INFO = true;
                    }
                });

            }
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    private void googleBtnUi() {
        // TODO Auto-generated method stub

        final LoginButton btn_login = findViewById(R.id.btn_login);

        Button btn_log = findViewById(R.id.btn_log);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_login.performClick();
            }
        });
    }

}

