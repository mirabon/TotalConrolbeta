package ru.rassvetmedia.totalconrolbeta.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import ru.rassvetmedia.totalconrolbeta.fragments.AbstractTabFragment;
import ru.rassvetmedia.totalconrolbeta.fragments.AccountsFragment;
import ru.rassvetmedia.totalconrolbeta.fragments.ReportFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {
    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initFragments(context);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);

    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initFragments(Context context) {
        this.tabs = new HashMap<>();
        tabs.put(0, AccountsFragment.getInstance(context));
        tabs.put(1, ReportFragment.getInstance(context));
    }
}