package com.robin.dmpanel;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickUpFragment extends Fragment {

    private View pickUpView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAccessAdapterPickup tabAccessAdapterPickup;



    public PickUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pickUpView = inflater.inflate(R.layout.fragment_pick_up, container, false);

        viewPager=(ViewPager)pickUpView.findViewById(R.id.main_tab_pager_pickup_dm);
        tabAccessAdapterPickup = new TabAccessAdapterPickup(getChildFragmentManager(),0);
        viewPager.setAdapter(tabAccessAdapterPickup);

        tabLayout =(TabLayout) pickUpView.findViewById(R.id.main_tabs_pickup_dm);
        tabLayout.setupWithViewPager(viewPager);



        return pickUpView;
    }

}
