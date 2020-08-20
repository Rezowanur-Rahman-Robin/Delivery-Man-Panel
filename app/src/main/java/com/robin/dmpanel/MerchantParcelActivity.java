package com.robin.dmpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MerchantParcelActivity extends AppCompatActivity {

    private String parcelId;
    private TextView parcelNo,mName,bName,mPhone,mThana,mDetailsAddrees;
    private Button completeButton;
    private ImageButton callButton;

    private RecyclerView recyclerView;
    private MParcelProductAdapter mParcelProductAdapter;

    private ArrayList<MparcelProduct> mparcelProducts=new ArrayList<>();

    private DatabaseReference rootRef;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_parcel);

        parcelId=getIntent().getExtras().get("parcel_id").toString();

        rootRef= FirebaseDatabase.getInstance().getReference();

        parcelNo=findViewById(R.id.parcel_no_popup_dm);
        mName=findViewById(R.id.merchant_name_order_popup_dm);
        bName=findViewById(R.id.merchant_business_name_order_popup_dm);
        mPhone=findViewById(R.id.merchant_phone_order_popup_dm);
        mThana=findViewById(R.id.merchant_thana_order_popup_dm);
        mDetailsAddrees=findViewById(R.id.merchant_details_address_order_popup_dm);

        callButton=(ImageButton) findViewById(R.id.dm_call_button_merchant_parcel);

        completeButton=(Button) findViewById(R.id.complete_merchant_parcel_button_popup_dm);

        loadingBar=new ProgressDialog(this);

        recyclerView=(RecyclerView) findViewById(R.id.merchant_parcel_details_pickUp_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mParcelProductAdapter=new MParcelProductAdapter(mparcelProducts,getApplicationContext());
        recyclerView.setAdapter(mParcelProductAdapter);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CallUserAction();
            }
        });


        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeOrder();
            }
        });


    }

    private void completeOrder() {
        rootRef.child("Parcel-DeliveryMan").child(parcelId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    ParcelDm parcelDm=dataSnapshot.getValue(ParcelDm.class);

                    final String parcelIdd=parcelDm.getParcelId();

                    rootRef.child("Merchant-parcel").child(parcelIdd).child("status").setValue("Picked Up").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MerchantParcelActivity.this, "Successfully Updated Status", Toast.LENGTH_SHORT).show();
                                completeButton.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(MerchantParcelActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        loadingBar.setTitle("Merchant Parcel");
        loadingBar.setMessage("Please Wait,until the loading is completed.....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        rootRef.child("Parcel-DeliveryMan").child(parcelId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    ParcelDm parcelDm=dataSnapshot.getValue(ParcelDm.class);

                    final String parcelIdd=parcelDm.getParcelId();

                    rootRef.child("Merchant-parcel").child(parcelIdd).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                Mparcel mparcel=dataSnapshot.getValue(Mparcel.class);

                                if(mparcel.getStatus().equals("Picked Up")|| mparcel.getStatus().equals("Delivered") || mparcel.getStatus().equals("Shipped")){
                                    completeButton.setVisibility(View.GONE);
                                }


                                

                                String merchantId=mparcel.getMerchant_id();

                                rootRef.child("Merchant-parcel").child(parcelIdd).child("Products").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.exists()){
                                            mparcelProducts.clear();
                                            for(DataSnapshot model : dataSnapshot.getChildren()){

                                                MparcelProduct mparcelProduct=model.getValue(MparcelProduct.class);

                                                if(mparcelProduct!=null){
                                                    mparcelProducts.add(mparcelProduct);
                                                    mParcelProductAdapter.notifyDataSetChanged();
                                                }


                                            }
                                            loadingBar.dismiss();


                                        }
                                        else{
                                            loadingBar.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        loadingBar.dismiss();

                                    }
                                });

                                rootRef.child("Merchants").child(merchantId).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.exists()){
                                            Merchant merchant=dataSnapshot.getValue(Merchant.class);

                                            parcelNo.setText(parcelId);
                                            mName.setText(merchant.getName());
                                            bName.setText(merchant.getBusinessName());
                                            mPhone.setText(merchant.getPhone());
                                            mThana.setText(merchant.getPickUp_thana());
                                            mDetailsAddrees.setText(merchant.getAddress_details());

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        loadingBar.dismiss();

                                    }
                                });
                            }
                            else{
                                loadingBar.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void CallUserAction() {

        String s="tel:"+mPhone.getText().toString();

        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));
        startActivity(intent);

    }

}
