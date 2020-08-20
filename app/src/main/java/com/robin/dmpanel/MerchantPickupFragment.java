package com.robin.dmpanel;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MerchantPickupFragment extends Fragment {

    private View MpickUpView;
    private RecyclerView recyclerView;
    private MerchantPickupAdapter merchantPickupAdapter;

    private ArrayList<ParcelDm> parcelDmArrayList=new ArrayList<>();
    private DatabaseReference rootRef;
    private ProgressDialog loadingBar;

    private String currentUserId;
    private FirebaseAuth mAuth;


    public MerchantPickupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MpickUpView = inflater.inflate(R.layout.fragment_merchant_pickup, container, false);

        mAuth=FirebaseAuth.getInstance();

        currentUserId=mAuth.getCurrentUser().getUid();

        rootRef= FirebaseDatabase.getInstance().getReference();
        recyclerView=(RecyclerView) MpickUpView.findViewById(R.id.merchant_pickUp_recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        merchantPickupAdapter=new MerchantPickupAdapter(parcelDmArrayList,getContext());
        recyclerView.setAdapter(merchantPickupAdapter);

        loadingBar=new ProgressDialog(getActivity());



        return MpickUpView;
    }

    @Override
    public void onStart() {
        super.onStart();

        rootRef.child("Parcel-DeliveryMan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    parcelDmArrayList.clear();

                    for(DataSnapshot model:dataSnapshot.getChildren()){
                        ParcelDm parcelDm=model.getValue(ParcelDm.class);

                        if(parcelDm!=null){
                            if(parcelDm.getAction().equals("Pick-Up")  && parcelDm.getType().equals("Merchant") && parcelDm.getDriverId().equals(currentUserId)){


                                parcelDmArrayList.add(parcelDm);
                                merchantPickupAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    loadingBar.dismiss();
                }
                else
                {
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loadingBar.dismiss();
            }
        });
    }
}
