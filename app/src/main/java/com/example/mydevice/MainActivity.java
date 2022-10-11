package com.example.mydevice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.Utils;
import com.github.gzuliyujiang.oaid.DeviceIdentifier;

public class MainActivity extends AppCompatActivity {

    TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_info = findViewById(R.id.tv_info);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.READ_SMS}, 100);
        PermissionUtils.permission(Manifest.permission.READ_PHONE_STATE).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                initView();
            }

            @Override
            public void onDenied() {

            }
        }).request();
    }


    void initView() {
        Log.i("TAG", "androidID:" + DeviceUtils.getAndroidID());
        Log.i("TAG", "deviceID:" + DeviceUtils.getUniqueDeviceId(false));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "getSerial:" + PhoneUtils.getSerial());
        }
        Log.i("TAG", "getDeviceId:" + PhoneUtils.getDeviceId());
        Log.i("TAG", "getImeiOrMeid:" + PhoneUtils.getImeiOrMeid(true));
        Log.i("TAG", "getIMEI:" + PhoneUtils.getIMEI());
        Log.i("TAG", "getIMSI:" + PhoneUtils.getIMSI());
        Log.i("TAG", "getMEID:" + PhoneUtils.getMEID());
        Log.i("TAG", "getPhoneType:" + PhoneUtils.getPhoneType());
        Log.i("TAG", "Serial:" + Build.SERIAL);
        String[] abi = DeviceUtils.getABIs();
        String abis = "";
        for (String s : abi) {
            abis += s + " ";
        }

        String info = "androidID:" + DeviceUtils.getAndroidID() + "\n"
                + "deviceID:" + DeviceUtils.getUniqueDeviceId(false) + "\n"
                + "Serial:" + PhoneUtils.getSerial() + "\n"
                + "DeviceId:" + PhoneUtils.getDeviceId() + "\n"
                + "ImeiOrMeid:" + PhoneUtils.getImeiOrMeid(true) + "\n"
                + "IMEI:" + PhoneUtils.getIMEI() + "\n"
                + "IMSI:" + PhoneUtils.getIMSI() + "\n"
                + "MEID:" + PhoneUtils.getMEID() + "\n"
                + "PhoneType:" + PhoneUtils.getPhoneType() + "\n"
                + "TYPE:" + Build.VERSION.SDK_INT + "\n"
                + "Mac:" + DeviceUtils.getMacAddress() + "\n"
                + "ABI:" + abis + "\n"
                + "Model:" + DeviceUtils.getModel() + "\n"
                + "SimOperator:" + PhoneUtils.getSimOperatorByMnc() + "\n"
                + "SimOperator:" + PhoneUtils.getSimOperatorName() + "\n"
                + "imei:" + DeviceIdentifier.getIMEI(this) + "\n"
                + "WidevineID:" + DeviceIdentifier.getWidevineID() + "\n"
                + "OAID:" + DeviceIdentifier.getOAID(this) + "\n"
                + "Serial:" + Build.SERIAL;
        tv_info.setText(info);
    }

    @SuppressLint("HardwareIds")
    private String getDid() {
        TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "TODO";
        }
        return TextUtils.isEmpty(tm.getSimSerialNumber()+"")?"数据weinull":tm.getSimSerialNumber()+"";
    }

    private TelephonyManager getTelephonyManager() {
        return  (TelephonyManager) Utils.getApp().getSystemService(Context.TELEPHONY_SERVICE);
    }
}