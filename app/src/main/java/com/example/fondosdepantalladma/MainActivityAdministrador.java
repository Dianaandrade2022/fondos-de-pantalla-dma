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

import com.example.fondosdepantalladma.FragmentosAdministrador.InicioAdmin;
import com.example.fondosdepantalladma.FragmentosAdministrador.ListAdmin;
import com.example.fondosdepantalladma.FragmentosAdministrador.PerfilAdmin;
import com.example.fondosdepantalladma.FragmentosAdministrador.RegistrarAdmin;
import com.google.android.material.navigation.NavigationView;

public class MainActivityAdministrador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);

        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_A);

        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new InicioAdmin()).commit();
            navigationView.setCheckedItem(R.id.InicioAdmin);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.InicioAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new InicioAdmin()).commit();
        } else if (itemId == R.id.PerfilAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new PerfilAdmin()).commit();
        } else if (itemId == R.id.RegistrarAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new RegistrarAdmin()).commit();
        } else if (itemId == R.id.ListarAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new ListAdmin()).commit();
        } else if (itemId == R.id.Salir) {
            // Lógica para salir, por ejemplo, cerrando la sesión
            Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}