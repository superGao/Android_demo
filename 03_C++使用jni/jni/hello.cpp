#include <jni.h>
#include "cn_itcast_cplusplus_MainActivity.h"

JNIEXPORT jstring JNICALL Java_cn_itcast_cplusplus_MainActivity_helloFromC
  (JNIEnv * env, jobject thiz){

	char* cstr = "hahaha";
	//��c++�ַ���ת����java�ַ���
	jstring jstr = env->NewStringUTF(cstr);
	return jstr;
}
