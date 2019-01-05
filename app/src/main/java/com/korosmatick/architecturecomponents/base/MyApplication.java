package com.korosmatick.architecturecomponents.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        return ((MyApplication) context.getApplicationContext()).component;
    }
}
