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
public class GeneralPickupFragment extends Fragment {

    private View generalPickupView;
    private RecyclerView recyclerView;
    private GeneralPickupAdapter generalPickupAdapter;

    private ArrayList<ParcelDm> parcelArrayList=new ArrayList<>();
    private DatabaseReference rootRef;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    private String currentUserId;


    public GeneralPickupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        generalPickupView = inflater.inflate(R.layout.fragment_general_pickup, container, false);

        mAuth=FirebaseAuth.getInstance();

        currentUserId=mAuth.getCurrentUser().getUid();

        rootRef= FirebaseDatabase.getInstance().getReference();
        recyclerView=(RecyclerView)generalPickupView.findViewById(R.id.general_pickUp_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        generalPickupAdapter =new GeneralPickupAdapter(parcelArrayList,getContext());
        recyclerView.setAdapter(generalPickupAdapter);


        loadingBar=new ProgressDialog(getActivity());




        return generalPickupView;
    }

    @Override
    public void onStart() {
        super.onStart();

        loadingBar.setTitle("General Parcel");
        loadingBar.setMessage("Please Wait,until the loading is completed.....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        rootRef.child("Parcel-DeliveryMan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    parcelArrayList.clear();

                    for(DataSnapshot model:dataSnapshot.getChildren()){
                        ParcelDm parcelDm=model.getValue(ParcelDm.class);

                        if(parcelDm!=null){
                            if(parcelDm.getAction().equals("Pick-Up")  && parcelDm.getType().equals("Regular") && parcelDm.getDriverId().equals(currentUserId)){

                                parcelArrayList.add(parcelDm);
                                generalPickupAdapter.notifyDataSetChanged();
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
