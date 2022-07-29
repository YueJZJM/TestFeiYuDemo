package com.example.testfeiyudemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testfeiyudemo.audio.AudioActivity;
import com.example.testfeiyudemo.bluetooth.BluetoothActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_bluetooth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent((MainActivity.this), BluetoothActivity.class));
            }
        });

        findViewById(R.id.bt_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent((MainActivity.this), AudioActivity.class));
            }
        });
    }
}