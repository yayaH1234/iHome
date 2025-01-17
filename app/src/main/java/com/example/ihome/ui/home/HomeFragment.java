package com.example.ihome.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.ihome.R;
import com.example.ihome.addMaisonActivity2;
import com.example.ihome.mapForRentActivity;
import com.example.ihome.mapForSellActivity;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment {



    private String TAG="HomeFragment";



    private HomeViewModel homeViewModel;


    private Button ForSell ;
    private Button rent;

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



        ForSell = (Button) root.findViewById(R.id.ForSell);
        rent = (Button) root.findViewById(R.id.Rent);

       ForSell.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), mapForSellActivity.class);

               startActivity(intent);
           }
       });
       rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mapForRentActivity.class);

                startActivity(intent);
            }
        });

        return root;
    }





}