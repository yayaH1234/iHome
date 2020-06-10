package com.example.ihome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private EditText repsec;
    private EditText numeT;
    private Button valid;

    private String FirstNm ;
    private String LasstNm ;
    private String Email;
    private String num;
    private String Password ;
    private String numero;
    private Bitmap bitmap;
    private String numer;
    private Spinner questSec;

    private String selected_val;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        Bundle extras = getIntent().getExtras();
   //      num = extras.getString("num");

        questSec=(Spinner)findViewById(R.id.questSec);


    //    questSec = (EditText) findViewById(R.id.questSec);


        repsec = (EditText) findViewById(R.id.replyquest);
        valid = (Button) findViewById(R.id.signup);

    //    Spinner spinner=findViewById(R.id.qstsec);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.lit,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questSec.setAdapter(adapter);
        questSec.setOnItemSelectedListener(this);
 //       numeT = (EditText)findViewById(R.id.telephone);

     //   Bundle extras = getIntent().getExtras();
      //  FirstNm = extras.getString("First_name");
       // LasstNm = extras.getString("Last_name");
        //Email = extras.getString("Email");
        //Password = extras.getString("Password");
        Email = extras.getString("Email");
     //   bitmap = this.getIntent().getParcelableExtra("bitmap");


    //     numer=numeT.getText().toString().trim();



        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String qstsec=selected_val;
            String rpsc=repsec.getText().toString();

                /*,numer*/
        signUp (qstsec,rpsc,Email);
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void signUp(final String sqtc, final String repc,final String email) {

            //  String URL ="http://192.168.1.108:8080/user/signup3";
            String URL =AllUrls.sign3Url;
            StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                    URL , new Response.Listener <String> () {
                @Override
                public void onResponse(String response) {
                    Log.i("tagconvertstr", "["+response+"]");

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");


                        if (!error) {
                        //    progressDialog.dismiss();




                            Toast.makeText ( SignupActivity3.this,"Signup Successfull",Toast.LENGTH_SHORT ).show ();
                            Intent intent=new Intent(SignupActivity3.this,LoginActivity.class);
                            startActivity(intent);
                            finish();




                        } else {
                            // Error in login. Get the error message
                       //     progressDialog.hide();

                            Toast.makeText ( SignupActivity3.this,"Faild to Signup",Toast.LENGTH_SHORT ).show ();

                        }
                    } catch (JSONException e) {
                        //progressDialog.hide();
                        // JSON error
                        e.printStackTrace();


                        Toast.makeText (SignupActivity3.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                    }



                }
            } , new Response.ErrorListener () {
                @Override
                public void onErrorResponse(VolleyError error) {
                 //   progressDialog.hide();
                    //Log.e(TAG, "Login Error: " + error.getMessage());

                    Toast.makeText (SignupActivity3.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


                }
            }) {

                @Override
                protected Map<String, String> getParams() {


                    // Posting parameters to login url


                    Map<String, String> params = new HashMap<>();
            //        params.put("num", num);
                    params.put("repsec",repc);
                    params.put("email", email);

                    params.put("sqtsec",sqtc);





                    return params;


                }

            };

            // Adding request to request queue
            Singleton.getInstance( getApplicationContext() ).addToRequestQueue(stringRequest);





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //selected_val=questSec.getSelectedItem().toString();
        selected_val=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),selected_val,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
