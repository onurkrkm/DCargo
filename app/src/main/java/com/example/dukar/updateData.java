package com.example.dukar;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class updateData {
    public void update(Context context,String kar_id,String longi,String latit){
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String upUrl="http://kargomarac.kargomyolda.online/updateData.php";
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
                String sql="UPDATE `kargo_bilgi` SET `lat` = '"+latit+"', `longi` = '"+longi+"' WHERE `id` = "+kar_id+";";

                parameters.put("sql", sql);


                return parameters;
            }
        };
        requestQueue.add(update);
    }
    public void HesUpdate(Context context,String kar_id,String hes_kod){
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String upUrl="http://kargomarac.kargomyolda.online/updateData.php";
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
                String sql="UPDATE `kargo_bilgi` SET `heskodu` = '"+hes_kod+"' WHERE `id` = "+kar_id+";";

                parameters.put("sql", sql);


                return parameters;
            }
        };
        requestQueue.add(update);
    }
}
