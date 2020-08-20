package com.robin.dmpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParcelDetailsActivity extends AppCompatActivity {

    private String parcelId;
    private TextView parcelid,totalamount,sendername,senderphone,senderthana,senderdetailsAddress,odertime,paymenttype,size,weight,pickupInstructions,packageType;
    private Button CompleteButton;
    private ImageButton callButton;

    private DatabaseReference rootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_details);


        parcelId=getIntent().getExtras().get("parcel_id").toString();

        rootRef= FirebaseDatabase.getInstance().getReference();

        parcelid=findViewById(R.id.parcel_no_popup_dm);
        totalamount=findViewById(R.id.total_amount_popup_dm);
        sendername=findViewById(R.id.sender_name_order_popup_dm);
        senderphone=findViewById(R.id.sender_phone_order_popup_dm);
        senderthana=findViewById(R.id.sender_thana_order_popup_dm);
        senderdetailsAddress=findViewById(R.id.sender_details_address_order_popup_dm);
        odertime=findViewById(R.id.sender_order_time_popup_dm);
        paymenttype=findViewById(R.id.sender_payment_type_order_popup_dm);
        size=findViewById(R.id.product_size_order_popup_dm);
        weight=findViewById(R.id.product_weight_order_popup_dm);
        pickupInstructions=findViewById(R.id.product_pickup_instruction_order_popup_dm);
        packageType=findViewById(R.id.product_package_type_order_popup_dm);
        callButton=(ImageButton) findViewById(R.id.dm_call_button_parcel_details);

        CompleteButton=findViewById(R.id.complete_parcel_button_popup_dm);


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CallUserAction();
            }
        });

        CompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickUpCompleteAction();
            }
        });









    }




    @Override
    protected void onStart() {
        super.onStart();


        rootRef.child("Parcel").child(parcelId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Parcel parcel=dataSnapshot.getValue(Parcel.class);

                    parcelid.setText(String.valueOf(parcel.getParcelId()));
                    totalamount.setText(parcel.getTotalAmount());
                    sendername.setText(parcel.getSenderName());
                    senderphone.setText(parcel.getSenderPhone());
                    senderthana.setText(parcel.getPickupThana());
                    senderdetailsAddress.setText(parcel.getPickupAddress());
                    odertime.setText(parcel.getDate());
                    paymenttype.setText(parcel.getPaymentMethod());
                    pickupInstructions.setText(parcel.getPickupInstructions());
                    packageType.setText(parcel.getPackageType());

                    switch (parcel.getPackageSize()){

                        case "s1":
                            size.setText("Under 1 sq Feet");
                            break;
                        case "s2":
                            size.setText("1-2 sq Feet");
                            break;
                        case "s3":
                            size.setText("More than 2 sq Feet");
                            break;
                        default:
                            size.setText("");
                    }

                    switch (parcel.getPackageWeight()){

                        case "w1":
                            weight.setText("Under 500 gm");
                            break;
                        case "w2":
                            weight.setText("0.5-1 kg");
                            break;
                        case "w3":
                            weight.setText("1-3 kg");
                            break;
                        case "w4":
                            weight.setText("4-7 kg");
                            break;
                        case "w5":
                            weight.setText("More than 7 kg");
                            break;

                        default:
                            weight.setText("");
                    }

                    if(parcel.getStatus().equals("Picked Up")|| parcel.getStatus().equals("Delivered") || parcel.getStatus().equals("Shipped")){
                        CompleteButton.setVisibility(View.GONE);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void CallUserAction() {

        String s="tel:"+senderphone.getText().toString();

        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));
        startActivity(intent);

    }


    private void PickUpCompleteAction() {

        rootRef.child("Parcel").child(parcelId).child("status").setValue("Picked Up").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(ParcelDetailsActivity.this, "Updated The Parcel Status", Toast.LENGTH_SHORT).show();

                CompleteButton.setVisibility(View.GONE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ParcelDetailsActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                Toast.makeText(ParcelDetailsActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
