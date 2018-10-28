package com.app.tharindu.oauthdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.tharindu.oauthdemo.R;
import com.app.tharindu.oauthdemo.adapters.RepoAdapter;
import com.app.tharindu.oauthdemo.helper.Consts;
import com.app.tharindu.oauthdemo.models.AccessToken;
import com.app.tharindu.oauthdemo.models.Repository;
import com.app.tharindu.oauthdemo.services.sync.RepoSync;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposActivity extends AppCompatActivity implements RepoSync.RepoSyncCallback {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.reposRecyclerView)
    RecyclerView reposRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        ButterKnife.bind(this);

        getRepos();
    }

    private void getRepos() {
        Object object = getIntent().getExtras().getParcelable(Consts.EXTRA_ACCESSTOKEN);
        if(object!=null && object instanceof AccessToken){
            progressBar.setVisibility(View.VISIBLE);
            AccessToken accessToken = (AccessToken) object;
            new RepoSync(this).getRepos(accessToken);
        } else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

    private void setRecyclerView(List<Repository> repositories) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reposRecyclerView.setLayoutManager(linearLayoutManager);
        RepoAdapter repoAdapter = new RepoAdapter(repositories);
        reposRecyclerView.setAdapter(repoAdapter);
    }

    @Override
    public void onReposFound(List<Repository> repos) {
        progressBar.setVisibility(View.GONE);
        setRecyclerView(repos);
    }

    @Override
    public void onRepoSyncError(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Repos Fetching Error:\n" + error, Toast.LENGTH_LONG).show();
    }
}
