package com.example.ihome.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.ihome.MainActivity;
import com.example.ihome.R;
import com.example.ihome.addMaisonActivity2;

public class SlideshowFragment extends Fragment  {

    private SlideshowViewModel slideshowViewModel;


    private EditText Nom_mais;
    private EditText NomOwner;
    private EditText Service_type;
    private EditText adress;
    private EditText price;

    private Button Next;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of( this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
      /*  final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Nom_mais= (EditText) root.findViewById(R.id.homename);
        NomOwner= (EditText) root.findViewById(R.id.ownername);
        Service_type = (EditText) root.findViewById(R.id.service);
        adress= (EditText) root.findViewById(R.id.adresss);
        price = (EditText) root.findViewById(R.id.price);


        Next = (Button) root.findViewById(R.id.next);

        Next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String NmMs=Nom_mais.getText().toString();
                String NmOw=NomOwner.getText().toString();
                String tyServ=Service_type.getText().toString();
                String adrs=adress.getText().toString();
                String pr=price.getText().toString();
                if(NmMs.equals("") || NmOw.equals("") || tyServ.equals("") || adrs.equals("") || pr.equals("")){
                    Toast.makeText(getActivity(),"Empty fields  ",Toast.LENGTH_LONG).show();
                }
                if(!NmMs.equals("") && !NmOw.equals("") && !tyServ.equals("") && !adrs.equals("") && !pr.equals("")){
                    Toast.makeText(getActivity(),"First step successfully completed  ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), addMaisonActivity2.class);
                    intent.putExtra("Mais_name",NmMs);
                    intent.putExtra("Owner_name", NmOw);
                    intent.putExtra("Service", tyServ);
                    intent.putExtra("adress", adrs);
                    intent.putExtra("Price", pr);

                    startActivity(intent);
                }

            }
        });
        return root;
    }


}