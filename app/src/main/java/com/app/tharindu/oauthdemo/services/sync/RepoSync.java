package com.app.tharindu.oauthdemo.services.sync;

import com.app.tharindu.oauthdemo.models.AccessToken;
import com.app.tharindu.oauthdemo.models.Repository;
import com.app.tharindu.oauthdemo.services.api.APIService;
import com.app.tharindu.oauthdemo.services.api.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoSync {

    private RepoSyncCallback repoSyncCallback;
    private APIService apiService;

    public RepoSync(RepoSyncCallback repoSyncCallback) {
        this.repoSyncCallback = repoSyncCallback;
        apiService = ApiUtils.getAPIService();
    }

    public void getRepos(AccessToken accessToken) {
        apiService.getRepos(accessToken.getTokenType()+" "+accessToken.getAccessToken()).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    repoSyncCallback.onReposFound(response.body());
                } else {
                    String errr = response.errorBody()+"";
                    repoSyncCallback.onRepoSyncError(response.errorBody() != null ?
                            response.errorBody().toString() : "Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                repoSyncCallback.onRepoSyncError("Something went wrong!");
            }
        });
    }

    public interface RepoSyncCallback {
        void onReposFound(List<Repository> repos);

        void onRepoSyncError(String error);
    }
}
