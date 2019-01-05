package com.korosmatick.architecturecomponents.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.korosmatick.architecturecomponents.home.ListViewModel;
import com.korosmatick.architecturecomponents.home.SelectedRepoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SelectedRepoViewModel.class)
    abstract ViewModel bindSelectedRepoViewModel(SelectedRepoViewModel viewModel);
}
