LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#指定使用log类库
LOCAL_LDLIBS += -llog
LOCAL_MODULE    := call
LOCAL_SRC_FILES := call.c

include $(BUILD_SHARED_LIBRARY)
