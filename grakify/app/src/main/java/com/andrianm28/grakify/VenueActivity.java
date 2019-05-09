package com.andrianm28.grakify;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VenueActivity extends AppCompatActivity {
    private static final String TAG = "VenueActivity";
    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
        centerTitle();

    }
    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if(textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }
    public void onAttach(Activity activity){
        this.activity = activity;
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (
                getIntent().hasExtra("venue_name")&&
                        getIntent().hasExtra("venue_address")&&
                        getIntent().hasExtra("venue_image")&&
                        getIntent().hasExtra("venue_summary")
            ){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String venue_name = getIntent().getStringExtra("venue_name");
            String venue_address= getIntent().getStringExtra("venue_address");
            String venue_image = getIntent().getStringExtra("venue_image");
            String venue_summary = getIntent().getStringExtra("venue_summary");
            setVenue(venue_name,venue_address,venue_summary,venue_image);
        }
    }
    private void setVenue(String venue_name,String venue_address,String venue_summary, String venue_image){
        Log.d(TAG, "setVenue: setting venue data to widgets.");
        TextView tvVenue_name = findViewById(R.id.venue_name);
        tvVenue_name.setText(venue_name);
        TextView tvVenue_address= findViewById(R.id.venue_address);
        tvVenue_address.setText(venue_address);
        TextView tvVenue_summary = findViewById(R.id.venue_summary);
        tvVenue_summary.setText(venue_summary);

        ImageView ivVenue_image = findViewById(R.id.venue_image);
        Glide.with(this)
                .asBitmap()
                .load(venue_image)
                .into(ivVenue_image);

        setTitle(venue_name);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
