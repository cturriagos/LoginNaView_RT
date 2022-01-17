package com.example.loginnaview_rt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import modelo.Modelo;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Modelo mod;
    Bundle bundle;
    ActionBarDrawerToggle actionBar;
    DrawerLayout drawer;
    Toolbar toolBar;
    NavigationView navigation;
    FragmentManager frgManager;
    FragmentTransaction frgTransaction;
    View view;
    TextView txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        bundle = this.getIntent().getExtras();
        mod = (Modelo) bundle.getSerializable("Modelo");
        drawer = findViewById(R.id.drawerLyt);
        toolBar = findViewById(R.id.lytToolbar);
        navigation = findViewById(R.id.lyt_Inicio);
        setSupportActionBar(toolBar);
        navigation.setNavigationItemSelectedListener(this);

        view = navigation.getHeaderView(0);
        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserName.setText(mod.getUsuario());
        if(mod.getTipo_usuario().equals("user")) {
            Menu menu = navigation.getMenu();

            MenuItem item = menu.findItem(R.id.itemAjustes);
            item.setVisible(false);
        }

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.itemHome) {
            frgManager = getSupportFragmentManager();
            frgTransaction = frgManager.beginTransaction();
            frgTransaction.replace(R.id.frame, new fragment_one());
            frgTransaction.commit();
        }
        else if (item.getItemId() == R.id.itemAjustes) {
            frgManager = getSupportFragmentManager();
            frgTransaction = frgManager.beginTransaction();
            frgTransaction.replace(R.id.frame, new fragment_two());
            frgTransaction.commit();
        }
        return true;
    }
}