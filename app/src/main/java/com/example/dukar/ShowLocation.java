package com.example.dukar;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowLocation {

    public void showLoc(Activity activity,Class gecis,String kargoNo){
        RequestQueue requestQueue ;



        String showUrl = "http://kargomarac.kargomyolda.online/Kkonum.php";
        requestQueue = Volley.newRequestQueue(activity.getApplicationContext());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                showUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response.toString());
                try {

                    URL url = new URL(showUrl);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    JSONArray jsonArray = response.getJSONArray("showLocation");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String kar_id= jsonObject.getString("id");
                        String kar_adı= jsonObject.getString("alc_ad");
                        String kar_lon=jsonObject.getString("longi");
                        String kar_lat=jsonObject.getString("lat");
                        String kar_tipi=jsonObject.getString("kargo_tipi");
                        String kar_adres = jsonObject.getString("alc_adres");


                        if (kar_id==null){
                            Toast.makeText(activity, "Veri alınamadı", Toast.LENGTH_SHORT).show();
                        }
                        if (kargoNo.toString().equals(""))
                        {
                            Toast.makeText(activity,"Lütfen Alanları Doldurunuz" , Toast.LENGTH_SHORT).show();
                        }
                        if(kargoNo.toString().equals(kar_id))
                        {

                            MainActivity2.adres=kar_adres;
                            MapsActivity.lon=kar_lon;
                            MapsActivity.lat=kar_lat;
                            MapsActivity.k_tipi=kar_tipi;
                            MapsActivity.ad=kar_adı;
                            MapsActivity.id=kar_id;
                            Intent intent = new Intent(activity,gecis);
                            activity.startActivity(intent);

                            /*Intent intent = new Intent(activity,goster);

                            activity.startActivity(intent);*/

                            Toast.makeText(activity, "İşlem Başarılı Hoşgeldin ", Toast.LENGTH_SHORT).show();

                            //sms.smsSending();


                        }
                    }


                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }


}
