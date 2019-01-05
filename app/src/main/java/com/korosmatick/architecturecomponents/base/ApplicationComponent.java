package com.korosmatick.architecturecomponents.base;

import com.korosmatick.architecturecomponents.details.DetailsFragment;
import com.korosmatick.architecturecomponents.home.ListFragment;
import com.korosmatick.architecturecomponents.networking.NetworkModule;
import com.korosmatick.architecturecomponents.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ViewModelModule.class,
})
public interface ApplicationComponent {

    void inject(ListFragment listFragment);

    void inject(DetailsFragment detailsFragment);
}
