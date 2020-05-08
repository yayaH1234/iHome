package com.example.ihome.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.ihome.R;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment implements LocationListener, OnMapReadyCallback{

    private MapView mMapView;
    private GoogleMap googleMap;

    private String TAG="HomeFragment";

    private LocationManager lm;
    private final static int PERMS_CALL_ID = 1234;

    private HomeViewModel homeViewModel;

    private double latitude=0;
    private double longitude=0;

    private Location curent;

    private MarkerOptions  Moi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;


//                LatLng My_VIEW = new LatLng(latitude,longitude );


              // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

       /*         LatLng latLng=new LatLng(curent.getLatitude(),curent.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
        */




/*                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(curent.getLatitude(),curent.getLongitude()))      // Sets the center of the map to Mountain View
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
*/

// For zooming automatically to the location of the marker
         /*       CameraPosition cameraPosition = new CameraPosition.Builder().zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            */



            }
        });

        return root;
    }






    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();


    }
    @Override
    public void onLocationChanged(Location location) {

         latitude=location.getLatitude();
         longitude=location.getLongitude();
        Log.d(TAG, "Vos coordonnées"+latitude+"/"+longitude);
         Moi = new MarkerOptions().position(new LatLng(latitude, longitude)).title("My position");
        LatLng googleLocation = new LatLng(location.getLatitude(), location.getLongitude());
        if(googleMap != null ){
            googleMap.clear();

            googleMap.addMarker(Moi);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap mMap) {
 //       mMap=googleMap;

    }
}