package com.example.vkcoin.dagger;


import com.example.vkcoin.ui.MainActivity;
import com.example.vkcoin.ui.fragment.CoinFragment.CoinFragment;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(CoinFragment coinFragment);//Зависымый класс
    void inject(MainActivity mainActivity);//Зависымый класс
}