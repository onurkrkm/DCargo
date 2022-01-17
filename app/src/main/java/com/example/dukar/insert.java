package com.example.dukar;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class insert {
    String date;


    public void ekle(Context context,String haraket,String sehir,String kar_id){

        Date objDate = new Date(); //Sistem Tarihi
        System.out.println(objDate);

        String strDateFormat = "yyyy-MM-dd"; //Tarih Biçimlendirme
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        date=objSDF.format(objDate);

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String upUrl="http://kargomarac.kargomyolda.online/insertDb.php";
        StringRequest update = new StringRequest(Request.Method.POST,upUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters  = new HashMap<String, String>();
                String sql="INSERT INTO `kargo_hareket` (`hareket_id`, `kargo_id`, `hareket_turu`, `hareket_tarih`, `durum`) VALUES (NULL, '"+kar_id+"', '"+sehir+"', '"+date+"', '"+haraket+"');";

                parameters.put("sql", sql);


                return parameters;
            }
        };
        requestQueue.add(update);
}}
