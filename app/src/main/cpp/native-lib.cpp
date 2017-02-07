#include <jni.h>
#include <string>



static long fib(jlong n){

    jlong previous = -1;
    jlong result = 1;
    for (jlong i = 0; i<= n; i++){
        jlong sum = result + previous;
        previous = result;
        result = sum ;
    }
    return result;
    //return  n <= 0?0:n==1?1:fib(n-1)+fib(n-2);
}
extern "C"
JNIEXPORT jlong JNICALL
Java_com_verizon_ndkapp_FibLib_fibNativeIterative(JNIEnv *env, jclass type, jlong n) {

    // TODO
    return  fib(n);
}

