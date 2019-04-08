package com.example.vkcoin.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.vkcoin.R;
import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;
import com.example.vkcoin.repository.UpgradeRepository;
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

            CPUmodel cpu = new CPUmodel();
            ServerModel server = new ServerModel();
            createCPU(cpu);
            createServer(server);
        }
    }


    private void createCPU(CPUmodel cpu) {
        cpu.setId(0);
        cpu.setPrice(0.01f);
        cpu.setGain(0.001f);
        cpu.setName("name1");
        cpu.setQuantity(1);
        //Log.e("TEST", String.valueOf(cpu));
        UpgradeRepository.getInstance(getApplicationContext()).saveCPU(cpu);
    }

    private void createServer(ServerModel server) {
        server.setId(0);
        server.setPrice(0.1f);
        server.setGain(0.01f);
        server.setName("name2");
        server.setQuantity(2);
        //Log.e("TEST", String.valueOf(server));
        UpgradeRepository.getInstance(getApplicationContext()).saveServer(server);
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
