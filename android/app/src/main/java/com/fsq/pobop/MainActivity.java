package com.fsq.pobop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mBottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_pantry, R.id.nav_recipe)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Some stuff for the filter popup
        mBottomSheet = findViewById(R.id.bottom_sheet);
        mButton = findViewById(R.id.extended_fab);

        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);

        mButton.setOnClickListener(v -> mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));

}

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("id", null) == null) {
            Intent newIntent = new Intent(MainActivity.this, AuthenticationActivity.class);
            startActivity(newIntent);
        }
    }

}