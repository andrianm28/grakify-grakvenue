package com.andrianm28.grakify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CardFragment extends Fragment {
    private static final String TAG = "CardFragment";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference venueRef = db.collection("venues");

    private VenueAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = view.findViewById(R.id.recycler_container);
        setUpRecyclerView();
        return view;
    }

    public void setUpRecyclerView(){
        Query query = venueRef.orderBy("venue_name", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Venue> options = new FirestoreRecyclerOptions.Builder<Venue>()
                .setQuery(query,Venue.class)
                .build();
        adapter = new VenueAdapter(getContext(),options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}