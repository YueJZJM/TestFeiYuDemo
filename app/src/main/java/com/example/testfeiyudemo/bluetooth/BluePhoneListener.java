package com.example.testfeiyudemo.bluetooth;

import com.iflytek.autofly.bluetoothservice.aidl.FlyBluetoothCallLog;
import com.iflytek.autofly.bluetoothservice.aidl.FlyBluetoothContact;
import com.iflytek.autofly.bluetoothservice.util.IBluetoothPhoneListener;

public class BluePhoneListener implements IBluetoothPhoneListener {

    @Override
    public void onContactsNumber(int i) {

    }

    @Override
    public void onCallLogNumber(int i) {

    }

    @Override
    public void onPhoneContact(FlyBluetoothContact flyBluetoothContact, boolean b) {

    }

    @Override
    public void onPhoneCallLog(FlyBluetoothCallLog flyBluetoothCallLog, boolean b) {

    }

    @Override
    public void onCallTransferToPhone(boolean b) {

    }

    @Override
    public void onCallStatusChanged(int i, String s) {

    }

    @Override
    public void onConnectStatus(int i) {

    }

    @Override
    public void onServiceStatusChanged(int i) {

    }

    @Override
    public void onContactsNumber(int i, int i1, int i2) {

    }

    @Override
    public void onCallLogNumber(int i, int i1, int i2) {

    }

    @Override
    public void onPhoneContact(FlyBluetoothContact flyBluetoothContact, int i) {

    }

    @Override
    public void onPhoneCallLog(FlyBluetoothCallLog flyBluetoothCallLog, int i) {

    }
}
