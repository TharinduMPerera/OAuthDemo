package com.app.tharindu.oauthdemo.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(Consts.redirectUri)) {
            // use the parameter your API exposes for the code ("code")
            String code = uri.getQueryParameter("code");
            String error;
            if (code != null) {

            } else if ((error = uri.getQueryParameter("error")) != null) {
                Toast.makeText(this, "Something went wrong! Error: " + error, Toast.LENGTH_LONG).show();
            }
        }
    }
}
