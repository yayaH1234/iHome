package com.example.ihome;






import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity2 extends AppCompatActivity {
    ImageView image;
    String Email;
    Button choose, upload;
    int PICK_IMAGE_REQUEST = 111;
    String URL ="http://192.168.1.108:8080/user/signup2";
    Bitmap bitmap;
    ProgressDialog progressDialog;

    private static int RESULT_LOAD_IMAGE = 1;
    final int IMG_REQUEST = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        image = (ImageView)findViewById(R.id.image);
        choose = (Button)findViewById(R.id.choose_image_btn);
        upload = (Button)findViewById(R.id.next);



        Bundle extras = getIntent().getExtras();
        Email = extras.getString("Email");



        //opening image chooser option
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Signup(Email);
            }
        });
    }


    public void Signup(final String Email){



        String URL ="http://192.168.1.108:8080/user/signup2";


        StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                URL , new Response.Listener <String> () {


            @Override
            public void onResponse(String response) {
                Log.i("tagconvertstr", "["+response+"]");

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");


                    if (!error) {
                        progressDialog.dismiss();



                        image.setImageResource(0);
                        Toast.makeText ( SignupActivity2.this,"Signup Successfull",Toast.LENGTH_SHORT ).show ();
                        Intent intent=new Intent(SignupActivity2.this,LoginActivity.class);
                        startActivity(intent);
                        finish();




                    } else {
                        // Error in login. Get the error message
                        progressDialog.hide();

                        Toast.makeText ( SignupActivity2.this,"Faild to Signup",Toast.LENGTH_SHORT ).show ();

                    }
                } catch (JSONException e) {
                    progressDialog.hide();
                    // JSON error
                    e.printStackTrace();


                    Toast.makeText (SignupActivity2.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                }



            }
        } , new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                //Log.e(TAG, "Login Error: " + error.getMessage());

                Toast.makeText (SignupActivity2.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


            }
        }) {

            @Override
            protected Map<String, String> getParams() {


                // Posting parameters to login url


                Map<String, String> params = new HashMap<>();

                params.put("email", Email);
                params.put("image", imageToString(bitmap));




                return params;


            }

        };

        // Adding request to request queue
        Singleton.getInstance( getApplicationContext() ).addToRequestQueue(stringRequest);




    }








    private String imageToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);





        byte[] imageBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

    //        ImageView imageView = (ImageView) findViewById(R.id.imgView);
            image.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

}



























/*
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class SignupActivity2 extends AppCompatActivity {


    private EditText NumTel;
  //  private Button previeus;
    private Button Next;
    private String FirstNm ;
    private String LasstNm ;
    private String Email;
    private String Password ;

    ImageView mImageView;
    Button mChooseBtn;

    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;


    private String image=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        NumTel = (EditText) findViewById(R.id.telephone);
     //   previeus = (Button) findViewById(R.id.previeus);

        Next = (Button) findViewById(R.id.next);

        mImageView=findViewById(R.id.image_view);
        mChooseBtn=findViewById(R.id.choose_image_btn);
        /*
        Bundle extras = getIntent().getExtras();
        FirstNm = extras.getString("First_name");
        LasstNm = extras.getString("Last_name");
        Email = extras.getString("Email");
        Password = extras.getString("Password");*/



/*

        System.out.println("-------------->"+FirstNm+" "+ LasstNm +" "+ Email +" "+Password);


        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //if permission not granted
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions,PERMISSION_CODE);

                    }else{
                        //premission already granted
                        pickImageFromGallery();


                    }
                }else{
                    //system os is less then marshmallo
                    pickImageFromGallery();

                }
            }
        });



        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignupActivity3.class);
                final String numero=NumTel.getText().toString();
                BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
                final Bitmap bitmap = drawable.getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                 byte[] bytes = stream.toByteArray();

/*
                intent.putExtra("First_name", FirstNm);
                intent.putExtra("Last_name", LasstNm);
                intent.putExtra("Password", Password);
                //intent.putExtra("bitmap", bitmap);
                intent.putExtra("bitmap",bytes);
                intent.putExtra("numte", numero);*/
/*

                intent.putExtra("Email", Email);

                SendDAta(image,numero,Email);

/*
                String requestUrl = "http://192.168.1.111:8080/user/signup1";
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
                        postMap.put("email", Email);
                        postMap.put("num", numero);
                        postMap.put("img",bitmap);



                        //..... Add as many key value pairs in the map as necessary for your request
                        return postMap;
                    }
                };
                //make the request to your server as indicated in your request url
                Volley.newRequestQueue(SignupActivity2.this).add(stringRequest);


*/

  /*
                startActivity(intent);
                finish();
            }
        });
    }


    private void pickImageFromGallery(){
        //intent to pick image
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }
    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }else{
                    //permission was denied
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }

            }
        }

    }
    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            //set image to image view
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Bitmap lastBitmap = null;
                lastBitmap = bitmap;
                //encoding image to string
                image = getStringImage(lastBitmap);
                Log.d("image",image);
                //passing the image to volley
              //  SendImage(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
            mImageView.setImageURI(data.getData());

        }

    }

    private void SendDAta( final String image ,final String numero,final String Email) {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "URL",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("uploade",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);


                    } catch (
                    JSONException e) {
                        e.printStackTrace();
                    }

                }
    },
            new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(SignupActivity2.this, "No internet connection", Toast.LENGTH_LONG).show();

        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> params = new Hashtable<String, String>();

            params.put("image", image);
            params.put("email", Email);
            params.put("num", numero);
            return params;
        }
    };
    {
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}



}*/
