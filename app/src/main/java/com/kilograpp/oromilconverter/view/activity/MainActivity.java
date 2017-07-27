package com.kilograpp.oromilconverter.view.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kilograpp.oromilconverter.adapters.ViewPagerAdapter;
import com.kilograpp.oromilconverter.presenter.MainActivityPresenter;
import com.kilograpp.oromilconverter.presenter.MainMvpView;
import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.view.fragments.ConverterFragment;
import com.kilograpp.oromilconverter.view.fragments.StatisticFragment;

import lombok.Getter;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    ViewPagerAdapter adapter;
    private ViewPager mVievPager;
    private TabLayout mTabLayout;
    private SwipeRefreshLayout mSwipeLayout;

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

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(() -> mPresenter.updateData()
                .subscribe(o -> showProgress(false),
                            throwable -> showProgress(false)));

        mVievPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mVievPager);
        mVievPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mSwipeLayout.setEnabled(false);
            }

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {
                mSwipeLayout.setEnabled(true);
            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mVievPager);
    }

    @Override
    public void showProgress(boolean show) {
        mSwipeLayout.setRefreshing(show);
    }

    @Override
    public Fragment getActiveFragment() {
    return null;
    }

    @Override
    public ConverterFragment getConverterFragment() {
        return mConverterFragment;
    }
}
