package com.kilograpp.oromilconverter.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.adapters.ViewPagerAdapter;
import com.kilograpp.oromilconverter.presenter.MainActivityPresenter;
import com.kilograpp.oromilconverter.view.fragments.ConverterFragment;
import com.kilograpp.oromilconverter.view.fragments.StatisticFragment;

import lombok.Getter;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    ViewPagerAdapter adapter;

    @Getter
    private MainActivityPresenter mPresenter;

    private ConverterFragment mConverterFragment;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(mConverterFragment, "People");
        adapter.addFragment(new StatisticFragment(), "Group");
        viewPager.setAdapter(adapter);
    }
    private void init(){
        mPresenter = new MainActivityPresenter();
        mPresenter.attachView(this);

        mConverterFragment = new ConverterFragment();

        ViewPager mVievPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mVievPager);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mVievPager);
    }
}
