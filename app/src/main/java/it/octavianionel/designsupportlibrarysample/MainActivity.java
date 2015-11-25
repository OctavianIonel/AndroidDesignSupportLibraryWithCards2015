package it.octavianionel.designsupportlibrarysample;

import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by octavian on 7/29/15.
 */

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabBtn;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private CoordinatorLayout rootLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Fragment fragment = null;
                menuItem.setChecked(true);
                switch (id) {
                    case R.id.navItem1:
                        toolbar.setVisibility(View.GONE);
                        fragment = new FragmentOffers();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content, fragment)
                                .commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navItem2:
                        toolbar.setVisibility(View.VISIBLE);
                        fragment = new Fragment2();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content, fragment)
                                .commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navItem3:
                        toolbar.setVisibility(View.VISIBLE);
                        fragment = new Fragment3();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content, fragment)
                                .commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navItem4:
                        toolbar.setVisibility(View.VISIBLE);
                        fragment = new Fragment4();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content, fragment)
                                .commit();
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Fragment fragment = new FragmentOffers();
        toolbar.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //if the navigation view is open, when I press the "back" key, do not exit the application
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
