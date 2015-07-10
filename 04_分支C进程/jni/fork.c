#include <jni.h>

#include <android/log.h>

#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT void JNICALL Java_cn_itcast_fork_MainActivity_callC
  (JNIEnv * env, jobject thiz){
	//分支C进程
	//返回值是分支的子进程的进程id
	//子进程执行fork，不会再分支子进程了，返回值是0
	int pid = fork();
	if(pid < 0){
		LOGI("分支失败");
	}
	//如果=0成立，说明当前代码执行在子进程
	else if(pid == 0){
		LOGI("pid为0");
		while(1){
			LOGI("耘赫败人品，不得好翔");
			sleep(1);
		}
	}
	//如果>0成立，说明当前代码执行在主进程
	else if(pid > 0){
		LOGI("pid的值为%d", pid);
	}

}
