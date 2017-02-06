package com.verizon.ndkapp;

/**
 * Created by ronak on 2/6/2017.
 */

public class FibLib {

    static {
        System.loadLibrary("native-lib");
    }


    public static long fibJavaRecursive(long n){
        return  n <= 0?0:n==1?1:fibJavaRecursive(n-1)+fibJavaRecursive(n-2);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native long fibNativeRecursive(long n);


}
