package com.app.tharindu.oauthdemo.services.api;

import com.app.tharindu.oauthdemo.helper.Consts;

public class ApiUtils {
    private ApiUtils(){}

    public static final String BASE_URL = Consts.domainURL+"api/v1/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
