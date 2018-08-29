package ru.shadowsparky.news.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.shadowsparky.news.news.api.Api;
import ru.shadowsparky.news.news.pojo.category.CategoryEvents;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView mNavigation;
    DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        check("football");
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
                check("football");
                break;
            }
            case R.id.hockey: {
                check("hockey");
                break;
            }
            case R.id.tennis: {
                check("tennis");
                break;
            }
            case R.id.basketball: {
                check("basketball");
                break;
            }
            case R.id.volleyball: {
                check("volleyball");
                break;
            }
            case R.id.cybersport: {
                check("cybersport");
                break;
            }
        }
        mDrawer.closeDrawers();
        return true;
    }

    private void check(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mikonatoruri.win")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Observable.just("mock")
                .observeOn(Schedulers.io())
                .map(item -> api.getCategory(category))
                .map(item -> item.blockingFirst())
                .subscribe(
                        next -> inflate(next, category),
                        error -> Log.println(Log.DEBUG, "MAIN_TAG", error.toString())
                );
    }

    private void inflate(CategoryEvents events, String category) {
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("EVENTS", events);
        bundle.putString("CATEGORY", category);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
