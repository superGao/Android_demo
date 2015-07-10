LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS += -llog
LOCAL_MODULE    := fork
LOCAL_SRC_FILES := fork.c

include $(BUILD_SHARED_LIBRARY)
