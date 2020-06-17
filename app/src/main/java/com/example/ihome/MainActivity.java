package com.example.ihome;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ihome.ui.slideshow.SlideshowFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String res[];

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //      Toolbar toolbar = findViewById(R.id.toolbar);
/*   setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Singleton.getInstance(MainActivity.this).getRequestQueue();// Volley.newRequestQueue(this);


        Bundle extras = getIntent().getExtras();
        Toast.makeText(MainActivity.this,"Your email "+extras.getString("email"),Toast.LENGTH_LONG).show();


        final String url = AllUrls.userinformationtool+extras.getString("email");


        final String id ;
        final String name;
        final String email ;
        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            String id = jo.getString("id");

                           String  name =jo.getString("nom");
                          String   email = jo.getString("email");
                            JSONObject imagedbjson = jo.getJSONObject("imagedp");
                          String imagedp=imagedbjson.getString("data");
                            TextView eml=(TextView)findViewById(R.id.textView);
                            TextView nameuser=(TextView)findViewById(R.id.nmUser);
                            ImageView imageView =(ImageView) findViewById(R.id.imageView);

                            byte[] decodedString = Base64.decode(imagedp, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            nameuser.setText(name);
                            eml.setText(email);
        //                  Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            imageView.setImageBitmap(decodedByte);



                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


//                        res=response.toString().substring(1, response.toString().length() - 1).split(",");





                      //  TextView eml=(TextView)findViewById(R.id.textView);
                       // TextView nameuser=(TextView)findViewById(R.id.nmUser);
                     //   ImageView imageView =(ImageView) findViewById(R.id.imageView);

                      //  nameuser.setText(res[5]);

                    //    eml.setText(res[3]);

                       // byte[] decodedString = Base64.decode(res[2], Base64.DEFAULT);
                   //     Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    //    imageView.setImageBitmap(decodedByte);
                        Log.d("MainActivity","-------------------------------> information "+response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(MainActivity.this, "Error Server", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static class addMaisonActivity2 extends AppCompatActivity implements LocationListener, OnMapReadyCallback {
        private LocationManager lm;
        private final static int PERMS_CALL_ID = 1234;
        private MapFragment mapFragment;
        private GoogleMap googleMap;
        //widget
        private MarkerOptions Moi, voiture;
        private Button param;
        private String TAG="MainActivity";
        private static final float DEFAULT_ZOOM = 15f;
        private Location locat=null;



        private Button back;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_addmaison2);

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
        public void onLocationChanged(Location location){

            double latitude=location.getLatitude();
            double longitude=location.getLongitude();
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
}