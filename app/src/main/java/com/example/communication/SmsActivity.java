package com.example.communication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Android 短信发送器
 */
public class SmsActivity extends AppCompatActivity {
    private EditText etPhoneNum, etSmsContent;
    private List<String> permissionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        findView();
    }

    private void findView() {
        etPhoneNum = (EditText) findViewById(R.id.et_phonenum);
        etSmsContent = (EditText) findViewById(R.id.et_smscontent);

    }

    public void send(View view) {
        // 1.获取电话号码和短信内容
        String phoneNum = etPhoneNum.getText().toString().trim();
        String smsContent = etSmsContent.getText().toString().trim();
        // 2.获取发送短信的api
        SmsManager smsManager = SmsManager.getDefault();
        //3.动态申请权限
        permissionList = new ArrayList<>();
        askPermissions();//        运行时权限 (动态权限申请)
        if(!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);//list-->String
            ActivityCompat.requestPermissions(SmsActivity.this,permissions,1);
        }
        // 4.发送短信
//        smsManager.sendMultipartTextMessage(phoneNum, null, smsManager.divideMessage(smsContent), null, null);
        if(smsContent.length() <= 70) {
            smsManager.sendTextMessage(phoneNum, null, smsContent, null, null);

        }else{

            List<String> smsDivs = smsManager.divideMessage(smsContent);
            for(String sms : smsDivs) {
                smsManager.sendTextMessage(phoneNum, null, sms, null, null);


            }
        }
        Toast.makeText(SmsActivity.this, "信息已发送", Toast.LENGTH_SHORT).show();
    }

    public void askPermissions(){
        if(ContextCompat.checkSelfPermission(SmsActivity.this, Manifest.permission.SEND_SMS)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.SEND_SMS);
        }
        if(ContextCompat.checkSelfPermission(SmsActivity.this,Manifest.permission.READ_SMS)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_SMS);
        }
        if(ContextCompat.checkSelfPermission(SmsActivity.this,Manifest.permission.RECEIVE_SMS)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.RECEIVE_SMS);
        }
        //多的同样加进去
    }
}
