package com.robin.dmpanel;

import android.content.Context;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MdeliveryProductAdapter extends RecyclerView.Adapter<MdeliveryProductAdapter.MdeliveryProductHolder> {

    private ArrayList<PurchaseProduct> purchaseProducts;
    private Context myContext;
    private DatabaseReference rootRef= FirebaseDatabase.getInstance().getReference();

    public MdeliveryProductAdapter(ArrayList<PurchaseProduct> purchaseProducts, Context myContext) {
        this.purchaseProducts = purchaseProducts;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MdeliveryProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from((parent.getContext())).inflate(R.layout.parcel_product_single_recylerview_layout,parent,false);

        return new MdeliveryProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MdeliveryProductHolder holder, int position) {

        final PurchaseProduct purchaseProduct=purchaseProducts.get(position);

        holder.id.setText(purchaseProduct.getProductID());

        holder.insTextView.setText("Delivery Charge:");

        DatabaseReference proRef= FirebaseDatabase.getInstance().getReference().child("Merchant-Products").child(purchaseProduct.getProductID());

        proRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    MProduct mProduct=dataSnapshot.getValue(MProduct.class);

                    if(mProduct!=null){


                        holder.name.setText(mProduct.getProduct_name());
                        holder.quantity.setText(purchaseProduct.getQuantity());
                        holder.pack_type.setText(mProduct.getProduct_type());
                        if(mProduct.getProduct_discount_price().isEmpty()){

                            holder.price.setText(mProduct.getProduct_price()+" ৳");

                        }
                        else {
                            holder.price.setText(mProduct.getProduct_discount_price()+" ৳");
                        }

                        holder.instruction.setText(purchaseProduct.getDeliveryCharge()+" ৳");


                        Picasso.with(myContext).load(mProduct.getImage_url()).into(holder.image);

                        switch (mProduct.getProduct_size()){

                            case "s1":
                                holder.size.setText("Under 1 sq Feet");
                                break;
                            case "s2":
                                holder.size.setText("1-2 sq Feet");
                                break;
                            case "s3":
                                holder.size.setText("More than 2 sq Feet");
                                break;
                            default:
                                holder.size.setText("");
                        }

                        switch (mProduct.getProduct_weight()){

                            case "w1":
                                holder.weight.setText("Under 500 gm");
                                break;
                            case "w2":
                                holder.weight.setText("0.5-1 kg");
                                break;
                            case "w3":
                                holder.weight.setText("1-3 kg");
                                break;
                            case "w4":
                                holder.weight.setText("4-7 kg");
                                break;
                            case "w5":
                                holder.weight.setText("More than 7 kg");
                                break;

                            default:
                                holder.weight.setText("");
                        }
                    }
                }

                else{
                    holder.name.setText("Unavailable");
                    holder.unavailableLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return purchaseProducts==null? 0: purchaseProducts.size();
    }

    public class MdeliveryProductHolder extends RecyclerView.ViewHolder{

        TextView name,price,id,size,weight,quantity,pack_type,instruction,insTextView;
        LinearLayout instructionLayout,unavailableLayout;
        RoundedImageView image;

        public MdeliveryProductHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_name_popupView_order_dm);
            price=itemView.findViewById(R.id.product_price_popupView_order_dm);
            id=itemView.findViewById(R.id.product_id_popupView_order_dm);
            size=itemView.findViewById(R.id.product_size_popupView_order_dm);
            weight=itemView.findViewById(R.id.product_weight_popupView_order_dm);
            quantity=itemView.findViewById(R.id.product_quantity_popupView_order_dm);
            pack_type=itemView.findViewById(R.id.product_package_type_popupView_order_dm);
            instruction=itemView.findViewById(R.id.product_instruction_popupView_order_dm);
            insTextView=itemView.findViewById(R.id.instruction_textView);
            instructionLayout=(LinearLayout) itemView.findViewById(R.id.instruction_layout);
            unavailableLayout=(LinearLayout) itemView.findViewById(R.id.unavailable_delivery_product_dm);

            image=(RoundedImageView)itemView.findViewById(R.id.product_image_popupView_order);
        }
    }
}
