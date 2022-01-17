package com.example.dukar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    EditText k_num,sonuc;
    int s1,s2;
    static String adres;
    ShowLocation kargo_num;
    TextView tx1,tx2;
    Button giris;
    int sonuc2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        k_num=findViewById(R.id.edt_1);
        sonuc=findViewById(R.id.edt_2);
        tx1=findViewById(R.id.textView3);
        tx2=findViewById(R.id.textView4);
        giris=findViewById(R.id.button);

        kargo_num=new ShowLocation();


        Random r = new Random();
        s1=r.nextInt(10);
        s2=r.nextInt(10);
        tx1.setText(s1+"x"+s2);

        sonuc2=(s1*s2);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (sonuc2==Integer.parseInt(sonuc.getText().toString())){
                    kargo_num.showLoc(MainActivity2.this,MapsActivity.class,k_num.getText().toString());

                }else{
                    Toast.makeText(MainActivity2.this, "Lütfen İşlemi kontrol dediniz", Toast.LENGTH_SHORT).show();

                }


            }
        });





    }
}