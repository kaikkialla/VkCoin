package com.example.vkcoin.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.vkcoin.R;
import com.example.vkcoin.Upgrade;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;
import com.example.vkcoin.ui.fragment.CoinFragment.CoinFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.vkcoin.ui.MainActivity.UpgradeType.CPU;
import static com.example.vkcoin.ui.MainActivity.UpgradeType.SERVER;

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

            UpgradeRepository.getInstance(getApplicationContext()).add(new Upgrade(CPU, "a", 0.01f,0.001f));
            UpgradeRepository.getInstance(getApplicationContext()).add(new Upgrade(SERVER, "b", 0.1f,0.01f));
            UpgradeRepository.getInstance(getApplicationContext()).add(new Upgrade(SERVER, "c", 0.1f,0.01f));


            //Upgrade upgrade = new Upgrade();
            //upgrade.setA(2);
        }
    }


    public void getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = getResources().getDisplayMetrics().density;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
    }

    public static enum UpgradeType{
        CPU, SERVER
    }
}
