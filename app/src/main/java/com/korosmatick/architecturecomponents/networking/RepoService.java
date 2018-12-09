package com.korosmatick.architecturecomponents.networking;

import com.korosmatick.architecturecomponents.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoService {

    @GET("orgs/Google/repos")
    Call<List<Repo>> getRepositories();

}
