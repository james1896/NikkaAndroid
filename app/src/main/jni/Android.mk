LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := hello
LOCAL_SRC_FILES := HelloJni.c

include $(BUILD_SHARED_LIBRARY)

APP_PLATFORM:=android-14

