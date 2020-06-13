package com.example.pendulumtestjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pendulumtestjava.firebase.FirebaseAuthActivity;
import com.example.pendulumtestjava.fragments.MainFragment;
import com.example.pendulumtestjava.fragments.about.AboutFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    TextView email;
    TextView name;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        email = headerView.findViewById(R.id.email);
        name = headerView.findViewById(R.id.name);
        profilePic = headerView.findViewById(R.id.profilePic);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_main);
        }

        mAuth = FirebaseAuth.getInstance();

        updateDrawer(mAuth.getCurrentUser());
    }

    private void updateDrawer(FirebaseUser user)
    {
        if(user != null)
        {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());

            this.name.setText(name);
            this.email.setText(email);

            if(user.getPhotoUrl() == null)
            {
                Picasso.get().load(R.drawable.logosized).into(this.profilePic);
            }else {
                Picasso.get().load(photo).into(this.profilePic);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
            case R.id.nav_logOut:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(
                                        MainActivity.this,
                                        FirebaseAuthActivity.class));
                                MainActivity.this.finish();
                            } else {
                                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                // Report error to user
                            }
                        });
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


}
