package org.victoryaxon.twiterclient.main.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by VictorYaxon on 28/06/16
 */
public class MainSectionsPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Fragment[] fragments;

    public MainSectionsPagerAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
