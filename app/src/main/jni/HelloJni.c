//加载生成的头文件
#include "com_never_nikkaandroid_base_JniHello.h"

//实现该头文件中包含的方法
JNIEXPORT jstring JNICALL Java_com_never_nikkaandroid_base_JniHello_SayHello
  (JNIEnv * env, jobject obj){
    //要执行的代码
    char *hello = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqgKQmqFeq0J6Vr+d90A0jlkkG5DkNYyShGj+IY9dV79T8q/cnziWnfYovZum6Vo7k83KN9tWWUEGI6NQgdY861tQ9WSQGdMiG7Oli94z6wYsKCvMZjPv7jeEY0pdLgDkr71g7/KrKPtXLmBz7LINDOE18pcKrjl/RTrOYtDo3PQIDAQAB";
    (*env)->NewStringUTF(env, hello);
  }

