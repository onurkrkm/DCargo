package com.example.dukar;

import android.content.Context;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

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

public class dataCustumer {

    static String hes;


    public void CustumerData(Context context, String kargo_No, String mus_tel,String heskodu){
        String showCustumer = "http://kargomarac.kargomyolda.online/upHes.php";
        updateData update=new updateData();


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                showCustumer, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    URL url = new URL(showCustumer);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    JSONArray jsonArray = response.getJSONArray("showCustumer");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String cus_Id= jsonObject.getString("id");
                        String cus_ad = jsonObject.getString("alc_ad");
                        String cus_tel = jsonObject.getString("alc_tel");

                        if (cus_ad==null){
                            Toast.makeText(context, "Veri alınamadı", Toast.LENGTH_SHORT).show();
                        }
                        if (kargo_No.toString().equals("")|| cus_tel.toString().equals(""))
                        {
                            Toast.makeText(context,"Lütfen Alanları Doldurunuz" , Toast.LENGTH_SHORT).show();
                        }
                        if(kargo_No.toString().equals(cus_Id))
                        {
                            if (kargo_No.toString().equals(cus_Id)&& mus_tel.toString().equals(cus_tel)) {
                                update.HesUpdate(context,kargo_No,heskodu);
                                Toast.makeText(context, "Başarlı Güncelleme İşlemi  " + cus_ad, Toast.LENGTH_SHORT).show();
                                hes=cus_ad.toString();

                            }else {
                                Toast.makeText(context, "Şifre Yanlış", Toast.LENGTH_SHORT).show();
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
