LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#ָ��ʹ��log���
LOCAL_LDLIBS += -llog
LOCAL_MODULE    := call
LOCAL_SRC_FILES := call.c

include $(BUILD_SHARED_LIBRARY)
