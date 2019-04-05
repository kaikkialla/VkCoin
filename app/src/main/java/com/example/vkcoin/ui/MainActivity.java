package com.example.vkcoin.ui;

import android.os.Bundle;

import com.example.vkcoin.R;
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
    }
}
