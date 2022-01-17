package com.example.dukar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity3 extends AppCompatActivity {
    Button kaydet;
    RequestQueue requestQueue;
    dataCustumer hestanim;
    EditText k_num,m_tel,m_Hes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        kaydet=findViewById(R.id.button2);
        k_num=findViewById(R.id.edt3_1);
        m_tel=findViewById(R.id.edt3_2);
        m_Hes=findViewById(R.id.edt3_3);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        hestanim=new dataCustumer();

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                hestanim.CustumerData(getApplicationContext(),k_num.getText().toString(),m_tel.getText().toString(),m_Hes.getText().toString());


            }
        });





    }
}