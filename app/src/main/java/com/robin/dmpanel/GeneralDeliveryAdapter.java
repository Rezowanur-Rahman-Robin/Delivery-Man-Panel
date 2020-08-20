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

public class GeneralDeliveryAdapter extends RecyclerView.Adapter<GeneralDeliveryAdapter.GeneralDeliveryHolder> {


    private ArrayList<ParcelDm> parcelDms;
    private Context myContext;

    public GeneralDeliveryAdapter(ArrayList<ParcelDm> parcelDms, Context myContext) {
        this.parcelDms = parcelDms;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public GeneralDeliveryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.pickup_details_show_layout,parent,false);

        return new GeneralDeliveryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GeneralDeliveryHolder holder, int position) {

        final ParcelDm parcelDm=parcelDms.get(position);

        holder.Id.setText("#"+parcelDm.getParcelId());

        DatabaseReference parcelRef= FirebaseDatabase.getInstance().getReference().child("Parcel").child(parcelDm.getParcelId());
        parcelRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Parcel parcel=dataSnapshot.getValue(Parcel.class);

                    holder.name.setText(parcel.getRecipientName());
                    holder.amount.setText(parcel.getRecipientPhone());


                    if(parcel.getStatus().equals("Shipped") || parcel.getStatus().equals("Verified") || parcel.getStatus().equals("Picked Up") ){

                        holder.complete.setText("Incomplete");
                    }
                    else {
                        holder.complete.setText("Complete");
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

                Intent intent=new Intent(myContext,DeliveryDetailsActivity.class);

                intent.putExtra("parcel_id",parcelDm.getParcelId());

                myContext.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return parcelDms==null? 0: parcelDms.size();
    }


    public class GeneralDeliveryHolder extends RecyclerView.ViewHolder{

        TextView name,Id,amount,complete;
        LinearLayout layout;


        public GeneralDeliveryHolder(@NonNull View itemView) {
            super(itemView);

            Id=itemView.findViewById(R.id.pickup_id_dm);
            name=itemView.findViewById(R.id.pickup_name_dm);
            amount=itemView.findViewById(R.id.pickup_total_amount_dm);
            complete=itemView.findViewById(R.id.pickup_complete_status);
            layout=(LinearLayout) itemView.findViewById(R.id.pickup_show_item_layout);
        }
    }
}
