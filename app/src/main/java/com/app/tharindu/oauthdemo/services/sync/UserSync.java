package com.app.tharindu.oauthdemo.services.sync;

import com.app.tharindu.oauthdemo.helper.Consts;
import com.app.tharindu.oauthdemo.models.AccessToken;
import com.app.tharindu.oauthdemo.services.api.APIService;
import com.app.tharindu.oauthdemo.services.api.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSync {

    private UserSyncCallback userSyncCallback;
    private APIService apiService;

    public UserSync(UserSyncCallback userSyncCallback) {
        this.userSyncCallback = userSyncCallback;
        apiService = ApiUtils.getAPIService();
    }

    public void getAccessToken(String code) {
        apiService.getAccessToken(Consts.clientID, Consts.clientSecret, code).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userSyncCallback.onAccessTokenGranted(response.body());
                } else {
                    userSyncCallback.onUserSyncError(response.errorBody() != null ?
                            response.errorBody().toString() : "Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                userSyncCallback.onUserSyncError("Something went wrong!");
            }
        });
    }

    public interface UserSyncCallback {
        void onAccessTokenGranted(AccessToken accessToken);
        void onUserSyncError(String error);
    }
}
