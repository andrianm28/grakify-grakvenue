package com.andrianm28.grakify;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class VenueActivity extends AppCompatActivity {
    private static final String TAG = "VenueActivity";
    private Activity activity;

    //FAB
    private FloatingActionButton fab_main, fab1_mail, fab2_share;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView textview_mail, textview_share;
    Boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        Log.d(TAG, "onCreate: started");
        setFAB();
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

    private void setFAB(){

        fab_main = findViewById(R.id.fab);
        fab1_mail = findViewById(R.id.fab1);
        fab2_share = findViewById(R.id.fab2);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

        textview_mail = (TextView) findViewById(R.id.tvCall);
        textview_share = (TextView) findViewById(R.id.tvDirection);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {

                    textview_mail.setVisibility(View.INVISIBLE);
                    textview_share.setVisibility(View.INVISIBLE);
                    fab2_share.startAnimation(fab_close);
                    fab1_mail.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab2_share.setClickable(false);
                    fab1_mail.setClickable(false);
                    isOpen = false;
                } else {
                    textview_mail.setVisibility(View.VISIBLE);
                    textview_share.setVisibility(View.VISIBLE);
                    fab2_share.startAnimation(fab_open);
                    fab1_mail.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab2_share.setClickable(true);
                    fab1_mail.setClickable(true);
                    isOpen = true;
                }

            }
        });


        fab2_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();

            }
        });

        fab1_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Email", Toast.LENGTH_SHORT).show();

            }
        });
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
                        getIntent().hasExtra("venue_summary")&&
                        getIntent().hasExtra("venue_price")
            ){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String venue_name = getIntent().getStringExtra("venue_name");
            String venue_address= getIntent().getStringExtra("venue_address");
            String venue_image = getIntent().getStringExtra("venue_image");
            String venue_summary = getIntent().getStringExtra("venue_summary");
            int sum = 0;
            int venue_price = getIntent().getIntExtra("venue_price",sum);
            setVenue(venue_name,venue_address,venue_summary,venue_image,venue_price);
        }
    }
    private void setVenue(String venue_name,String venue_address,String venue_summary, String venue_image,int venue_price){
        Log.d(TAG, "setVenue: setting venue data to widgets.");
        TextView tvVenue_name = findViewById(R.id.venue_name);
        tvVenue_name.setText(venue_name);
        TextView tvVenue_address= findViewById(R.id.venue_address);
        tvVenue_address.setText(venue_address);
//        TextView tvVenue_summary = findViewById(R.id.venue_summary);
//        tvVenue_summary.setText(venue_summary);
        TextView tvVenue_price = findViewById(R.id.venue_price);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvVenue_price.setText(formatRupiah.format((int)venue_price));
        ImageView ivVenue_image = findViewById(R.id.venue_image);
        Glide.with(this)
                .load(venue_image)
                .into(ivVenue_image)
        ;

        setTitle(venue_name);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
