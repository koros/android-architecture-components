package com.korosmatick.architecturecomponents.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.util.Log;

import com.korosmatick.architecturecomponents.model.Repo;
import com.korosmatick.architecturecomponents.networking.RepoService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedRepoViewModel extends ViewModel {

    private final MutableLiveData<Repo> selectedRepo =  new MutableLiveData<>();
    private final RepoService repoService;
    private Call<Repo> repoCall;

    @Inject
    public SelectedRepoViewModel(RepoService repoService) {
        this.repoService = repoService;
    }

    public LiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

    public void saveToBundle(Bundle outState) {
        if (selectedRepo.getValue() != null) {
            outState.putStringArray("repo_details", new String[]{
                    selectedRepo.getValue().owner().login(), selectedRepo.getValue().name()});
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        // we only restore if we don't have selectedRepo already set
        if (selectedRepo.getValue() == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repoDetails) {
        repoCall = repoService.getRepo(repoDetails[0], repoDetails[1]);
        repoCall.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                selectedRepo.setValue(response.body());
                repoCall = null;
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading repo", t);
                repoCall = null;
            }
        });
    }

}
