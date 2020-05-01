package com.example.pendulumtestjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.pendulumFragments.PendulumsFragment;
import com.example.pendulumtestjava.fragments.savingsFragment.FragmentList;
import com.google.android.material.tabs.TabLayout;

public class MainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new PendulumsFragment(), "Pendulums");
        adapter.AddFragment(new FragmentList(), "Saved");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }

}
