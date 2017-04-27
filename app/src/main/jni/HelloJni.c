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
    //得到当前秒数
    time_t t;
    time(&t);
    int t2 = t%100;
    int t3 = t%1000/100;
    int i = t2%10;
    //
    jint result_i = num;
    char buf[64]; // assumed large enough to cope with result

    //得到 每三位一组
    jint first  = num/1000/1000;
    jint second = num%1000000/1000;
    jint third  = num%1000;

    //拆分成一位 一共九位
    jint i1 = first/100;
    jint j1 = first%100/10;
    jint k1 = first%10;

    jint i2 = second/100;
    jint j2 = second%100/10;
    jint k2 = second%10;

    jint i3 = third/100;
    jint j3 = third%100/10;
    jint k3 = third%10;

    //
    char firstBuf[6];
    char secondBuf[6];
    char thirdBuf[6];
    char tmpBuf[6];

    //sort

    if(i>5){
        sprintf(firstBuf,"%d%d%d",k1,i1,j1);
        sprintf(secondBuf,"%d%d%d",i2,k2,j2);
        sprintf(thirdBuf,"%d%d%d",j3,k3,i3);
    } else{
        sprintf(firstBuf,"%d",first);
        sprintf(secondBuf,"%d",second);
        sprintf(thirdBuf,"%d",third);
    }



//    sprintf(buf,"%d%d%d%d%d%d%d%d%d",i1,j1,k1,i2,j2,k2,i3,j3,k3);
    sprintf(buf,"13%s%1d%1d%s%d%s%d%1d%1d",firstBuf,rand()%10,rand()%10,secondBuf,t2,thirdBuf,t3,rand()%10,rand()%10);
    return (*env)->NewStringUTF(env, buf);

}