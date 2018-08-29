package ru.shadowsparky.news;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import ru.shadowsparky.news.MVP.fragments.category.MenuFragment;

public class ListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView mNavigation;
    DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.WithoutBarTransparent);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        mNavigation = findViewById(R.id.navigation_drawer);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavigation.setNavigationItemSelectedListener(this);
        firstLaunch();
    }

    public void firstLaunch() {
        mNavigation.setCheckedItem(0);
        inflate("football");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()) {
            case R.id.football: {
                inflate("football");
                break;
            }
            case R.id.hockey: {
                inflate("hockey");
                break;
            }
            case R.id.tennis: {
                inflate("tennis");
                break;
            }
            case R.id.basketball: {
                inflate("basketball");
                break;
            }
            case R.id.volleyball: {
                inflate("volleyball");
                break;
            }
            case R.id.cybersport: {
                inflate("cybersport");
                break;
            }
        }
        mDrawer.closeDrawers();
        return true;
    }

    private void inflate(String category) {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CATEGORY", category);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
