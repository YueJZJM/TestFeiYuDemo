package com.example.testfeiyudemo.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;

import com.example.testfeiyudemo.BaseActivity;
import com.example.testfeiyudemo.R;
import com.iflytek.autofly.audioservice.util.AudioManager;
import com.iflytek.autofly.bluetoothservice.util.BluetoothManager;

public class AudioActivity extends BaseActivity {

    AudioManager mAudioManager;

    private AudioListener mListener = new AudioListener();

    @Override
    protected Object getManager() throws RemoteException {
        mAudioManager = AudioManager.getInstance(this);
        return mAudioManager;
    }

    @Override
    protected void callMethodCode(String methodName) {
        String text = "方法名 : " + methodName;
        String resultStr = null;
        switch (methodName) {
            case "getInstance":
                resultStr = mAudioManager.toString();
                break;
            case "registerListener":
                AudioManager.registerListener(this, mListener);
                break;
            case "unregisterListener":
                AudioManager.unregisterListener(mListener);
                break;
            case "SetAudioSource":
                resultStr = String.valueOf(mAudioManager.SetAudioSource(1));
                break;
            case "SetRadioFreq":
                resultStr = mAudioManager.SetRadioFreq(1,1) +"";
                break;
            case "abandonAudioFocus":
                resultStr = mAudioManager.abandonAudioFocus(new android.media.AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {

                    }
                }) + "";
                break;
            case "changeEnvironmentMode":
                resultStr = mAudioManager.changeEnvironmentMode(1) + "";
                break;
            case "changeFuncBeamForming":
                resultStr = mAudioManager.changeFuncBeamForming(1,1) + "";
                break;
            default:
                resultStr += "未命中";
                break;
        }
        text += ", 返回值 :" + resultStr;
        mTextView.setText(text);
    }
}