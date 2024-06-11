package com.example.fondosdepantalladma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.example.fondosdepantalladma.FragmentosCliente.AcercaDeCliente;
import com.example.fondosdepantalladma.FragmentosCliente.CompartirCliente;
import com.example.fondosdepantalladma.FragmentosCliente.InicioCliente;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.nav_view);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new InicioCliente()).commit();
            navigationView.setCheckedItem(R.id.InicioCliente);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.InicioCliente) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioCliente()).commit();
        } else if (itemId == R.id.AcercaDe) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AcercaDeCliente()).commit();
        } else if (itemId == R.id.Compartir) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompartirCliente()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}