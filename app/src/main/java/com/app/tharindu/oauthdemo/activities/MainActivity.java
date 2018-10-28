package com.app.tharindu.oauthdemo.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.tharindu.oauthdemo.R;
import com.app.tharindu.oauthdemo.helper.Consts;
import com.app.tharindu.oauthdemo.models.AccessToken;
import com.app.tharindu.oauthdemo.models.Repository;
import com.app.tharindu.oauthdemo.services.sync.RepoSync;
import com.app.tharindu.oauthdemo.services.sync.UserSync;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements UserSync.UserSyncCallback {

    @BindView(R.id.loadingAnimation)
    AVLoadingIndicatorView loadingAnimation;

    @BindView(R.id.githubLoginBtn)
    Button githubLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void showReposActivity(AccessToken accessToken) {
        Intent reposActivityIntent = new Intent(this, ReposActivity.class);
        reposActivityIntent.putExtra(Consts.EXTRA_ACCESSTOKEN, accessToken);
        startActivity(reposActivityIntent);
        finish();
    }

    @OnClick(R.id.githubLoginBtn)
    public void showGithubLogin() {
        Intent githubLogin = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" +
                "?client_id=" + Consts.clientID + "&scope=repo&redirect_uri=" + Consts.redirectUri));
        startActivity(githubLogin);
    }

    private void setLoginBtnEnabled(boolean status) {
        githubLoginBtn.setEnabled(status);
        if (status) {
            loadingAnimation.setVisibility(View.GONE);
            githubLoginBtn.setVisibility(View.VISIBLE);
        } else {
            githubLoginBtn.setVisibility(View.GONE);
            loadingAnimation.setVisibility(View.VISIBLE);
        }
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
                setLoginBtnEnabled(false);
                new UserSync(this).getAccessToken(code);
            } else if ((error = uri.getQueryParameter("error")) != null) {
                Toast.makeText(this, "Something went wrong!\nError: " + error, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onAccessTokenGranted(AccessToken accessToken) {
        // once the access token is granted, it should be encrypted and saved
        // for the demonstration, that part is not implemented
        Toast.makeText(this, "Access token is granted Successfully!", Toast.LENGTH_LONG).show();
        System.out.println("Access Token: " + accessToken.getAccessToken());

        showReposActivity(accessToken);
    }

    @Override
    public void onUserSyncError(String error) {
        Toast.makeText(this, "Access Token Granting Error:\n" + error, Toast.LENGTH_LONG).show();
        setLoginBtnEnabled(true);
    }
}
