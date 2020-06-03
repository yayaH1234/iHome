package com.example.ihome.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.ihome.LoginActivity;
import com.example.ihome.R;
import com.example.ihome.addMaisonActivity2;
import com.example.ihome.ui.gallery.GalleryFragment;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    Button Noop;
    Button yeep;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
     /*   final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Noop= (Button) root.findViewById(R.id.Noo);
        yeep= (Button) root.findViewById(R.id.yep);


        Noop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GalleryFragment.class);

                startActivity(intent);
            }
        });
        yeep.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);
            }
        });


        return root;
    }
}