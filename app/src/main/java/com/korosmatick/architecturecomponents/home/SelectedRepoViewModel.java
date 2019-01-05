package com.korosmatick.architecturecomponents.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.korosmatick.architecturecomponents.model.Repo;

public class SelectedRepoViewModel extends ViewModel {

    private final MutableLiveData<Repo> selectedRepo =  new MutableLiveData<>();

    public LiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

 }
