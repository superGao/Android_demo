#include <jni.h>
#include <android/log.h>

#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)


JNIEXPORT void JNICALL Java_cn_itcast_ccalljava_MainActivity_callC
  (JNIEnv * env, jobject thiz){

	LOGD("debug");
	LOGI("info");
	//加载字节码
	//jclass      (*FindClass)(JNIEnv*, const char*);
	jclass clazz = (*env)->FindClass(env, "cn/itcast/ccalljava/MainActivity");

	//获取方法id
	//jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
	jmethodID methodId = (*env)->GetMethodID(env, clazz, "showDialog", "(Ljava/lang/String;)V");

	//调用方法
	//void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
	(*env)->CallVoidMethod(env, thiz, methodId, (*env)->NewStringUTF(env, "you are superMan"));


}
