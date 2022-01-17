package com.example.dukar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
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

public class dataPersonel {




    public void personelData(Activity activity, Class gec, String p_ad, String p_sif){

        RequestQueue requestQueue ;


        String showUrl = "http://kargomarac.kargomyolda.online/perBilgi.php";
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
                    JSONArray jsonArray = response.getJSONArray("showArray");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String per_ad = jsonObject.getString("per_kadi");
                        String per_sif = jsonObject.getString("per_tc");
                        String per_sehir=jsonObject.getString("per_sube");


                        if (per_ad==null){
                            Toast.makeText(activity, "Veri alınamadı", Toast.LENGTH_SHORT).show();
                        }
                        if (p_ad.toString().equals("")|| p_sif.toString().equals(""))
                        {
                            Toast.makeText(activity,"Lütfen Alanları Doldurunuz" , Toast.LENGTH_SHORT).show();
                        }
                        if(p_ad.toString().equals(per_ad))
                        {
                            if (p_ad.toString().equals(per_ad)&& p_sif.toString().equals(per_sif)) {
                                Intent intent = new Intent(activity,gec);
                                MainActivity5.per_sube=per_sehir;
                                activity.startActivity(intent);

                                Toast.makeText(activity, "Başarlı Giriş İşlemi Hoşgeldin " + per_ad, Toast.LENGTH_SHORT).show();

                                //sms.smsSending();
                            }else {
                                Toast.makeText(activity, "Şifre Yanlış", Toast.LENGTH_SHORT).show();
                            }

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
