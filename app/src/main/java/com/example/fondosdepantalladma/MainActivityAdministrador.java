package com.example.fondosdepantalladma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivityAdministrador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);

        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.nav_viewA);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}