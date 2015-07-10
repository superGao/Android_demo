#include <jni.h>
#include "cn_itcast_cplusplus_MainActivity.h"

JNIEXPORT jstring JNICALL Java_cn_itcast_cplusplus_MainActivity_helloFromC
  (JNIEnv * env, jobject thiz){

	char* cstr = "hahaha";
	//°Ñc++×Ö·û´®×ª»»³Éjava×Ö·û´®
	jstring jstr = env->NewStringUTF(cstr);
	return jstr;
}
