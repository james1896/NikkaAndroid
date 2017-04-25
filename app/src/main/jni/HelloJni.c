//加载生成的头文件
#include "com_never_nikkaandroid_base_JniHello.h"
#include <string.h>
#include <stdio.h>



//实现该头文件中包含的方法
JNIEXPORT jstring JNICALL Java_com_never_nikkaandroid_base_JniHello_SayHello
  (JNIEnv * env, jobject obj){
    //要执行的代码
    char *hello = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqgKQmqFeq0J6Vr+d90A0jlkkG5DkNYyShGj+IY9dV79T8q/cnziWnfYovZum6Vo7k83KN9tWWUEGI6NQgdY861tQ9WSQGdMiG7Oli94z6wYsKCvMZjPv7jeEY0pdLgDkr71g7/KrKPtXLmBz7LINDOE18pcKrjl/RTrOYtDo3PQIDAQAB";
    (*env)->NewStringUTF(env, hello);
  }

JNIEXPORT jint JNICALL Java_com_never_nikkaandroid_base_JniHello_intMethod
        (JNIEnv *env, jobject obj, jint num)
{
    return num * num;
}

JNIEXPORT jboolean JNICALL Java_com_never_nikkaandroid_base_JniHello_booleanMethod
        (JNIEnv *env, jobject obj, jboolean boolean)
{
    return !boolean;
}

//JNIEXPORT jstring JNICALL Java_com_never_nikkaandroid_base_JniHello_stringMethod
//        (JNIEnv *env, jobject obj, jstring string)
//{
//    const char* str = (*env)->GetStringUTFChars(env, string, 0);
//    char cap[128];
//    strcpy(cap, str);
//    (*env)->ReleaseStringUTFChars(env, string, 0);
//    return (*env)->NewStringUTF(env, "sss");
//}

JNIEXPORT jint JNICALL Java_com_never_nikkaandroid_base_JniHello_intArrayMethod
        (JNIEnv *env, jobject obj, jintArray array)
{
    int i, sum = 0;
    jsize len = (*env)->GetArrayLength(env, array);
    jint *body = (*env)->GetIntArrayElements(env, array, 0);

    for (i = 0; i < len; ++i)
    {
        sum += body[i];
    }
    (*env)->ReleaseIntArrayElements(env, array, body, 0);
    return sum;
}