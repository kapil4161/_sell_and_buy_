package com.example.valarmorghulis.firebaseauth;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
	//The navigation drawer slides in from the left and contains the navigation destinations for your app.
//    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashIntent = new Intent(DrawerActivity.this,Activity_splash_screen.class);
                startActivity(SplashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		/*Toolbar was introduced in Android Lollipop, API 21 release and is the spiritual successor of the ActionBar.
		It's a ViewGroup that can be placed anywhere in your XML layouts. Toolbar's appearance and behavior 
		can be more easily customized than the ActionBar. Toolbar works well with apps targeted to API 21 and above.*/
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
		/*Represents a standard navigation menu for application. The menu contents can be populated by a menu resource file.
		NavigationView is typically placed inside a DrawerLayout.*/
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
		/*This class provides a handy way to tie together the functionality of DrawerLayout and the framework ActionBar
		to implement the recommended design for navigation drawers.
		To use ActionBarDrawerToggle, create one in your Activity and call through to the following methods
		corresponding to your Activity callbacks:
		onConfigurationChanged
		onOptionsItemSelected*/
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new HomeFragment()).commit();
                break;

            case R.id.nav_my_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new ProfileFragment()).commit();
                break;

            case R.id.nav_sell:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new SellFragment()).commit();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new AboutFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new FeedbackFragment()).commit();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

