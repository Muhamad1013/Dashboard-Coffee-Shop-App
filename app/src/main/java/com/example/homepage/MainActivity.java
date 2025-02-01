package com.example.homepage;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        frameLayout = findViewById(R.layout.activity_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.navigation_home){
                    loadFragment(new HomeFragment(), false);

                } else if (itemId == R.id.navigation_heart){
                    loadFragment(new FavoritesFragment(), false);

                } else if (itemId == R.id.navigation_settings){
                    loadFragment(new SettingsFragment(), false);

                } else if (itemId == R.id.navigation_profile){
                    loadFragment(new ProfileFragment(), false);

                } else if (itemId == R.id.navigation_history){
                    loadFragment(new HistoryFragment(), false);

                }

                return true;
            }
        });

        loadFragment(new HomeFragment(), true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadFragment (Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frame_layout, fragment);

        } else {
            fragmentTransaction.replace(R.id.frame_layout, fragment);
        }

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}