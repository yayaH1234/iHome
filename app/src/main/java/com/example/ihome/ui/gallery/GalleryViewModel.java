package com.example.ihome.ui.gallery;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.ihome.AllUrls;
import com.example.ihome.models.MaisonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GalleryViewModel extends ViewModel {
/*
    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }*/

    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    private MutableLiveData<List<MaisonModel>> MaisonList;
    LiveData<List<MaisonModel>> getMaisonList() {

        if (MaisonList == null) {

         MaisonList = new MutableLiveData<>();
            taskAsync = new GetMaison();
            taskAsync.execute();
        }
        return MaisonList;
    }


    private GetMaison taskAsync;
    private String te;
    public GalleryViewModel() {
        mText = new MutableLiveData<>();




        //     taskAsync = new GetReclamation("cne1");
        //   taskAsync.execute();
    }

    public LiveData<String> getText() {
        return mText;
    }



    private class GetMaison extends AsyncTask<Void,Void,String> {


        public GetMaison() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //    mText.setValue(s);
            new Parser(s).execute();

        }

        @Override
        protected String doInBackground(Void... voids) {
            return this.send();
        }

        public Object connect(String url) {

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(25000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(true);
                connection.setDefaultUseCaches(true);
                return connection;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error : url n'existe pas";

            } catch (IOException e) {
                e.printStackTrace();
                return "Error : erreur de connection !! ";
            }
        }


        private String send() {
            //       Object mconnect = connect.connect(AllUrls.listMaisonUrl);

            URL url = null;
            try {
                url = new URL(AllUrls.listMaisonUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.setRequestProperty("Accept", "application/json");
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    response.append(line + "\n");
                }
                br.close();
                in.close();

                te = response.toString();

    return te;



            } catch (IOException e) {
                e.printStackTrace();
            }
            return te;
        }
    }
        //G
       /*    if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
  /*          try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;
*/
            //    OutputStream os = new BufferedOutputStream(connection.getOutputStream());
           //     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

           //     String dataurl = "maison_id=" + maison_id ;

            //    bw.write(dataurl);
             //   bw.flush();
           //     bw.close();
          //      os.close();
            //    int responsecode = connection.getResponseCode();
            //    if (responsecode == connection.HTTP_OK) {

                //    InputStream is = new BufferedInputStream(connection.getInputStream());
                  //  BufferedReader br = new BufferedReader(new InputStreamReader(is));


        /*        } else {
                    return "erreurs" + String.valueOf(responsecode);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //Get response


   //         return "Check your connection !!! ";
   //     }


   // }

    private class Parser extends AsyncTask<Void, Void, Boolean> {



        String jdata;


        List<MaisonModel> RecList = new ArrayList<>();

        ProgressDialog pd;

        public Parser(String jdata) {

            this.jdata = jdata;



        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return this.parseReclamation();

        }


        private Boolean parseReclamation() {
            try {





                JSONArray ja = new JSONArray(jdata);
                JSONObject jo;

                RecList.clear();
                MaisonModel con;



                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);


                    String  id = jo.getString("id");
                    String nom_mais =jo.getString("nom_mais");
                    String nom_prop = jo.getString("nom_prop");
                    String nom_loc  = jo.getString("nom_loc");
                    String type_serv = jo.getString("type_serv");
                    String adress =jo.getString("adress");
                  //  String attitude  =jo.getString("attitude");
                  //  String longitude =jo.getString("longitude");
                    String attitude  ="fff";
                     String longitude ="fff";
                    String prix_serv =jo.getString("prix_serv");
                    JSONObject imagedbjson = jo.getJSONObject("imagedp");
                    String imagedp=imagedbjson.getString("data");


                    con = new MaisonModel();
                    con.setId(id);
                    con.setNom_mais(nom_mais);
                    con.setNom_prop(nom_prop);
                    con.setNom_loc(nom_loc);
                    con.setType_serv(type_serv);
                    con.setAdress(adress);
                    con.setAttitude(attitude);
                    con.setLongitude(longitude);
                    con.setPrix_serv(prix_serv);
                    con.setImagedp(imagedp);


                    RecList.add(con);
                }

                return true;

            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean isParsed) {
            super.onPostExecute(isParsed);



            if (isParsed) {

                MaisonList.setValue(RecList);
            } else {
                MaisonList.setValue(null);
            }


        }

    }
}