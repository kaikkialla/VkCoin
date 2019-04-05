package com.example.vkcoin.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.vkcoin.R;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.ui.fragment.CoinFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new CoinFragment()).commit();
        }
        SharedPreferences firstLogin = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        Boolean login = firstLogin.getBoolean("login", false);
        if(login == false) {
            SharedPreferences.Editor editor = firstLogin.edit();
            editor.putBoolean("login", true).apply();
            BalanceRepository.getInstance(getApplicationContext()).increaseBg(0.001f);
            BalanceRepository.getInstance(getApplicationContext()).increaseClick(0.05f);
        }
        Log.e("fadada", String.valueOf(login));
    }
}
