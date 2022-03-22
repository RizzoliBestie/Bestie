package com.example.bestie.map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.bestie.MainActivity;
import com.example.bestie.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    Activity act = null;
    Context ctx = null;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        act = (Activity) context;
        ctx = getContext();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ctx);
        fetchLastLocation();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);

        //Usa getChildFragmentManager() invece di GetActivity().getSupportFragmentManager()
        /*SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); */

        return v;
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(act, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation = location;
                    Toast.makeText(getContext(), "Posizione Attuale:\n" + currentLocation.getLatitude() + " " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapFragment.this);
                }
            }
        });
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

    List<MapLocation> locationslist = new ArrayList<MapLocation>();

    //https://developers.google.com/maps/documentation/android-sdk/marker
    //Location milan = new Location("Milano", 45.4654219, 9.1859243);
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Posizione attuale
        LatLng currLoc = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.ic_baseline_pets_24);
        MarkerOptions markerOptions = new MarkerOptions().position(currLoc).title("Posizione Attuale").snippet("La tua posizione").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));//.icon(BitmapDescriptorFactory.fromBitmap(icon));
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(currLoc));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLoc, 12));
        googleMap.addMarker(markerOptions);
        
        locationslist.add(new MapLocation("Ambulatorio Veterinario Risorgimento", "Veterinario", "Milano", 45.469503314883355, 9.20761260002182));
        locationslist.add(new MapLocation("Ambulatorio Veterinario Centro", "Veterinario", "Torino", 45.07255418517881, 7.682647026773152));
        locationslist.add(new MapLocation("Puppakioti Pets Botique per cani", "Pet Shop", "Milano", 45.47066254747872, 9.204109886367203));
        locationslist.add(new MapLocation("ArcaPlanet", "Pet Shop", "Milano", 45.50174978055938, 9.131297253435202));

        for(int mkindex = 0; mkindex < locationslist.size(); mkindex++){
            // Add a marker and move the camera
            LatLng marker = new LatLng(locationslist.get(mkindex).getLatitude(), locationslist.get(mkindex).getLongitude());

            BitmapDescriptor locationTypeIcon = null;
            switch(locationslist.get(mkindex).getType()){
                case "Veterinario":
                    locationTypeIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    break;
                case "Pet Shop":
                    locationTypeIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                    break;
                default:
                    locationTypeIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                    break;
            }
            MarkerOptions mapLocationMarkerOptions = new MarkerOptions()
                    .position(marker)
                    .title(locationslist.get(mkindex).getName()).snippet(locationslist.get(mkindex).getType() + " " + locationslist.get(mkindex).getCity())
                    .icon(locationTypeIcon);
            mMap.addMarker(mapLocationMarkerOptions);
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }
}
