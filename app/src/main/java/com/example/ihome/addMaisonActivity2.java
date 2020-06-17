package com.example.ihome;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.ihome.ui.slideshow.SlideshowFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class addMaisonActivity2 extends AppCompatActivity implements LocationListener, OnMapReadyCallback {
    private LocationManager lm;
    private final static int PERMS_CALL_ID = 1234;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    //widget
    private MarkerOptions Moi;
    private Button param;
    private String TAG="MainActivity";



    private Button back;

    private String mais_name;
    private String owner_name;
    private String service ;
    private String adress ;
    private String Price;
    private String lat;
    private String lng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmaison2);


        Bundle extras = getIntent().getExtras();

        mais_name =extras.getString("Mais_name");
        owner_name = extras.getString("Owner_name");
        service=  extras.getString("Service");
        adress=  extras.getString("adress");
        Price = extras.getString("Price");

        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SlideshowFragment.class);

                startActivity(intent);
                finish();
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        checkPe();


    }


    private void checkPe() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
        loadMap();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_CALL_ID) {
            checkPe();

        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (lm != null) {
            lm.removeUpdates(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void loadMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                addMaisonActivity2.this.googleMap = googleMap;
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));

                googleMap.setMyLocationEnabled(true);
                //googleMap.addMarker(new MarkerOptions().position(new LatLng(43.799345, 6.725426)));
                //location.


            }
        });

    }

    public void onProviderEnabled(String provider){

    }
    public void onProviderDisabled(String provider){

    }
    public void onStatusChanged(String provider,int status,Bundle extras){

    }
    public void onLocationChanged(final Location location){

        final double latitude=location.getLatitude();
        final double longitude=location.getLongitude();
        Log.d(TAG, "Vos coordonnées"+latitude+"/"+longitude);
        Moi = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Moi");
        LatLng googleLocation = new LatLng(location.getLatitude(), location.getLongitude());
        if(googleMap != null ){
            googleMap.clear();
            googleMap.addMarker(Moi);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));

        }
        Toast.makeText(addMaisonActivity2.this," Please click on the house  ",Toast.LENGTH_LONG).show();


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(addMaisonActivity2.this," Retape to select it  ",Toast.LENGTH_LONG).show();

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        final Marker posMais=googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("Your house"));
                        Intent intent = new Intent(getBaseContext(), addMaisonActivity3.class);
                        intent.putExtra("Mais_name",mais_name);
                        intent.putExtra("Owner_name", owner_name);
                        intent.putExtra("Service", service);
                        intent.putExtra("adress", adress);
                        intent.putExtra("Price", Price);
                        lat=location.getLatitude()+"";
                        lng=location.getLongitude()+"";
                        Toast.makeText(addMaisonActivity2.this,lat+" "+lng,Toast.LENGTH_LONG).show();
                        intent.putExtra("lat",lat);
                        intent.putExtra("lng",lng);
                        startActivity(intent);
                        finish();
                    }
                });

            }



        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_option, menu);
        return true;
    }
    // attention cette fonction pour la boutter d'application a l'avenier il change le type de map....
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

}