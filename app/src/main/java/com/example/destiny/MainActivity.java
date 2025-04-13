package com.example.destiny;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.destiny.app.BattleFragment;
import com.example.destiny.app.GuildFragment;
import com.example.destiny.app.TrainingFragment;
import com.example.destiny.domain.area.Battle;
import com.example.destiny.domain.area.Guild;
import com.example.destiny.domain.area.Training;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        // set data directory of Battle, Guild and Training areas
//        Battle.getInstance().setFileName(getDataDir().toString());
//        Guild.getInstance().setFileName(getDataDir().toString());
//        Training.getInstance().setFileName(getDataDir().toString());

        // fetch data from files
        Battle.getInstance().fetchData();
        Guild.getInstance().fetchData();
        Training.getInstance().fetchData();



        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // listener for item selection on nav bar
        bottomNavigationView.setOnItemSelectedListener(
        new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                replaceFragment(item);
                return true;
            }
        }
        );

        bottomNavigationView.setItemIconTintList(null);

    }
    // method that determines the selected menu item and
    // replacing the active fragment accordingly in fragmentContainerView
    private void replaceFragment(MenuItem item)
    {
        Fragment selectedFragment = null;

        int id = item.getItemId();

        if (id == R.id.trainButton)
        {
            selectedFragment = new TrainingFragment();
        }
        else if (id == R.id.battleButton)
        {
            selectedFragment = new BattleFragment();
        }
        else if (id == R.id.guildButton)
        {
            selectedFragment = new GuildFragment();
        }

        if(selectedFragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, selectedFragment)
                    .commit();
        }
    }
}