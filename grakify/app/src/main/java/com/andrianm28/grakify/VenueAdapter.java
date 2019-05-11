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
        System.out.println("longitutde"+model.getVenue_geo().getLongitude());
        holder.tv_venue_name.setText(model.getVenue_name());
        holder.tv_venue_address.setText(model.getVenue_address());

        Glide.with(mContext)
                .load(model.getVenue_image())
                .into(holder.iv_venue_image);

        holder.lt_parent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: "+model.getVenue_name());
                Toast.makeText(mContext,model.getVenue_name(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext,VenueActivity.class);
                intent.putExtra("venue_name",model.getVenue_name());
                intent.putExtra("venue_address",model.getVenue_address());
                intent.putExtra("venue_desc",model.getVenue_desc());
                intent.putExtra("venue_image",model.getVenue_image());
                intent.putExtra("venue_price",model.getVenue_price());
                intent.putExtra("venue_phone",model.getVenue_phone());
                intent.putExtra("venue_geo_lt",model.getVenue_geo().getLatitude());
                intent.putExtra("venue_geo_lg",model.getVenue_geo().getLongitude());
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
        TextView tv_venue_name;
        TextView tv_venue_address;
        TextView tv_venue_price;
        TextView tv_venue_phone;
        ImageView iv_venue_image;
        RelativeLayout lt_parent;

        VenueHolder(View itemView){
            super(itemView);
            tv_venue_name= itemView.findViewById(R.id.venue_name);
            tv_venue_address= itemView.findViewById(R.id.venue_address);
            tv_venue_price= itemView.findViewById(R.id.venue_price);
            tv_venue_phone= itemView.findViewById(R.id.venue_phone);
            iv_venue_image= itemView.findViewById(R.id.venue_image);
            lt_parent = itemView.findViewById(R.id.parent_layout);
        }
    }
}
