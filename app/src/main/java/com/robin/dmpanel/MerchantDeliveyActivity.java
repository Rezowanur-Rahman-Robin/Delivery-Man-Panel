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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MerchantDeliveyActivity extends AppCompatActivity {

    private String parcelId;
    private TextView title, O_ID,total_Amount,b_name,b_pay_type,b_order_time,b_phone,b_thana,b_address_details;
    private Button completeButton;

    private ImageButton callButton;

    private RecyclerView recyclerView;
    private MdeliveryProductAdapter mdeliveryProductAdapter;

    private ArrayList<PurchaseProduct> purchaseProductArrayList=new ArrayList<>();

    private DatabaseReference rootRef;

    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_delivey);

        parcelId=getIntent().getExtras().get("parcel_id").toString();

        rootRef= FirebaseDatabase.getInstance().getReference();

        O_ID=findViewById(R.id.parcel_no_popup_dm_delivery_merchant);
        title=findViewById(R.id.delivery_title_popup);
        total_Amount=findViewById(R.id.total_amount_popup_dm_delivery_merchant);
        b_name=findViewById(R.id.buyer_name_order_popup_dm__delivery_merchant);
        b_pay_type=findViewById(R.id.buyer_payment_type_popup_dm__delivery_merchant);
        b_order_time=findViewById(R.id.buyer_order_time_order_popup_dm__delivery_merchant);
        b_phone=findViewById(R.id.buyer_phone_order_popup_dm_delivery_merchant);
        b_thana=findViewById(R.id.buyer_thana_order_popup_dm_delivery_merchant);
        b_address_details=findViewById(R.id.buyer_details_address_order_popup_dm_delivery_merchant);

        callButton=(ImageButton) findViewById(R.id.dm_call_button_merchant_delivery);

        title.setText("Receiver Information");

        completeButton=(Button) findViewById(R.id.complete_merchant_delivery_button_popup_dm_delivery_merchant);

        loadingBar=new ProgressDialog(this);


        recyclerView=(RecyclerView) findViewById(R.id.merchant_parcel_details_delivery_recyclerView_delivery_merchant);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mdeliveryProductAdapter=new MdeliveryProductAdapter(purchaseProductArrayList,getApplicationContext());
        recyclerView.setAdapter(mdeliveryProductAdapter);


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CallUserAction();
            }
        });



        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                completeDeliveryAction();
            }
        });




    }


    @Override
    protected void onStart() {
        super.onStart();

        loadingBar.setTitle("Merchant Delivery");
        loadingBar.setMessage("Please Wait,until the loading is completed.....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        rootRef.child("Parcel-DeliveryMan").child(parcelId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    ParcelDm parcelDm=dataSnapshot.getValue(ParcelDm.class);

                    final String parcelIdd=parcelDm.getParcelId();

                    rootRef.child("Purchase-Order").child(parcelIdd).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.exists()){

                                final Purchase purchase=dataSnapshot.getValue(Purchase.class);




                                if(purchase!=null){
                                    O_ID.setText(parcelIdd);
                                    total_Amount.setText(purchase.getTotal_taka());
                                    b_name.setText(purchase.getBuyerName());
                                    b_order_time.setText(purchase.getDate());
                                    b_thana.setText(purchase.getShippingThana());
                                    b_address_details.setText(purchase.getShippingAddress());
                                    b_pay_type.setText(purchase.getPaymentType());

                                    if(purchase.getStatus().equals("Delivered")){
                                        completeButton.setVisibility(View.GONE);
                                    }

                                    rootRef.child("Users").child(purchase.getUserID()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if(dataSnapshot.exists()){
                                                String Phone_number= (String) dataSnapshot.getValue();

                                                b_phone.setText(Phone_number);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                    rootRef.child("Purchase-Order").child(parcelIdd).child("Products").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.exists()){

                                                purchaseProductArrayList.clear();

                                                for(DataSnapshot model:dataSnapshot.getChildren()){

                                                    PurchaseProduct purchaseProduct=model.getValue(PurchaseProduct.class);

                                                    if(purchaseProduct!=null){
                                                        purchaseProductArrayList.add(purchaseProduct);
                                                        mdeliveryProductAdapter.notifyDataSetChanged();
                                                    }


                                                }
                                                loadingBar.dismiss();

                                            }
                                            else {
                                                loadingBar.dismiss();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }





                            }
                            else {
                                loadingBar.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loadingBar.dismiss();

            }
        });


    }

    @SuppressLint("MissingPermission")
    private void CallUserAction() {

        String s="tel:"+b_phone.getText().toString();

        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));
        startActivity(intent);

    }


    private void completeDeliveryAction() {

        rootRef.child("Purchase-Order").child(parcelId).child("status").setValue("Delivered").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    Toast.makeText(MerchantDeliveyActivity.this, "Successfully Updated Delivery Status", Toast.LENGTH_SHORT).show();
                    rootRef.child("Merchant-parcel").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                for(DataSnapshot model:dataSnapshot.getChildren()){

                                    Mparcel mparcel=model.getValue(Mparcel.class);

                                    String mParcelId=mparcel.getPercel_id();

                                    if(String.valueOf(mparcel.getOrder_no()).equals(parcelId)){

                                        rootRef.child("Merchant-parcel").child(mParcelId).child("status").setValue("Delivered");




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
        });

    }

    }

