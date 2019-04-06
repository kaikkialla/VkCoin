package com.example.vkcoin.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.vkcoin.Upgrades;
import com.example.vkcoin.R;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.ui.fragment.CoinFragment.CoinFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static float density;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getScreenSize();
        checkLogin();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new CoinFragment()).commit();
        }
    }


    private void checkLogin() {
        SharedPreferences firstLogin = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        boolean login = firstLogin.getBoolean("login", false);
        if(!login) {
            SharedPreferences.Editor editor = firstLogin.edit();
            editor.putBoolean("login", true).apply();

            BalanceRepository.getInstance(getApplicationContext()).increaseBg(0.001f);
            BalanceRepository.getInstance(getApplicationContext()).increaseClick(0.005f);

            BalanceRepository.getInstance(getApplicationContext()).addmodule(new Upgrades(1, "CPU", 0.1f, 0.003f));
            BalanceRepository.getInstance(getApplicationContext()).addmodule(new Upgrades(1, "CPU", 0.1f, 0.003f));
            BalanceRepository.getInstance(getApplicationContext()).addmodule(new Upgrades(2, "Superpc", 1f, 0.03f));

            BalanceRepository.getInstance(getApplicationContext()).increaseBalance(11.111f);
        }
    }


    public void getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = getResources().getDisplayMetrics().density;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
    }
}
