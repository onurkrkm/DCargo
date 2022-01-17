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

public class dataShow {

    public void show_location(Activity activity,String k_id){

        RequestQueue requestQueue ;
        smsInformation sms=new smsInformation();
        String[] adres = new String[1];


        String showUrl = "http://kargomarac.kargomyolda.online/dKarP.php";
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
                        String m_id= jsonObject.getString("id");
                        String m_ad = jsonObject.getString("alc_ad");
                        String m_adres = jsonObject.getString("alc_adres");
                        String m_tlefon =jsonObject.getString("alc_tel");
                        String m_hes=jsonObject.getString("heskodu");

                        if(m_hes.toString().equals("")){
                            String mesaj=m_id+" nolu kargonuzun yola çıkabilmesi için heskodu tanımlaması yapmanız lazım... iyi günler dileriz "+m_ad;
                            sms.smsSending(m_tlefon,mesaj);
                        }


                        if (m_ad==null){
                            Toast.makeText(activity, "Veri alınamadı", Toast.LENGTH_SHORT).show();
                        }
                        if (k_id.toString().equals(""))
                        {
                            Toast.makeText(activity,"Lütfen Alanları Doldurunuz" , Toast.LENGTH_SHORT).show();
                        }
                        if(k_id.toString().equals(m_id))
                        {
                            MainActivity5.isim=m_ad;
                            MainActivity5.adres=m_adres;
                            MainActivity5.hes=m_hes;
                            adres[0] =m_adres;
                            /*Intent intent = new Intent(activity,goster);

                            activity.startActivity(intent);*/

                            Toast.makeText(activity, "İşlem Başarılı Lütfen Göster Butonuna Basınız..."+m_ad, Toast.LENGTH_SHORT).show();

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
        System.out.println(adres[0]);

    }


}
