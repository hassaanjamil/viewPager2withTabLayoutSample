package com.example.viewpager2sample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    TabLayout tabLayout;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> tabTexts = new ArrayList<>();
        tabTexts.add(getString(R.string.fragment1));
        tabTexts.add(getString(R.string.fragment2));

        final List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(new Fragment1());
        listFragments.add(new Fragment2());

        // SETUP VIEWPAGER2
        final ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(toast != null)
                    toast.cancel();

                toast = Toast.makeText(MainActivity.this,
                        listFragments.get(position).getClass().getSimpleName(), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(this, listFragments);
        viewPager2.setAdapter(adapter);

        // SETUP TAB LAYOUT
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(tabTexts.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabTexts.get(1)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(tabTexts.get(position)))
                .attach();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
