package com.robin.dmpanel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MerchantDeliveryAdapter extends RecyclerView.Adapter<MerchantDeliveryAdapter.MerchantDeliveryHolder> {

    private ArrayList<ParcelDm> parcelDms;
    private Context myCotext;

    private DatabaseReference rootRef= FirebaseDatabase.getInstance().getReference();


    public MerchantDeliveryAdapter(ArrayList<ParcelDm> parcelDms, Context myCotext) {
        this.parcelDms = parcelDms;
        this.myCotext = myCotext;
    }

    @NonNull
    @Override
    public MerchantDeliveryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.pickup_details_show_layout,parent,false);

        return new MerchantDeliveryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MerchantDeliveryHolder holder, int position) {

        final ParcelDm parcelDm=parcelDms.get(position);

        holder.Id.setText("#"+parcelDm.getParcelId());


        rootRef.child("Purchase-Order").child(parcelDm.getParcelId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Purchase purchase=dataSnapshot.getValue(Purchase.class);

                    if(purchase!=null){
                        holder.name.setText(purchase.getBuyerName());
                        holder.amount.setText(purchase.getTotal_taka()+ " ৳");

                        if(purchase.getStatus().equals("Picked Up") || purchase.getStatus().equals("Verified") || purchase.getStatus().equals("Shipped")){

                            holder.complete.setText("Incomplete");

                        }
                        else {
                            holder.complete.setText("Complete");
                        }
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(myCotext,MerchantDeliveyActivity.class);

                intent.putExtra("parcel_id",parcelDm.getParcelId());

                myCotext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return parcelDms==null? 0: parcelDms.size();
    }

    public class MerchantDeliveryHolder extends RecyclerView.ViewHolder{
        TextView name,Id,amount,complete;
        LinearLayout layout;


        public MerchantDeliveryHolder(@NonNull View itemView) {
            super(itemView);

            Id=itemView.findViewById(R.id.pickup_id_dm);
            name=itemView.findViewById(R.id.pickup_name_dm);
            amount=itemView.findViewById(R.id.pickup_total_amount_dm);
            complete=itemView.findViewById(R.id.pickup_complete_status);
            layout=(LinearLayout) itemView.findViewById(R.id.pickup_show_item_layout);
        }
    }


}
