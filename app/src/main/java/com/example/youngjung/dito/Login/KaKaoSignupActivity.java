package com.example.youngjung.dito.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.youngjung.dito.BaseActivity;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.ApiErrorCode;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.helper.log.Logger;

public class KaKaoSignupActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    redirectMainActivity();
                }
            }
        });
    }
}
