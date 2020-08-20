package com.robin.dmpanel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessAdapterDelivery extends FragmentPagerAdapter {

    public TabAccessAdapterDelivery(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                GeneralDeliveryFragment generalDeliveryFragment=new GeneralDeliveryFragment();
                return generalDeliveryFragment;

            case 1:
                MerchantDeliveyFragment merchantDeliveyFragment =new MerchantDeliveyFragment();
                return merchantDeliveyFragment;


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
                return "General Delivery";


            case 1:
                return "Merchant Delivery";

            default:
                return null;

        }

    }
}
