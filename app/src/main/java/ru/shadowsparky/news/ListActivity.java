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
    public static final String FOOTBALL_ITEM = "football";
    public static final String HOCKEY_ITEM = "hockey";
    public static final String TENNIS_ITEM = "tennis";
    public static final String BASKETBALL_ITEM = "basketball";
    public static final String VOLLEYBALL_ITEM = "volleyball";
    public static final String CYBERSPORT_ITEM = "cybersport";
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
        openFirstCategory();
    }

    public void openFirstCategory() {
        mNavigation.setCheckedItem(R.id.football);
        inflate(FOOTBALL_ITEM);
        setTitle("Футбол");
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
                inflate(FOOTBALL_ITEM);
                break;
            }
            case R.id.hockey: {
                inflate(HOCKEY_ITEM);
                break;
            }
            case R.id.tennis: {
                inflate(TENNIS_ITEM);
                break;
            }
            case R.id.basketball: {
                inflate(BASKETBALL_ITEM);
                break;
            }
            case R.id.volleyball: {
                inflate(VOLLEYBALL_ITEM);
                break;
            }
            case R.id.cybersport: {
                inflate(CYBERSPORT_ITEM);
                break;
            }
        }
        setTitle(menuItem.getTitle());
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
