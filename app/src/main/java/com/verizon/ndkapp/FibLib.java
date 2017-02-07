package com.verizon.ndkapp;

import android.util.Log;

/**
 * Created by ronak on 2/6/2017.
 */

public class FibLib {

    static {
        System.loadLibrary("native-lib");
    }


    public static long fibJavaIterative(long n){
        Log.d("test =" , "" + n);
        long previous = -1;
        long result = 1;
        for (long i = 0; i<= n; i++){
            long sum = result + previous;
            previous = result;
            result = sum ;
        }
        return result;

            //use recursive
        //return  n <= 0?0:n==1?1:fibJavaRecursive(n-1)+fibJavaRecursive(n-2);

    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native long fibNativeIterative(long n);


}
