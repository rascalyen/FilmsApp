package com.example.yen.imdb.ui.mvvm.mainpage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.yen.imdb.R;
import com.example.yen.imdb.configs.dagger.HasComponent;
import com.example.yen.imdb.configs.dagger.component.ActivityComponent;
import com.example.yen.imdb.ui.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private static final String MAIN_TAG = "MAIN";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        initializeActivity();
    }

    private void preSetViews() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initializeActivity() {
        setSupportActionBar(toolbar);

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);

        if (mainFragment == null)
            addFragment(R.id.fl_base, MainFragment.newInstance(), MAIN_TAG);
        else
            replaceFragment(R.id.fl_base, mainFragment);
    }

    @OnClick(R.id.text_refresh)
    public void onRefresh() {
        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
        
        if (mainFragment != null && mainFragment.isVisible()) {
            mainFragment.clearMovies();
            mainFragment.getMainViewModel().initialize();
        }
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}