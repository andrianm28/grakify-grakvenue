package com.andrianm28.grakify;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapFragment extends Fragment {
    private static final String TAG = "MapFragment";
    private Venue model;

    private String venueName;
    private String venueAddress;
    private String venueDesc;
    private String venueImage;
    private int venuePrice;
    private String venuePhone;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference venueRef = db.collection("venue");

    ArrayList<Venue> list = new ArrayList<Venue>();

    ArrayList<ParcelableGeoPoint> geoPointList = new ArrayList<ParcelableGeoPoint>();

    private Map<Marker, Integer> markersOrderNumbers = new HashMap<>();
    private int markerIndex = 0;

    private LatLng geoCity = new LatLng(-5.3995857,105.2642129);

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            public void onMapReady(final GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                Log.d(TAG, "MAP READY");

                mMap.clear();

                CameraPosition mapPosition = CameraPosition.builder()
                        .target(geoCity)
                        .zoom(12)
                        .bearing(0)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(mapPosition), 2000, null);

                venueRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "MAPQWE" + document.getId() + " => " + document.getData());
                                GeoPoint geoPoint = new GeoPoint( ((GeoPoint) document.get("venue_geo")).getLatitude(),((GeoPoint) document.get("venue_geo")).getLongitude() );
                                list.add(new Venue(
                                        ((Long)document.get("id")).intValue(),
                                        document.get("venue_name").toString(),
                                        document.get("venue_desc").toString(),
                                        document.get("venue_address").toString(),
                                        document.get("venue_image").toString(),
                                        ((Long)document.get("venue_price")).intValue(),
                                        document.get("venue_phone").toString(),
                                        geoPoint
                                ));
                                geoPointList.add(
                                        new ParcelableGeoPoint(geoPoint)
                                );
                                Log.d(TAG, "LATLONG" + new LatLng(((GeoPoint) document.get("venue_geo")).getLatitude(),((GeoPoint) document.get("venue_geo")).getLongitude()));
                                Log.d(TAG, "geopointt:"+geoPoint);
                                Marker marker = mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(((GeoPoint) document.get("venue_geo")).getLatitude(),((GeoPoint) document.get("venue_geo")).getLongitude()))
                                        .title(document.get("venue_name").toString())
                                        .snippet(document.get("venue_address").toString())
                                );
                                markersOrderNumbers.put(marker, markerIndex);
                                markerIndex++;
                            }

                            Log.d(TAG, "JUMLAH LIST " + list.size());
                        }
                    }
                });

             mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                 @Override
                 public void onInfoWindowClick(Marker marker) {
                     Integer index = markersOrderNumbers.get(marker);
                     Intent intent = new Intent(getActivity(),VenueActivity.class);
                     model = list.get(index);
                     ParcelableGeoPoint getgeo = geoPointList.get(index);
                     Log.d(TAG, "KLIKMARKER: " + index);
                     Log.d(TAG, "venue name:"+model.getVenue_name());
                     Log.d(TAG, "venueprice : "+model.getVenue_price());
                     Log.d(TAG, "parcelablegetgeo : "+getgeo);


                     intent.putExtra("venue_name",model.getVenue_name());
                     intent.putExtra("venue_address",model.getVenue_address());
                     intent.putExtra("venue_desc",model.getVenue_desc());
                     intent.putExtra("venue_image",model.getVenue_image());
                     intent.putExtra("venue_price",model.getVenue_price());
                     intent.putExtra("venue_phone",model.getVenue_phone());
                     intent.putExtra("venue_geo_lt",-90);
                     intent.putExtra("venue_geo_lg",90);
                     intent.putExtra("geo_point",geoPointList);
                     Log.d(TAG, "geopointlist: "+geoPointList);
                     intent.putExtra("index",index);

                     String venueName = model.getVenue_name();
                     String venueAddress = model.getVenue_address();
                     String venueDesc = model.getVenue_desc();
                     String venueImage = model.getVenue_image();
                     int venuePrice = model.getVenue_price();
                     String venuePhone = model.getVenue_phone();
                     Log.d(TAG, "geolat: "+model.getVenue_geo());
//                     Double venueLt = model.getVenue_geo().getLatitude();
//                     Double venueLg = model.getVenue_geo().getLongitude();


//                     venue.setName(venueName);
//                     venue.setAddress(venueAddress);
//                     venue.setDesc(venueDesc);
//                     venue.setImage(venueImage);
//                     venue.setPrice(venuePrice);
//                     venue.setPhone(venuePhone);
//                     venue.setGeo_lt(venueLt);
//                     venue.setGeo_lg(venueLg);
//                     intent.putExtra("Venue",venue);
                     startActivity(intent);
                 }
             });

            }
        });
        return rootView;
    }
}