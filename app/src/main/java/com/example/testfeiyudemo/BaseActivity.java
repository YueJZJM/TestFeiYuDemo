package com.example.testfeiyudemo;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfeiyudemo.bluetooth.BluePhoneListener;
import com.example.testfeiyudemo.bluetooth.BluetoothActivity;
import com.iflytek.autofly.bluetoothservice.util.BluetoothManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "FeiYuOS_TEST";
    protected Object mManager;
    protected TextView mTextView;
    //    private Map<String, Map<String, List<String>>> methodsMap = new IdentityHashMap<>();
    protected List<MethodInfo> methodInfos = new ArrayList<>();
    protected List<String> reflectNotMethod = new ArrayList<>();
    protected ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        mTextView = findViewById(R.id.tv_result);
        mListView = findViewById(R.id.lv_method);

        try {
            mManager = getManager();
            Method[] methods = mManager.getClass().getDeclaredMethods();
//            getAllPublicMethods(methods);
            ReflectUtils.getAllPublicMethods(methods, methodInfos, reflectNotMethod);
            mTextView.setText(methodInfos.size() + " | " + reflectNotMethod.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> methodList = new ArrayList<>();
        for (MethodInfo info : methodInfos) {
            methodList.add(info.method);
        }
        methodList.add("不能反射");
        methodList.addAll(reflectNotMethod);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                    // Context上下文
                android.R.layout.simple_list_item_1,  // 子项布局id
                methodList);                                // 数据
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

    }

    protected abstract Object getManager() throws RemoteException;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < methodInfos.size()) {
            Log.d(TAG, "反射调用");
            MethodInfo info = methodInfos.get(position);
            String text = "方法名 : " + info.method + "返回值 : " + info.returnType;
            Class clazz = mManager.getClass();
            try {
                Method method = clazz.getDeclaredMethod(info.method);
                if ("int".equals(info.returnType) || "java.lang.String".equals(info.returnType) || "boolean".equals(info.returnType)) {
                    String returnValue = String.valueOf(method.invoke(mManager));
                    text += "\n " + "返回结果为 ：" + returnValue;
                } else if ("java.util.List".equals(info.returnType)) {
                    List<Object> returnValue = (List<Object>) method.invoke(mManager);
                    if (returnValue != null) {
                        text += "\n " + "返回结果为 ：" + returnValue.toString();
                    } else {
                        text += "\n " + "返回结果为 ：null";
                    }
                }
                mTextView.setText(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (position == methodInfos.size()){
            Log.d(TAG, "不能反射");
        } else {
            String methodName = reflectNotMethod.get(position -1 - methodInfos.size());
            callMethodCode(methodName);

        }

    }

    protected abstract void callMethodCode(String methodName);
}
