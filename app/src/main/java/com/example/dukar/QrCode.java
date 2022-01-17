package com.example.dukar;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrCode {
    Bitmap bitmap;
    public Bitmap QrGenerator(String hes){

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(hes, BarcodeFormat.QR_CODE,150,150);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            bitmap=barcodeEncoder.createBitmap(bitMatrix);


        }catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }
}
