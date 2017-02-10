package com.example.android.miwok.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.miwok.Fragments.ColorsFragment;
import com.example.android.miwok.Fragments.FamilyFragment;
import com.example.android.miwok.Fragments.NumbersFragment;
import com.example.android.miwok.Fragments.PhrasesFragment;

/**
 * Created by kristenwoodward on 12/5/16.
 */

public class CategoryAdapter extends FragmentPagerAdapter {


    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new ColorsFragment();
            case 1: return new FamilyFragment();
            case 2: return new NumbersFragment();
            case 3: return new PhrasesFragment();
            default: return new ColorsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
