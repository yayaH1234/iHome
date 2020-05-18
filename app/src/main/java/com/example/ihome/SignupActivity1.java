package com.example.ihome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class SignupActivity1 extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText password;
    private EditText password1;
    private Button Next;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        FirstName= (EditText) findViewById(R.id.firstname);
        LastName= (EditText) findViewById(R.id.lastname);
        password = (EditText) findViewById(R.id.passwordId1);
        password1 = (EditText) findViewById(R.id.passwordId2);
        Email = (EditText) findViewById(R.id.email);
        Next = (Button) findViewById(R.id.next);



        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String firstNm=FirstName.getText().toString();
                final   String lastNm=LastName.getText().toString();
                final  String email=Email.getText().toString();
                final  String pwd1=password.getText().toString();
                final   String pwd2=password1.getText().toString();


                if(firstNm.equals("") || lastNm.equals("") || email.equals("") || pwd1.equals("") || pwd2.equals("")){
                    Toast.makeText(SignupActivity1.this,"Empty fields  ",Toast.LENGTH_LONG).show();

                }
                if(!pwd1.equals(pwd2)){

                    Toast.makeText(SignupActivity1.this,"Password invalid  ",Toast.LENGTH_LONG).show();

                }
                if(!firstNm.equals("") && !lastNm.equals("") && !email.equals("") && !pwd1.equals("") && !pwd2.equals("")
                        && pwd1.equals(pwd2)){
                    Toast.makeText(SignupActivity1.this,"First step successfully completed  ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), SignupActivity2.class);

                    /*intent.putExtra("First_name", firstNm);
                    intent.putExtra("Last_name", lastNm);
                    intent.putExtra("Password", pwd1);*/
                    intent.putExtra("Email", email);




                    String requestUrl = "http://192.168.1.108:8080/user/signup1";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Volley Result", ""+response); //the response contains the result from the server, a json string or any other object returned by your server

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> postMap = new HashMap<>();
                            postMap.put("nom", firstNm);
                            postMap.put("prenom", lastNm);
                            postMap.put("email",email);
                            postMap.put("pwd", pwd1);



                            //..... Add as many key value pairs in the map as necessary for your request
                            return postMap;
                        }
                    };
                    //make the request to your server as indicated in your request url
                    Volley.newRequestQueue(SignupActivity1.this).add(stringRequest);

//getActivity().getApplicationContext()





                    startActivity(intent);
                    finish();
                }

            }
        });
    }


}

