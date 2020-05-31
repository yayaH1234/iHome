package com.example.ihome.ui.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.ihome.R;
import com.example.ihome.models.MaisonModel;

import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    CustomAdapterRec adapter ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
   /*     galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
     /*   final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


                galleryViewModel=
                        ViewModelProviders.of(this).get(GalleryViewModel.class);
                View root = inflater.inflate(R.layout.fragment_gallery, container, false);
                final ListView listView = root.findViewById(R.id.list
                );
                final Button btn = root.findViewById(R.id.bbtn);
           //     final String com = SharedPref.readSharedSetting(getActivity(),"cne","empty");

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            galleryViewModel.getMaisonList().observe(getViewLifecycleOwner(), new Observer<List<MaisonModel>>() {
                                @Override
                                public void onChanged(List<MaisonModel> maisonModels) {
                                    adapter = new CustomAdapterRec(maisonModels);


                                    listView.setAdapter(adapter);

                                }


                            });

                    }
                });
     /* homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

                return root;
            }
            private class CustomAdapterRec extends BaseAdapter {
                private List<MaisonModel> arrayreclamation;
                public CustomAdapterRec(List<MaisonModel> rec){
                    this.arrayreclamation =rec;
                }
                @Override
                public int getCount() {
                    return arrayreclamation.size();
                }

                @Override
                public Object getItem(int position) {
                    return arrayreclamation.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.row, parent, false);
                    }


                    TextView txtid = (TextView) convertView.findViewById(R.id.id);
                    TextView txtNom_mais = (TextView) convertView.findViewById(R.id.nom_mais);
                    TextView txtNom_pros = (TextView) convertView.findViewById(R.id.nom_prop);
                    TextView txtNom_loc = (TextView) convertView.findViewById(R.id.nom_loc);
                    TextView txtadress = (TextView) convertView.findViewById(R.id.adress);
                    TextView txtType_serv = (TextView) convertView.findViewById(R.id.type_serv);
                    TextView txtprix_serv = (TextView) convertView.findViewById(R.id.prix_serv);
                    TextView txtlat = (TextView) convertView.findViewById(R.id.attitude);
                    TextView txtlng = (TextView) convertView.findViewById(R.id.longitude);
                    ImageView imageView =(ImageView)convertView.findViewById(R.id.image_);
                    final MaisonModel maison = (MaisonModel) this.getItem(position);

                    final String id = maison.getId();
                    final String Nom_mais = maison.getNom_mais();
                    final String Nom_prop = maison.getNom_prop();
                    final String Nom_loc = maison.getNom_loc();
                    final String adress = maison.getAdress();
                    final String type_serv = maison.getType_serv();
                    final String prix_serv = maison.getPrix_serv();
                    final String attitude = maison.getAttitude();
                    final String longitude = maison.getLongitude();
                    final String imagedp = maison.getImagedp();
                    byte[] decodedString = Base64.decode(imagedp, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    txtid.setText(id);
                    txtNom_mais.setText(Nom_mais);
                    txtNom_pros.setText(Nom_prop);
                    txtNom_loc.setText(Nom_loc);
                    txtadress.setText(adress);
                    txtType_serv.setText(type_serv);
                    txtprix_serv.setText(prix_serv);
                    txtlat.setText(attitude);
                    txtlng.setText(longitude);
imageView.setImageBitmap(decodedByte);

                    txtid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity().getApplicationContext(),"kkkk",Toast.LENGTH_LONG).show();
                        }
                    });
                    return  convertView;
                }
            }
        }

