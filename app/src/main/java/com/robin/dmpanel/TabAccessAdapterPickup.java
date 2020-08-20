package com.robin.dmpanel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessAdapterPickup extends FragmentPagerAdapter {

    public TabAccessAdapterPickup(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                GeneralPickupFragment generalPickupFragment=new GeneralPickupFragment();
                return generalPickupFragment;

            case 1:
                MerchantPickupFragment merchantPickupFragment =new MerchantPickupFragment();
                return merchantPickupFragment;


            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "General PickUp";


            case 1:
                return "Merchant PickUp";

            default:
                return null;

        }

    }


}
