package com.example.youngjung.dito.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.youngjung.dito.BaseActivity;
import com.example.youngjung.dito.DefaultAppliction;
import com.example.youngjung.dito.Model.member;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.ApiErrorCode;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.helper.log.Logger;

public class KaKaoSignupActivity extends BaseActivity {
    //firebase
    private DatabaseReference databaseReference;
    com.example.youngjung.dito.Model.member member;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        requestMe();
    }

    protected void requestMe() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                int result = errorResult.getErrorCode();
                if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Logger.e("onSessionClosed");
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                if (result.hasSignedUp() == OptionalBoolean.FALSE) {
                    Log.e("login fail"," fail");
                    //showSignup();
                } else {
                    String id = String.valueOf(result.getId());
                    String nick = result.getNickname();
                    String img = result.getThumbnailImagePath();

                    DefaultAppliction.m_name(id);
                    member = new member(id,nick,img);
                    save_profile(member);
                    redirectMainActivity();
                }
            }
        });
    }

    // Profile 저장.
    private void save_profile(member m){
        databaseReference.child("profile").child(m.getId()).setValue(m);
    }
}
