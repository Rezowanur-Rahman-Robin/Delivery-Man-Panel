package com.robin.dmpanel;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

   private View HomeView;
   private int u_Delivery,u_pickup;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String currentUserId;
    private DatabaseReference rootRef;

    private ProgressDialog loadingBar;

    private TextView inconpletePickup,incompleteDelivery;
    private ImageView goPickUpButton,goDeliveryButton;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeView = inflater.inflate(R.layout.fragment_home, container, false);


        mAuth=FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if(currentUser!=null){
            currentUserId=currentUser.getUid();
        }

        rootRef= FirebaseDatabase.getInstance().getReference();

        inconpletePickup=HomeView.findViewById(R.id.incomplete_pickup_dm_dashBoard);
        incompleteDelivery=HomeView.findViewById(R.id.incomplete_delivery_dm_dashBoard);
        goPickUpButton=HomeView.findViewById(R.id.incomplete_pickup_go_button);
        goDeliveryButton=HomeView.findViewById(R.id.incomplete_delivery_go_button);

        loadingBar=new ProgressDialog(getContext());

        goPickUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,new PickUpFragment()).commit();
            }
        });

        goDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,new DeliveryFragment()).commit();
            }
        });




        return HomeView;
    }

    @Override
    public void onStart() {
        super.onStart();


        loadingBar.setTitle("Delivery Man Profile");
        loadingBar.setMessage("Please Wait Until The Profile Loading Successfully Completed...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        u_Delivery=0;
        u_pickup=0;

        rootRef.child("Parcel-DeliveryMan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot model:dataSnapshot.getChildren()){

                        ParcelDm parcelDm=model.getValue(ParcelDm.class);

                        if(parcelDm!=null){

                            if(parcelDm.getAction().equals("Pick-Up") && parcelDm.getDriverId().equals(currentUserId)){

                                rootRef.child("Parcel").child(parcelDm.getParcelId()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            Parcel parcel=dataSnapshot.getValue(Parcel.class);

                                            if(parcel!=null){
                                                if(parcel.getStatus().equals("Pending")|| parcel.getStatus().equals("Verified")){
                                                    u_pickup++;
                                                    inconpletePickup.setText(String.valueOf(u_pickup));
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                                rootRef.child("Merchant-parcel").child(parcelDm.getParcelId()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){

                                            Mparcel mparcel=dataSnapshot.getValue(Mparcel.class);

                                            if(mparcel!=null){
                                                if(mparcel.getStatus().equals("Pending")|| mparcel.getStatus().equals("Verified")){
                                                    u_pickup++;
                                                    inconpletePickup.setText(String.valueOf(u_pickup));
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
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

            }
        });


        rootRef.child("Parcel-DeliveryMan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot model:dataSnapshot.getChildren()){
                        final ParcelDm parcelDm=model.getValue(ParcelDm.class);

                        if(parcelDm!=null){

                            if(parcelDm.getAction().equals("Delivery") && parcelDm.getDriverId().equals(currentUserId)){




                                rootRef.child("Parcel").child(parcelDm.getParcelId()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            Parcel parcel=dataSnapshot.getValue(Parcel.class);



                                            if(parcel!=null){



                                                if(parcel.getStatus().equals("Picked Up")|| parcel.getStatus().equals("Shipped") || parcel.getStatus().equals("Verified")){
                                                    u_Delivery++;
                                                    incompleteDelivery.setText(String.valueOf(u_Delivery));
                                                }
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                rootRef.child("Purchase-Order").child(parcelDm.getParcelId()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            Purchase purchase=dataSnapshot.getValue(Purchase.class);

                                            if(purchase!=null){
                                                if(purchase.getStatus().equals("Picked Up")|| purchase.getStatus().equals("Shipped") || purchase.getStatus().equals("Verified")){
                                                    u_Delivery++;
                                                    incompleteDelivery.setText(String.valueOf(u_Delivery));
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
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
