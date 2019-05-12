package com.andrianm28.grakify;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class VenueActivity extends AppCompatActivity {
    private static final String TAG = "VenueActivity";
    private Activity activity;

    private VenueAdapter adapter;

    //FAB
    private FloatingActionButton fab_main, fab_call, fab_direction;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView tv_fab_call, tv_fab_direction;
    Boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        Log.d(TAG, "onCreate: started");
        setFAB();
        getIncomingIntent();
        centerTitle();

        final ScrollView mainScrollView = findViewById(R.id.main_scrollview);
        ImageView transparentImageView = findViewById(R.id.transparent_image);

        transparentImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });

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
        fab_call = findViewById(R.id.fab_call);
        fab_direction = findViewById(R.id.fab_direction);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);
        tv_fab_call = (TextView) findViewById(R.id.tv_fab_call);
        tv_fab_direction = (TextView) findViewById(R.id.tv_fab_direction);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    tv_fab_call.setVisibility(View.INVISIBLE);
                    tv_fab_direction.setVisibility(View.INVISIBLE);
                    fab_direction.startAnimation(fab_close);
                    fab_call.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab_direction.setClickable(false);
                    fab_call.setClickable(false);
                    isOpen = false;
                } else {
                    tv_fab_call.setVisibility(View.VISIBLE);
                    tv_fab_direction.setVisibility(View.VISIBLE);
                    fab_direction.startAnimation(fab_open);
                    fab_call.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab_direction.setClickable(true);
                    fab_call.setClickable(true);
                    isOpen = true;
                }
            }
        });
        fab_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Calling", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("+6289647045539"));
                startActivity(intent);
                //TODO call dialer
            }
        });

        fab_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Get Direction", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (
                getIntent().hasExtra("venue_name")&&
                        getIntent().hasExtra("venue_address")&&
                        getIntent().hasExtra("venue_image")&&
                        getIntent().hasExtra("venue_desc")&&
                        getIntent().hasExtra("venue_price")&&
                        getIntent().hasExtra("venue_phone")&&
                        getIntent().hasExtra("venue_geo_lt")&&
                        getIntent().hasExtra("venue_geo_lg")
            ){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String venue_name = getIntent().getStringExtra("venue_name");
            String venue_address= getIntent().getStringExtra("venue_address");
            String venue_image = getIntent().getStringExtra("venue_image");
            String venue_desc = getIntent().getStringExtra("venue_desc");
            int venuePrice = 0;
            int venue_price = getIntent().getIntExtra("venue_price",venuePrice);
            String venue_phone = getIntent().getStringExtra("venue_phone");
            double vglt=0,vglg=0;
            double venue_geo_lt = (double) getIntent().getDoubleExtra("venue_geo_lt",vglt);
            double venue_geo_lg = (double) getIntent().getDoubleExtra("venue_geo_lg",vglg);

            setVenue(venue_name,venue_address,venue_desc,venue_image,venue_price,venue_phone,venue_geo_lt,venue_geo_lg);
        }
    }

    private void setVenue(
            final String venue_name,
            final String venue_address,
            String venue_desc,
            String venue_image,
            int venue_price,
            String venue_phone,
            final double venue_geo_lt,
            final double venue_geo_lg){
        Log.d(TAG, "setVenue: setting venue data to widgets.");

        TextView tv_venue_name = findViewById(R.id.venue_name);
        tv_venue_name.setText(venue_name);
        setTitle(venue_name);

        TextView tv_venue_desc = findViewById(R.id.venue_desc);
        tv_venue_desc.setText(venue_desc);

        TextView tv_venue_address= findViewById(R.id.venue_address);
        tv_venue_address.setText(venue_address);

        ImageView iv_venue_image = findViewById(R.id.venue_image);
        Glide.with(this)
                .load(venue_image)
                .into(iv_venue_image)
        ;

        Locale localeID = new Locale("in", "ID");
        TextView tv_venue_price = findViewById(R.id.venue_price);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tv_venue_price.setText(formatRupiah.format(venue_price));

        TextView tv_venue_phone = findViewById(R.id.venue_phone);
        tv_venue_phone.setText(venue_phone);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition venue_geo = CameraPosition.builder()
                        .target(new LatLng(venue_geo_lt, venue_geo_lg))
                        .zoom(18)
                        .bearing(0)
                        .build();
                System.out.println(venue_geo_lt);
                System.out.println(venue_geo_lg);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(venue_geo), 2000, null);

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(venue_geo_lt, venue_geo_lg))
                        .title(venue_name)
                        .snippet(venue_address)
                );
                marker.showInfoWindow();
            }
        });
    }
}
