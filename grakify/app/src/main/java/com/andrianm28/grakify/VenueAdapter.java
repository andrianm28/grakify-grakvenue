package com.andrianm28.grakify;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.auth.User;

import java.text.NumberFormat;
import java.util.Locale;

public class VenueAdapter extends FirestoreRecyclerAdapter<Venue, VenueAdapter.VenueHolder> {

    private static final String TAG = "VenueAdapter";
    private Context mContext;


    VenueAdapter(Context context,@NonNull FirestoreRecyclerOptions<Venue> options) {
        super(options);
        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VenueHolder holder, int position, @NonNull final Venue model) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.tvVenue_name.setText(model.getVenue_name());
        holder.tvVenue_address.setText(model.getVenue_address());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvVenue_price.setText(formatRupiah.format((int)model.getVenue_price()));
        Glide.with(mContext)
                .asBitmap()
                .load(model.getVenue_image())
                .into(holder.ivVenue_image);

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: "+model.getVenue_name());
                Toast.makeText(mContext,model.getVenue_name(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext,VenueActivity.class);
                intent.putExtra("venue_name",model.getVenue_name());
                intent.putExtra("venue_address",model.getVenue_address());
                intent.putExtra("venue_summary",model.getVenue_summary());
                intent.putExtra("venue_image",model.getVenue_image());
                System.out.println(model.getVenue_price());
                intent.putExtra("venue_price",model.getVenue_price());
                mContext.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public VenueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item,parent,false);
        return new VenueHolder(v);
    }

    class VenueHolder extends RecyclerView.ViewHolder{
        TextView tvVenue_name;
        TextView tvVenue_address;
        TextView tvVenue_price;
        ImageView ivVenue_image;
        RelativeLayout parentLayout;

        VenueHolder(View itemView){
            super(itemView);
            tvVenue_name= itemView.findViewById(R.id.venue_name);
            tvVenue_address= itemView.findViewById(R.id.venue_address);
            tvVenue_price= itemView.findViewById(R.id.venue_price);
            ivVenue_image= itemView.findViewById(R.id.venue_image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
