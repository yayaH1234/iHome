package com.example.ihome.ui.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ihome.AllUrls;
import com.example.ihome.ForgotPassActivity1;
import com.example.ihome.R;
import com.example.ihome.Singleton;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;


    private String res[]=null;


    private TextView nameuser ;
    private TextView offreForuser ;
    private TextView userpub ;
    private TextView numtel;
    private TextView passwd ;
    private TextView eml ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        ///final TextView textView = root.findViewById(R.id.text_tools);
        /*toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/



        final ImageView imageView =(ImageView) root.findViewById(R.id.imaguser);

        nameuser= (TextView) root.findViewById(R.id.userName);
        offreForuser = (TextView) root.findViewById(R.id.ofreForuser);
        userpub = (TextView) root.findViewById(R.id.userpub);
        numtel = (TextView) root.findViewById(R.id.telnum);
        passwd = (TextView) root.findViewById(R.id.passwordforuser);
        eml = (TextView) root.findViewById(R.id.mailuse);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Singleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();// Volley.newRequestQueue(this);

        final String url = AllUrls.userinformationtool+"z";

        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));





                        res=response.toString().substring(1, response.toString().length() - 1).split(",");


                        Toast.makeText(getContext(), res[0]+" "+res[1]+" "+res[2]+" "+res[3]+" "+res[4], Toast.LENGTH_SHORT).show();



                        offreForuser.setText(res[0]);
                        userpub.setText(res[1]);
                        nameuser.setText(res[5]);
                        numtel.setText(res[4]);
                        passwd.setText(res[6]);
                        eml.setText(res[3]);

                        byte[] decodedString = Base64.decode(res[2], Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        imageView.setImageBitmap(decodedByte);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(getContext(), "Error Server", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return root;
    }
}