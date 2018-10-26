package com.app.tharindu.oauthdemo.services.api;

import com.app.tharindu.oauthdemo.helper.Consts;
import com.app.tharindu.oauthdemo.models.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    //Login API Request To https://github.com
    @Headers("Accept: application/json")
    @POST(Consts.domainURL + "login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

}
