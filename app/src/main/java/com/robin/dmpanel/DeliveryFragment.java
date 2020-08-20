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
public class DeliveryFragment extends Fragment {

    private View deliveryView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAccessAdapterDelivery tabAccessAdapterDelivery;


    public DeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        deliveryView = inflater.inflate(R.layout.fragment_delivery, container, false);

        viewPager=(ViewPager) deliveryView.findViewById(R.id.main_tab_pager_delivery_dm);
        tabAccessAdapterDelivery=new TabAccessAdapterDelivery(getChildFragmentManager(),0);
        viewPager.setAdapter(tabAccessAdapterDelivery);

        tabLayout=(TabLayout) deliveryView.findViewById(R.id.main_tabs_delivery_dm);
        tabLayout.setupWithViewPager(viewPager);


        return deliveryView;
    }

}
