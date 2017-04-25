package com.never.nikkaandroid.base;

/**
 * Created by toby on 19/04/2017.
 */

public class JniHello {
    //使用静态代码块来加载相应的so文件
    static {
        System.loadLibrary("hello");//需要加载的so文件的名称
    }

    //声明一个由 native 修饰的函数
    public native String SayHello();

    //声明四种带参数的 函数
    public native int intMethod(int n);
    public native boolean booleanMethod(boolean bool);
//    public native String stringMethod(String text);
    public native int intArrayMethod(int[] intArray);

}
