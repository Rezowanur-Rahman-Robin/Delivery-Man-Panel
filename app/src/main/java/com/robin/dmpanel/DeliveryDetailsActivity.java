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

public class DeliveryDetailsActivity extends AppCompatActivity {

    private String parcelId;

    private TextView pId,t_amount,r_name,r_phone,r_thana,r_add_details,order_time,pay_type,size,weight,package_Type;
    private Button CompleteButton;

    private ImageButton callButton;


    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);



        parcelId=getIntent().getExtras().get("parcel_id").toString();

        rootRef= FirebaseDatabase.getInstance().getReference();

        pId=findViewById(R.id.parcel_no_popup_dm_delivery);
        r_name=findViewById(R.id.receiver_name_order_popup_dm);
        r_phone=findViewById(R.id.receiver_phone_order_popup_dm);
        r_thana=findViewById(R.id.receiver_thana_order_popup_dm);
        r_add_details=findViewById(R.id.receiver_details_address_order_popup_dm);
        order_time=findViewById(R.id.receiver_order_time_popup_dm);
        pay_type=findViewById(R.id.receiver_payment_type_order_popup_dm);
        size=findViewById(R.id.product_size_order_popup_dm_deliver);
        weight=findViewById(R.id.product_weight_order_popup_dm_deliver);
        package_Type=findViewById(R.id.product_package_type_order_popup_dm_deliver);
        callButton=(ImageButton) findViewById(R.id.dm_call_button_delivery_details);


        CompleteButton=findViewById(R.id.complete_parcel_button_popup_dm_deliver);


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CallUserAction();
            }
        });

        CompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryCompleteAction();
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

                    pId.setText(String.valueOf(parcel.getParcelId()));
                    r_name.setText(parcel.getRecipientName());
                    r_phone.setText(parcel.getRecipientPhone());
                    r_thana.setText(parcel.getRecipientThana());
                    r_add_details.setText(parcel.getRecipientAddress());
                    order_time.setText(parcel.getDate());
                    pay_type.setText(parcel.getPaymentMethod());
                    package_Type.setText(parcel.getPackageType());

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

                    if(parcel.getStatus().equals("Delivered")){
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

        String s="tel:"+r_phone.getText().toString();

        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));
        startActivity(intent);

    }

    private void DeliveryCompleteAction() {

        rootRef.child("Parcel").child(parcelId).child("status").setValue("Delivered").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(DeliveryDetailsActivity.this, "Updated The Parcel Status", Toast.LENGTH_SHORT).show();

                CompleteButton.setVisibility(View.GONE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(DeliveryDetailsActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                Toast.makeText(DeliveryDetailsActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
