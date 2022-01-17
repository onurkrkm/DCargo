package com.example.dukar;

import android.telephony.SmsManager;

public class smsInformation {
    public void smsSending(String m_tel,String mesaj){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(m_tel,null,mesaj,null,null);

    }
}
