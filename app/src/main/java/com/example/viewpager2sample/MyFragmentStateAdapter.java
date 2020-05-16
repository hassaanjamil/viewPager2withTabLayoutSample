package com.example.viewpager2sample;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

// An equivalent ViewPager2 adapter class
public class MyFragmentStateAdapter extends FragmentStateAdapter {

    private List<Fragment> mListFragments;

    public MyFragmentStateAdapter(FragmentActivity fa, List<Fragment> fragments) {
        super(fa);
        mListFragments = fragments;
    }

    @Override
    public Fragment createFragment(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mListFragments.size();
    }
}