package com.app.tharindu.oauthdemo.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.tharindu.oauthdemo.R;
import com.app.tharindu.oauthdemo.helper.Consts;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.githubLoginBtn)
    public void showGithubLogin() {
        Intent githubLogin = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" +
                "?client_id=" + Consts.clientID + "&scope=repo&redirect_uri=" + Consts.redirectUri));
        startActivity(githubLogin);
    }
}
