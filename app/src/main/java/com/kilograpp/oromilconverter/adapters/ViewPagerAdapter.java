package com.kilograpp.oromilconverter.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kilograpp.oromilconverter.view.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Oromil on 19.07.2017.
 */

public class ViewPagerAdapter <T extends Fragment>extends FragmentStatePagerAdapter {

    private final List<T> mFragmentList = new ArrayList<>();
    private final List<String> mTitleList = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    public void addFragment(T fragment, String title){
        mFragmentList.add(fragment);
        mTitleList.add(title);
    }

    public List<T> getFragments(){
        return null;
    }
}
