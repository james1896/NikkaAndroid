//加载生成的头文件
#include "com_never_nikkaandroid_base_JniHello.h"
#include <string.h>
#include <stdio.h>
#include<stdlib.h>
#include<time.h>



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

JNIEXPORT jstring JNICALL Java_com_never_nikkaandroid_base_JniHello_serialWithUserID
        (JNIEnv *env, jobject obj, jint num)
{

    jint result_i = num;
    char buf[64]; // assumed large enough to cope with result


    jint first  = num/1000/1000;
    jint second = num%1000000/1000;
    jint third  = num%1000;

    char firstBuf[6];
    char secondBuf[6];
    char thirdBuf[6];
    char tmpBuf[6];

    sprintf(firstBuf,"%d",first);
    sprintf(secondBuf,"%d",second);
    sprintf(thirdBuf,"%d",third);

//    tmpBuf = firstBuf;
//    firstBuf[0] = firstBuf[1];

    //得到当前秒数
    time_t t;
    time(&t);
    int t2 = t%100;
    int t3 = t%1000/100;
//    sprintf(buf, "%d|%s|%s|%s+%3d",result_i,firstBuf,secondBuf,thirdBuf,rand()%100);  // error checking omitted
//    sprintf(buf,"%s|%d|%d",timeBuf,t3,t2);
    sprintf(buf,"13%s%1d%1d%s%d%s%d%1d%1d",firstBuf,rand()%10,rand()%10,secondBuf,t2,thirdBuf,t3,rand()%10,rand()%10);
    return (*env)->NewStringUTF(env, buf);

}