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

public class MerchantPickupAdapter extends RecyclerView.Adapter<MerchantPickupAdapter.MerchantPickUpHolder> {


    private ArrayList<ParcelDm> parcelDms;
    private Context myContext;

    private DatabaseReference rootRef=FirebaseDatabase.getInstance().getReference();


    public MerchantPickupAdapter(ArrayList<ParcelDm> parcelDms, Context myContext) {
        this.parcelDms = parcelDms;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MerchantPickUpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.pickup_details_show_layout,parent,false);

        return new MerchantPickUpHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MerchantPickUpHolder holder, int position) {

        final ParcelDm parcelDm=parcelDms.get(position);

        holder.Id.setText("#"+parcelDm.getParcelId());

        DatabaseReference merchatParceRef= FirebaseDatabase.getInstance().getReference().child("Merchant-parcel").child(parcelDm.getParcelId());

        merchatParceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Mparcel mparcel=dataSnapshot.getValue(Mparcel.class);

                    if(mparcel.getStatus().equals("Pending") || mparcel.getStatus().equals("Verified")){
                        holder.complete.setText("Incomplete");
                    }
                    else{
                        holder.complete.setText("Complete");
                    }

                    rootRef.child("Merchants").child(mparcel.getMerchant_id()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){

                                Merchant merchant=dataSnapshot.getValue(Merchant.class);

                                holder.name.setText(merchant.getName());
                                holder.amount.setText(merchant.getBusinessName());

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


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(myContext,MerchantParcelActivity.class);

                intent.putExtra("parcel_id",parcelDm.getParcelId());

                myContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return parcelDms==null? 0: parcelDms.size();
    }

    public class MerchantPickUpHolder extends RecyclerView.ViewHolder{

        TextView name,Id,amount,complete;
        LinearLayout layout;

        public MerchantPickUpHolder(@NonNull View itemView) {
            super(itemView);

            Id=itemView.findViewById(R.id.pickup_id_dm);
            name=itemView.findViewById(R.id.pickup_name_dm);
            amount=itemView.findViewById(R.id.pickup_total_amount_dm);
            complete=itemView.findViewById(R.id.pickup_complete_status);
            layout=(LinearLayout) itemView.findViewById(R.id.pickup_show_item_layout);
        }
    }
}
