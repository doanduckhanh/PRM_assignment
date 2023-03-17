package com.example.cafemanagerapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.Fragments.DisplayFoodCrudFragment;
import com.example.cafemanagerapp.Fragments.DisplayHomeFragment;
import com.example.cafemanagerapp.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    TextView txt_menu_username;
    SharedPreferences sharedPreferences;
    Boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_trangchu);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        View view = navigationView.getHeaderView(0);
        txt_menu_username = (TextView) view.findViewById(R.id.txt_menu_tennv);

        User u = new User();
        u.setUsername("1");
        u.setPassword("1");
        u.setAdmin(true);
        AppDatabase.getInstance(this).userDAO().insert(u);

        // Todo: handler toolbar and navigation
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Todo: create button open navigation
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opentoggle, R.string.closetoggle) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        // Todo: binding username Extras
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        txt_menu_username.setText("Xin chào " + username + "!");
        sharedPreferences = getSharedPreferences("role", Context.MODE_PRIVATE);
        isAdmin = sharedPreferences.getBoolean("isAdmin", false);


        // Todo: set fragment home is default
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tranDisplayHome = fragmentManager.beginTransaction();
        DisplayHomeFragment displayHomeFragment = new DisplayHomeFragment();
        tranDisplayHome.replace(R.id.contentView, displayHomeFragment);
        tranDisplayHome.commit();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                FragmentTransaction tranDisplayHome = fragmentManager.beginTransaction();
                DisplayHomeFragment displayHomeFragment = new DisplayHomeFragment();
                tranDisplayHome.replace(R.id.contentView,displayHomeFragment);
                tranDisplayHome.commit();
                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_statistic:
//                FragmentTransaction tranDisplayStatistic = fragmentManager.beginTransaction();
//                DisplayStatisticFragment displayStatisticFragment = new DisplayStatisticFragment();
//                tranDisplayStatistic.replace(R.id.contentView,displayStatisticFragment);
//                tranDisplayStatistic.commit();
//                navigationView.setCheckedItem(item.getItemId());
//                drawerLayout.closeDrawers();
                break;

            case R.id.nav_table:
//                FragmentTransaction tranDisplayTable = fragmentManager.beginTransaction();
//                DisplayTableFragment displayTableFragment = new DisplayTableFragment();
//                tranDisplayTable.replace(R.id.contentView,displayTableFragment);
//                tranDisplayTable.commit();
//                navigationView.setCheckedItem(item.getItemId());
//                drawerLayout.closeDrawers();
                break;

            case R.id.nav_category:
                FragmentTransaction tranDisplayMenu = fragmentManager.beginTransaction();
                DisplayFoodCrudFragment displayCategoryFragment = new DisplayFoodCrudFragment();
                tranDisplayMenu.replace(R.id.contentView, displayCategoryFragment);
                tranDisplayMenu.commit();
                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawers();
//
                 break;

            case R.id.nav_staff:
                if(isAdmin == true){
//                    FragmentTransaction tranDisplayStaff = fragmentManager.beginTransaction();
//                    DisplayStaffFragment displayStaffFragment = new DisplayStaffFragment();
//                    tranDisplayStaff.replace(R.id.contentView,displayStaffFragment);
//                    tranDisplayStaff.commit();
//                    navigationView.setCheckedItem(item.getItemId());
//                    drawerLayout.closeDrawers();
                    Toast.makeText(getApplicationContext(),"Bạn có quyền truy cập",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Bạn không có quyền truy cập",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.nav_logout:
                Intent intent = new Intent(getApplicationContext(),WellcomeActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }


}