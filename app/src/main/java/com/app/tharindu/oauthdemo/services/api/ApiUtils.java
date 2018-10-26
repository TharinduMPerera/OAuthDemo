package com.app.tharindu.oauthdemo.services.api;

import com.app.tharindu.oauthdemo.helper.Consts;

public class ApiUtils {
    private ApiUtils(){}

    private static final String BASE_URL = Consts.domainURL;

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
