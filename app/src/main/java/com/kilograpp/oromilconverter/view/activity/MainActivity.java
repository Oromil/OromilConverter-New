package com.kilograpp.oromilconverter.view.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.adapters.ViewPagerAdapter;
import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.presenter.MainActivityPresenter;
import com.kilograpp.oromilconverter.view.fragments.ConverterFragment;
import com.kilograpp.oromilconverter.view.fragments.StatisticFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    private boolean KEYBOARD_SHOWING = false;

    private MainActivityPresenter presenter;
    private ConverterFragment converterFragment;
    private SwipeRefreshLayout swipeRefreshLayout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(converterFragment, "People");
        adapter.addFragment(new StatisticFragment(), "Group");
        viewPager.setAdapter(adapter);
    }

    private void init() {

        setSupportActionBar(findViewById(R.id.toolBar));

        presenter = new MainActivityPresenter();
        presenter.attachView(this);

        converterFragment = new ConverterFragment();

        ViewPager mViewPager = findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        TabLayout mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadData());

        presenter.loadData();
    }

    @Override
    public void showProgress(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void updateData(List<Currency> data) {
        converterFragment.updateList(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        new MaterialDialog.Builder(this).title("title")
                .items(presenter.getCurrenciesNames())
                .itemsCallbackMultiChoice(presenter.getSelectedCurrenciesIndexes(), (dialog, which, text) -> true)
                .onPositive((dialog, which) -> {
                    presenter.saveSelectedCurrencies(dialog.getSelectedIndices());
                    presenter.loadData();
                    dialog.dismiss();
                })
                .alwaysCallMultiChoiceCallback()
                .positiveText("ok")
                .autoDismiss(false)
                .show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        KEYBOARD_SHOWING = false;
        super.onBackPressed();
    }

    public void setKeyboardShowing(boolean show) {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if ((!(!show && !KEYBOARD_SHOWING)) && inputManager != null) {
            inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            KEYBOARD_SHOWING = show;
        }
    }
}
