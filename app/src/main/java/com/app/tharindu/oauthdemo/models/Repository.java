package com.app.tharindu.oauthdemo.models;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    private String name;

    @SerializedName("private")
    private Boolean isPrivate;

    @SerializedName("language")
    private String language;

    public String getName() {
        return name;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public String getLanguage() {
        return language;
    }
}
