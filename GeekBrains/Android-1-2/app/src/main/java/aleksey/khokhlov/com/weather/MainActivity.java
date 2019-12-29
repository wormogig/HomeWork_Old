package aleksey.khokhlov.com.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupNavigationDrawer(toolbar);
        setFragment(FragmentWeather.TAG);
        startService(new Intent(MainActivity.this, WeatherService.class));
    }

    private void setFragment(String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = initFragmentByTag(tag);
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        transaction.commitAllowingStateLoss();
    }

    private Fragment initFragmentByTag (String tag) {
        if (tag == FragmentWeather.TAG) {
            return new FragmentWeather();
        } else if (tag == FragmentSetting.TAG) {
            return new FragmentSetting();
        }
        return null;
    }

    // Подключение меню
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sourse_accuaweather: {
                Toast.makeText(getApplicationContext(), getString(R.string.menu_description_aw), Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.sourse_gismeteo: {
                Toast.makeText(getApplicationContext(), getString(R.string.menu_description_gm), Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_main: {
                setFragment(FragmentWeather.TAG);
                break;
            }
            case R.id.nav_settings: {
                setFragment(FragmentSetting.TAG);
                break;
            }
            case R.id.nav_about: {
                break;
            }
            case R.id.nav_feedback: {
                break;
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
