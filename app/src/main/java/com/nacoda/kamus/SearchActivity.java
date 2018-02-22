package com.nacoda.kamus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    FlowingDrawer drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navView.setNavigationItemSelectedListener(this);
        drawerLayout.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);

        addFragmentOnTop(new KamusFragment(getString(R.string.english)), R.id.frame_kamus);
    }


    public void addFragmentOnTop(Fragment fragment, int layout) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_english) {
            addFragmentOnTop(new KamusFragment(getString(R.string.english)), R.id.frame_kamus);
            drawerLayout.closeMenu(true);
        } else if (id == R.id.nav_indonesia) {
            addFragmentOnTop(new KamusFragment(getString(R.string.indonesia)), R.id.frame_kamus);
            drawerLayout.closeMenu(true);
        }

        return true;
    }

    @OnClick(R.id.drawer_button)
    public void onClick() {
        drawerLayout.openMenu(true);
    }
}
