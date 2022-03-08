package com.example.bestie.map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bestie.R;
import com.example.bestie.animal.AnimalWiki;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    Activity act = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);

        //Usa getChildFragmentManager() invece di GetActivity().getSupportFragmentManager()
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     *
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    List<Location> locationslist = new ArrayList<Location>();
    //Location milan = new Location("Milano", 45.4654219, 9.1859243);
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        locationslist.add(new Location("Milano", 45.4654219, 9.1859243));
        locationslist.add(new Location("Ambulatorio Veterinario Centro", 45.07255418517881, 7.682647026773152));

        for(int mkindex = 0; mkindex < locationslist.size(); mkindex++){
            // Add a marker and move the camera
            LatLng marker = new LatLng(locationslist.get(mkindex).getLatitude(), locationslist.get(mkindex).getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(marker)
                    .title(locationslist.get(mkindex).getType()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        }

    }
}
