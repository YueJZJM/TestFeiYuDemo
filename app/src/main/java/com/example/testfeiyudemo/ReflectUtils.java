package com.example.testfeiyudemo;

import android.util.Log;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectUtils {
    public static final String TAG = "FeiYuOS_ReflectUtils";
    public static void getAllPublicMethods(Method[] methods,List<MethodInfo> methodInfos,List<String> reflectNotMethod) {
        for (Method method : methods) {
            int mod = method.getModifiers();
            boolean isPublic = Modifier.isPublic(mod);
            if (isPublic) {
                Log.d(TAG, "方法名：" + method.getName());
                //获取本方法所有参数类型，存入数组
                Map<String, List<String>> methodDeclared = new HashMap<>();

                Class<?>[] getTypeParameters = method.getParameterTypes();
                if (getTypeParameters.length == 0) {
                    Log.d(TAG, "此方法无参数");
                    List<String> returnType = new ArrayList<>();
                    returnType.add(method.getReturnType().getName());
                    methodDeclared.put("ReturnType", returnType);
//                    methodsMap.put(method.getName(), methodDeclared);
                    MethodInfo info = new MethodInfo();
                    info.method = method.getName();
                    info.returnType = method.getReturnType().getName();
                    methodInfos.add(info);
                } else {
                    reflectNotMethod.add(method.getName());
                }
//                List<String> typeParameters = new ArrayList<>();
//                for (Class<?> class1 : getTypeParameters) {
//                    String parameterName = class1.getName();
//                    Log.d(TAG, "参数类型：" + parameterName);
//                    typeParameters.add(class1.getName());
//                    reflectNotMethod.add(method.getName());
//                }
//
//                methodDeclared.put("TypeParameters", typeParameters);
                Log.d(TAG, "****************************");
            }
        }
    }

}
