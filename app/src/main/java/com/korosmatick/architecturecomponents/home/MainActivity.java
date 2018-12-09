package com.korosmatick.architecturecomponents.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.korosmatick.architecturecomponents.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.screen_container, new ListFragment())
                    .commit();
        }
    }
}
