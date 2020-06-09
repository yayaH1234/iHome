package com.example.ihome.ui.tools;

import android.content.Intent;
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
import com.example.ihome.*;
import com.example.ihome.models.MaisonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;


    private String res[]=null;


    private TextView nameuser ;
    private TextView offreForuser ;
    private TextView userpub ;
    private TextView numtel;
    private TextView passwd ;
    private TextView eml ;
    private TextView interactwithpub;

    private String password;
    private int i=0;

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
        interactwithpub = (TextView) root.findViewById(R.id.intwp);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Singleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();// Volley.newRequestQueue(this);


        final Bundle extras = getActivity().getIntent().getExtras();
        Toast.makeText(getActivity(),"Your email "+extras.getString("email"),Toast.LENGTH_LONG).show();


        final String url = AllUrls.userinformationtool+extras.getString("email");

        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jo = new JSONObject(response);
                                String  id = jo.getString("id");
                                String nom =jo.getString("nom");
                                password =jo.getString("password");
                                String prenom = jo.getString("prenom");
                                JSONObject imagedbjson = jo.getJSONObject("imagedp");
                                String imagedp=imagedbjson.getString("data");
                            byte[] decodedString = Base64.decode(imagedp, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            nameuser.setText(jo.getString("nom").toString());
                            numtel.setText(jo.getString("numeroTel"));
                            eml.setText(jo.getString("email"));
                            imageView.setImageBitmap(decodedByte);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                   //     res=response.toString().substring(1, response.toString().length() - 1).split(",");


                     //   Toast.makeText(getContext(), res[0]+" "+res[1]+" "+res[2]+" "+res[3]+" "+res[4], Toast.LENGTH_SHORT).show();
//oast.makeText(getContext(),"hi"+ jo.getString("email"), Toast.LENGTH_LONG).show();


                    //    offreForuser.setText(res[0]);
                      //  userpub.setText(res[1]);
               //         nameuser.setText(jo.getString("nom").toString());
                    //    numtel.setText(res[4]);
                     //   passwd.setText(res[6]);
                    //    eml.setText(res[3]);

                 //      byte[] decodedString = Base64.decode(res[2], Base64.DEFAULT);
                 //       byte[] decodedString = res[2].getBytes();
              //       Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                //        imageView.setImageBitmap(decodedByte);


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


        // Instantiate the RequestQueue.
        final RequestQueue queue2 = Singleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();// Volley.newRequestQueue(this);

        final String url2 = AllUrls.userinformationtool2+"z";

        // Request a string response from the provided URL.
        final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
/*
                        try {
                            JSONObject jo = new JSONObject(response);
                            String  id = jo.getString("id");
                            String nom =jo.getString("nom");
                            String prenom = jo.getString("prenom");
                            JSONObject imagedbjson = jo.getJSONObject("imagedp");
                            String imagedp=imagedbjson.getString("data");
                            byte[] decodedString = Base64.decode(imagedp, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            nameuser.setText(jo.getString("nom").toString());
                            numtel.setText(jo.getString("numeroTel"));
                            eml.setText(jo.getString("email"));
                            imageView.setImageBitmap(decodedByte);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

*/
                             res=response.toString().substring(1, response.toString().length() - 1).split(",");


                        //   Toast.makeText(getContext(), res[0]+" "+res[1]+" "+res[2]+" "+res[3]+" "+res[4], Toast.LENGTH_SHORT).show();
//oast.makeText(getContext(),"hi"+ jo.getString("email"), Toast.LENGTH_LONG).show();


                            offreForuser.setText(res[0]);
                          userpub.setText(res[1]);
                        //         nameuser.setText(jo.getString("nom").toString());
                        //    numtel.setText(res[4]);
                        //   passwd.setText(res[6]);
                        //    eml.setText(res[3]);

                        //      byte[] decodedString = Base64.decode(res[2], Base64.DEFAULT);
                        //       byte[] decodedString = res[2].getBytes();
                        //       Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        //        imageView.setImageBitmap(decodedByte);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(getContext(), "Error Server 2   ", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue2.add(stringRequest2);

        passwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i%2!=0){
                    passwd.setText(password);
                }else{passwd.setText("*******");}
            }
        });
        interactwithpub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), modOrDelActivity.class);

                intent.putExtra("email",extras.getString("email"));
                startActivity(intent);
            }
        });

        return root;
    }
}