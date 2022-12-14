package com.example.testfeiyudemo.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testfeiyudemo.BaseActivity;
import com.example.testfeiyudemo.MethodInfo;
import com.example.testfeiyudemo.R;
import com.example.testfeiyudemo.ReflectUtils;
import com.iflytek.autofly.bluetoothservice.util.BluetoothManager;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BluetoothActivity extends BaseActivity {

    protected BluePhoneListener bluePhoneListener = new BluePhoneListener();


//    private static final String TAG = "FeiYuOS_TEST";
    BluetoothManager bluetoothManager;
//    TextView mTextView;
//    //    private Map<String, Map<String, List<String>>> methodsMap = new IdentityHashMap<>();
//    private List<MethodInfo> methodInfos = new ArrayList<>();
//    private List<String> reflectNotMethod = new ArrayList<>();
//    private ListView mListView;
//    private BluePhoneListener bluePhoneListener = new BluePhoneListener();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bluetooth);
//        mTextView = findViewById(R.id.tv_result);
//        mListView = findViewById(R.id.lv_method);
//
//        try {
//            bluetoothManager = BluetoothManager.getInstance(this);
//            Method[] methods = bluetoothManager.getClass().getDeclaredMethods();
////            getAllPublicMethods(methods);
//            ReflectUtils.getAllPublicMethods(methods, methodInfos, reflectNotMethod);
//            mTextView.setText(methodInfos.size() + " | " + reflectNotMethod.size());
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//        List<String> methodList = new ArrayList<>();
//        for (MethodInfo info : methodInfos) {
//            methodList.add(info.method);
//        }
//        methodList.add("????????????");
//        methodList.addAll(reflectNotMethod);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                BluetoothActivity.this,                    // Context?????????
//                android.R.layout.simple_list_item_1,  // ????????????id
//                methodList);                                // ??????
//        mListView.setAdapter(adapter);
//        mListView.setOnItemClickListener(this);
//
//    }

    @Override
    protected Object getManager() throws RemoteException {
        bluetoothManager = BluetoothManager.getInstance(this);
        return bluetoothManager;
    }


//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (position < methodInfos.size()) {
//            Log.d(TAG, "????????????");
//            MethodInfo info = methodInfos.get(position);
//            String text = "????????? : " + info.method + "????????? : " + info.returnType;
//            Class clazz = bluetoothManager.getClass();
//            try {
//                Method method = clazz.getDeclaredMethod(info.method);
//                if ("int".equals(info.returnType) || "java.lang.String".equals(info.returnType) || "boolean".equals(info.returnType)) {
//                    String returnValue = String.valueOf(method.invoke(bluetoothManager));
//                    text += "\n " + "??????????????? ???" + returnValue;
//                } else if ("java.util.List".equals(info.returnType)) {
//                    List<Object> returnValue = (List<Object>) method.invoke(bluetoothManager);
//                    if (returnValue != null) {
//                        text += "\n " + "??????????????? ???" + returnValue.toString();
//                    } else {
//                        text += "\n " + "??????????????? ???null";
//                    }
//                }
//                mTextView.setText(text);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else if (position == methodInfos.size()){
//            Log.d(TAG, "????????????");
//        } else {
//            callMethodCode(position);
//
//        }
//
//    }

    @Override
    public void callMethodCode(String methodName) {
//        String methodName = reflectNotMethod.get(position -1 - methodInfos.size());

        String text = "????????? : " + methodName;
        String resultStr = null;
        switch (methodName) {
            case "getInstance":
                resultStr = bluePhoneListener.toString();
                break;
            case "registerListener":
                BluetoothManager.registerListener(this, bluePhoneListener, 3);
                break;
            case "unregisterListener":
                BluetoothManager.unregisterListener(bluePhoneListener, 3);
                break;
            case "accept":
                int result = bluetoothManager.accept("18855665566");
                int result1 = bluetoothManager.accept("18855665566", 0);
                resultStr = "accept(int) ???????????? : " + result + ",accept(int,int) ???????????? : " + result1;
                break;
            case "call":
                resultStr = String.valueOf(bluetoothManager.call("18855665566"));
                break;
            case "changeName":
                resultStr = String.valueOf(bluetoothManager.changeName("????????????????????????"));
                break;
            case "changePIN":
                resultStr = String.valueOf(bluetoothManager.changePIN("????????????PIN"));
                break;
            case "connectA2dpWithAddr":
                resultStr = String.valueOf(bluetoothManager.connectA2dpWithAddr("????????????PIN"));
                break;
            case "connectDevice":
                resultStr = String.valueOf(bluetoothManager.connectDevice("????????????PIN"));
                break;
            case "connectHfp":
                resultStr = String.valueOf(bluetoothManager.connectHfp("????????????PIN"));
                break;
            case "disconnectA2dpWithAddr":
                resultStr = String.valueOf(bluetoothManager.disconnectA2dpWithAddr("????????????PIN"));
                break;
            case "disconnectDevice":
                resultStr = String.valueOf(bluetoothManager.disconnectDevice("????????????PIN"));
                break;
            case "disconnectHfp":
                resultStr = String.valueOf(bluetoothManager.disconnectHfp("????????????PIN"));
                break;
            case "disconnectPbap":
                resultStr = String.valueOf(bluetoothManager.disconnectPbap("????????????PIN"));
                break;
            case "getContactByName":
                if (bluetoothManager.getContactByName("????????????PIN") != null) {
                    resultStr = bluetoothManager.getContactByName("????????????PIN").toString();
                } else {
                    resultStr = "null";
                }

                break;
            case "getContactByNumber":
                if (bluetoothManager.getContactByNumber("????????????PIN") != null) {
                    resultStr = bluetoothManager.getContactByNumber("????????????PIN").toString();
                } else {
                    resultStr = "null";
                }
                break;
            case "getPhoneBook":
                resultStr = String.valueOf(bluetoothManager.getPhoneBook(1));
                break;
            case "getPhoneBookRange":
                resultStr = String.valueOf(bluetoothManager.getPhoneBookRange("aa", 1, 1, 1, 1));
                break;
            case "hang":
                resultStr = String.valueOf(bluetoothManager.hang("18855665566"));
                break;
            case "launchActivity":
                resultStr = String.valueOf(bluetoothManager.launchActivity(123));
                break;
            case "reject":
                resultStr = String.valueOf(bluetoothManager.reject("18855665566"));
                break;
            case "sendDTMF":
                resultStr = String.valueOf(bluetoothManager.sendDTMF('d'));
                break;
            case "setAutoConnectEnable":
                resultStr = String.valueOf(bluetoothManager.setAutoConnectEnable(true));
                break;
            case "unPairDevice":
                resultStr = String.valueOf(bluetoothManager.unPairDevice("true"));
                break;
            default:
                resultStr += "?????????";
                break;
        }

        text += ", ????????? :" + resultStr;
        mTextView.setText(text);
    }
}