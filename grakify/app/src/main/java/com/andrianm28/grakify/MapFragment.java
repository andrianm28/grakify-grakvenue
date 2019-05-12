package com.andrianm28.grakify;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MapFragment extends Fragment {
    private static final String TAG = "MapFragment";
    private Venue model;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        double vlt = 0, vlg = 0;
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        System.out.println("test12"+getActivity().getIntent().getDoubleExtra("venue_geo_lt",vlt));
        if (
                Objects.requireNonNull(getActivity()).getIntent().hasExtra("venue_geo_lt")&&
                        getActivity().getIntent().hasExtra("venue_geo_lg")
        ){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            double venue_geo_lt = getActivity().getIntent().getDoubleExtra("venue_geo_lt",vlt);
            double venue_geo_lg = getActivity().getIntent().getDoubleExtra("venue_geo_lg",vlg);
            setGeo(venue_geo_lt,venue_geo_lg);
        }
    }

    public void setGeo(final double venue_geo_lt, final double venue_geo_lg){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(-5.357432, 105.314803))
                        .zoom(18)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 4000, null);
                for (int i=0; i<100; i++ ){
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(venue_geo_lt, venue_geo_lg))
                    );
                    System.out.println(venue_geo_lt);
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(-5.357432, 105.314803))
                        .zoom(18)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 4000, null);

                ArrayList<Double> listDouble = (ArrayList<Double>) getActivity().getIntent().getSerializableExtra("venue_geo_lt");
                System.out.println("seria"+listDouble);

//                for (int i=0; i<100; i++ ){
//                    mMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(venue_geo_lt, venue_geo_lg))
//                    );
//                    System.out.println(venue_geo_lt);
//                }

            }
        });
        return rootView;
    }
}