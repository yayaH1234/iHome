package com.example.ihome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class addMaisonActivity3 extends AppCompatActivity {


    private EditText NumCo;
    private Button previeus;

    private String mais_name;
    private String owner_name;
    private String service ;
    private String adress ;
    private String Price;
    private String lat;
    private String lng;
    Bitmap bitmap;
    ImageView mImageView;
    Button mChooseBtn;
    Button post ;

    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;


    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmaison3);

        Bundle extras = getIntent().getExtras();

        mais_name =extras.getString("Mais_name");
        owner_name = extras.getString("Owner_name");
        service=  extras.getString("Service");
        adress=  extras.getString("adress");
        Price = extras.getString("Price");
        lat = extras.getString("lat");
        lng = extras.getString("lng");
        Toast.makeText(addMaisonActivity3.this,lat+" "+lng,Toast.LENGTH_LONG).show();
        NumCo = (EditText) findViewById(R.id.cordonner);
        previeus = (Button) findViewById(R.id.previeus);

        mImageView=findViewById(R.id.image_view);
        mChooseBtn=findViewById(R.id.choose_image_btn);

        post = (Button) findViewById(R.id.post);

        NumCo.setText(lat+"/"+lng);
        NumCo.setEnabled(false);
/*
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
*/

        //opening image chooser option
        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMaison(bitmap,mais_name,owner_name,service,adress,Price);
            }
        });

    }
    private void pickImageFromGallery(){
        //intent to pick image
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
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
    /*    //handle result of picked image
        String path;
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
                //set image to image view
                //     mImageView.setImageURI(data.getData());
                Uri selectedImage = data.getData();



                //    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap bitmap = BitmapFactory.decodeFile(selectedImage.toString(),options);

              /*      if(bitmap!=null)
                    {
                        bitmap.recycle();
                        bitmap=null;
                    }
               */
/*            mImageView.setImageBitmap(bitmap);
            //  "file://" +
            path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);


        }

    }
*/
    String path;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                mImageView.setImageBitmap(bitmap);
                path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
                mChooseBtn.setText(path);
            } catch (IOException e) {
                e.printStackTrace();
            }


      /*     Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

    //        ImageView imageView = (ImageView) findViewById(R.id.imgView);
            image.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }*/


        }

    }

    private void postMaison(final Bitmap imageBitmap,final String mais_name, final String owner_name , final String service , final String adress , final String Price) {

        String requestURL = "http://192.168.1.107:8080/maison/addM";



        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,requestURL ,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //      Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nom_mais", mais_name);
                params.put("nom_prop", owner_name);
                params.put("type_serv", service);
                params.put("adress", adress);
                params.put("attitude",lat );

                params.put("longiture", lng);
                params.put("prix_serv", Price);



                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("imagedp", new DataPart(imagename + ".png", getFileDataFromDrawable(imageBitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
        Toast.makeText ( addMaisonActivity3.this,"successufl",Toast.LENGTH_SHORT ).show ();
        Intent intent=new Intent(addMaisonActivity3.this,MainActivity.class);
        //    Intent intent=new Intent(addMaisonActivity3.this,SignupActivity3.class);
        startActivity(intent);


    }

}