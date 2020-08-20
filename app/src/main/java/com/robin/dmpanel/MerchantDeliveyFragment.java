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
public class MerchantDeliveyFragment extends Fragment {

    private View mDeliveryView;
    private RecyclerView recyclerView;
    private MerchantDeliveryAdapter merchantDeliveryAdapter;

    private ArrayList<ParcelDm> parcelDmArrayList=new ArrayList<>();
    private DatabaseReference rootRef;
    private ProgressDialog loadingBar;

    private String currentUserId;
    private FirebaseAuth mAuth;


    public MerchantDeliveyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDeliveryView = inflater.inflate(R.layout.fragment_merchant_delivey, container, false);


        mAuth=FirebaseAuth.getInstance();

        currentUserId=mAuth.getCurrentUser().getUid();

        rootRef= FirebaseDatabase.getInstance().getReference();
        recyclerView=(RecyclerView) mDeliveryView.findViewById(R.id.merchant_delivery_recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);


        merchantDeliveryAdapter=new MerchantDeliveryAdapter(parcelDmArrayList,getContext());
        recyclerView.setAdapter(merchantDeliveryAdapter);

        loadingBar=new ProgressDialog(getActivity());


        return mDeliveryView;
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
                            if(parcelDm.getAction().equals("Delivery")  && parcelDm.getType().equals("Merchant") && parcelDm.getDriverId().equals(currentUserId)){


                                parcelDmArrayList.add(parcelDm);
                                merchantDeliveryAdapter.notifyDataSetChanged();

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
