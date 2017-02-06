#include <jni.h>
#include <string>



static long fib(jlong n){
    return  n <= 0?0:n==1?1:fib(n-1)+fib(n-2);
}
extern "C"
JNIEXPORT jlong JNICALL
Java_com_verizon_ndkapp_FibLib_fibNativeRecursive(JNIEnv *env, jclass type, jlong n) {

    // TODO
    return  fib(n);
}

