package com.example.dukar;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

public class MainActivity5 extends AppCompatActivity {
    View view;
    CheckBox cb1,cb2,cb3,cb4,cb5;
    EditText kar_no;
    ImageView karekod;
    String k_durum;
    Double longi,latit;
    Button sorgula,guncelle;
    TextView adi,adress,he_kod;
    static String isim,adres,hes,per_sube;
    dataShow show;
    QrCode qrCode;
    insert ekle;
    updateData update;
    private CodeScanner mCodeScanner;
    ActivityResultLauncher<String> izinAl;
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        adress=findViewById(R.id.textView15);
        kar_no=findViewById(R.id.edtKnum);
        adi=findViewById(R.id.textView12);
        he_kod=findViewById(R.id.textView13);
        sorgula=findViewById(R.id.button5);
        karekod=findViewById(R.id.imageView);
        show=new dataShow();
        qrCode=new QrCode();
        ekle =new insert();
        guncelle=findViewById(R.id.button6);
        cb1=findViewById(R.id.checkBox);
        cb2=findViewById(R.id.checkBox2);
        cb3=findViewById(R.id.checkBox3);
        cb4=findViewById(R.id.checkBox4);
        cb5=findViewById(R.id.checkBox5);
        update=new updateData();

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        //he_kod.setText(show.show_location(MainActivity5.this,));

        sorgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adress.setText(" Adres :"+adres);
                he_kod.setText("   HES Kodu :"+hes);
                adi.setText("   İsim :"+isim);

                karekod.setImageBitmap(qrCode.QrGenerator(hes));



            }
        });

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked()){
                    if(cb2.isChecked()||cb3.isChecked()||cb4.isChecked()||cb5.isChecked()){
                        Toast.makeText(MainActivity5.this, "lütfen sadece bir durum belirtiniz", Toast.LENGTH_LONG).show();
                    }else{
                        k_durum=cb1.getText().toString();
                        ekle.ekle(MainActivity5.this,k_durum,per_sube,kar_no.getText().toString());
                        update.update(MainActivity5.this,kar_no.getText().toString(),longi.toString(),latit.toString());
                        Toast.makeText(MainActivity5.this, "Güncelleme işlemi Başarılı", Toast.LENGTH_LONG).show();
                    }
                   
                }else if (cb2.isChecked()){
                    if(cb1.isChecked()||cb3.isChecked()||cb4.isChecked()||cb5.isChecked()){
                        Toast.makeText(MainActivity5.this, "lütfen sadece bir durum belirtiniz", Toast.LENGTH_LONG).show();
                    }else{
                        k_durum=cb2.getText().toString();
                        ekle.ekle(MainActivity5.this,k_durum,per_sube,kar_no.getText().toString());
                        update.update(MainActivity5.this,kar_no.getText().toString(),longi.toString(),latit.toString());
                        Toast.makeText(MainActivity5.this, "Güncelleme işlemi Başarılı", Toast.LENGTH_LONG).show();
                    }
                   
                }else if (cb3.isChecked()){
                    if(cb2.isChecked()||cb1.isChecked()||cb4.isChecked()||cb5.isChecked()){
                        Toast.makeText(MainActivity5.this, "lütfen sadece bir durum belirtiniz", Toast.LENGTH_LONG).show();
                    }else{
                        k_durum=cb3.getText().toString();
                        ekle.ekle(MainActivity5.this,k_durum,per_sube,kar_no.getText().toString());
                        update.update(MainActivity5.this,kar_no.getText().toString(),longi.toString(),latit.toString());
                        Toast.makeText(MainActivity5.this, "Güncelleme işlemi Başarılı", Toast.LENGTH_LONG).show();
                    }
                    
                }else if (cb4.isChecked()){
                    if(cb2.isChecked()||cb3.isChecked()||cb1.isChecked()||cb5.isChecked()){
                        Toast.makeText(MainActivity5.this, "lütfen sadece bir durum belirtiniz", Toast.LENGTH_LONG).show();
                    }else{
                        k_durum=cb4.getText().toString();
                        ekle.ekle(MainActivity5.this,k_durum,per_sube,kar_no.getText().toString());
                        update.update(MainActivity5.this,kar_no.getText().toString(),longi.toString(),latit.toString());
                        Toast.makeText(MainActivity5.this, "Güncelleme işlemi Başarılı", Toast.LENGTH_LONG).show();
                    }
                    
                }else if (cb5.isChecked()) {
                    if (cb2.isChecked() || cb3.isChecked() || cb1.isChecked()||cb4.isChecked()) {
                        Toast.makeText(MainActivity5.this, "lütfen sadece bir durum belirtiniz", Toast.LENGTH_LONG).show();
                    } else {
                        k_durum = cb5.getText().toString();
                        ekle.ekle(MainActivity5.this, k_durum, per_sube, kar_no.getText().toString());
                        update.update(MainActivity5.this, kar_no.getText().toString(), longi.toString(), latit.toString());
                        Toast.makeText(MainActivity5.this, "Güncelleme işlemi Başarılı", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity5.this, "Lütfen Durum Belirtiniz", Toast.LENGTH_SHORT).show();
                }

            }
        });


        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        kar_no.setText(result.getText());
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,350);
                        show.show_location(MainActivity5.this,result.getText().toString());


                    }

                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();

            }
        });




        //konum izinleri ve Konum alma alanı

        locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        izinAlımı();

        //
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            //izin istenmesi gerek
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(view,"Konum izni gereklidir",Snackbar.LENGTH_INDEFINITE).setAction("İzin Ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //izin butonu
                        izinAl.launch(Manifest.permission.ACCESS_FINE_LOCATION);
                    }
                }).show();
            }else{
                izinAl.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }else{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0,locationListener);
        }

    }




    private void izinAlımı(){
        izinAl=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    if (ContextCompat.checkSelfPermission(MainActivity5.this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0,locationListener);
                    }
                }else{
                    Toast.makeText(MainActivity5.this, "İzin Gerekli", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {

        longi=location.getLatitude();
        latit=location.getLongitude();



        }
    };



    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }


}