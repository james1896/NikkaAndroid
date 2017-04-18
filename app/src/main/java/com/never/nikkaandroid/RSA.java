package com.never.nikkaandroid;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by toby on 18/04/2017.
 */

/**
 * 随机生成RSA密钥对
 *
 * @param keyLength 密钥长度，范围：512～2048
 *                  一般1024
 * @return
 */

public class RSA {
    public static final String RSA = "RSA";// 非对称加密密钥算法
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";//加密填充方式
    public static final int DEFAULT_KEY_SIZE = 1024;//秘钥默认长度
    public static final byte[] DEFAULT_SPLIT = "#PART#".getBytes();    // 当要加密的内容超过bufferSize，则采用partSplit进行分块加密
    public static final int DEFAULT_BUFFERSIZE = (DEFAULT_KEY_SIZE / 8) - 11;// 当前秘钥支持加密的最大字节数

    public static final String PRIVATEKEY_STRING = "MIICWgIBAAKBgQCqgKQmqFeq0J6Vr+d90A0jlkkG5DkNYyShGj+IY9dV79T8q/cn\n" +
            "ziWnfYovZum6Vo7k83KN9tWWUEGI6NQgdY861tQ9WSQGdMiG7Oli94z6wYsKCvMZ\n" +
            "jPv7jeEY0pdLgDkr71g7/KrKPtXLmBz7LINDOE18pcKrjl/RTrOYtDo3PQIDAQAB\n" +
            "AoGAQpP/BVVg/kN+WWcZ1ugB6W6Kz5UYriCTshDk1CZgdJ81JQElfrahv3hnGEFw\n" +
            "bSHVpJNnnQXQu2RVYte/1XjyV1u1opXhaaF1RzNbj8Lac6KYcrQIdN+reeJw3UuJ\n" +
            "fxDJJl2kl9ZCYKHG6exI/2zNrqOPIgo5Iyruh5tG7o++AmUCQQC1XcRbZjexdGHW\n" +
            "iuI2DyzkBOmI5pz52uvd4rlxBhzpHtmGFY2IHcYX/VfHI9pERrzZ8Et5jTpW9lGX\n" +
            "7p85P83vAkEA8Kpq/biw1XIrOSI9lotQZAfHkmm11/W4cpg1lRjp5z0khGFjMLcA\n" +
            "h2ds+M7WKbLIMwf0xJSfLpJwGbLmWM95kwJAZ3sduwyigESoQjIyCag089D9spfq\n" +
            "PwEzDQH8zskvG6/3avLHMz/RtARf3gbN1aEKxcq+NZdoDxAx099Zx99ozwJADv5f\n" +
            "Cg2enr2tKbMTaTSmvoSpj1qcpKa/ZgxLkk1qDSkHBGRY3KkaFFWCOYIRX6muxVci\n" +
            "jbjArhMbfs5hgXjzlwI/SHlmWV59UluoNYqH15gCHK/26p/uD3DC0vi+2NvZt32h\n" +
            "5JVOEfLUY6NM1L4gt6A+M7yKtPuirerErmUfM2gF";




//public static String aaa(){
//
//        //公钥加密
//        long start=System.currentTimeMillis();
//        byte[] encryptBytes=  RSAUtils.encryptByPublicKeyForSpilt(jsonData.getBytes(),publicKey.getEncoded());
//        long end=System.currentTimeMillis();
//        Log.e("MainActivity","公钥加密耗时 cost time---->"+(end-start));
//        String encryStr=Base64Encoder.encode(encryptBytes);
//        Log.e("MainActivity","加密后json数据 --1-->"+encryStr);
//        Log.e("MainActivity","加密后json数据长度 --1-->"+encryStr.length());
//    }

    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用公钥对字符串进行加密
     *
     * @param data 原文
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        // 得到公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PublicKey keyPublic = kf.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance(ECB_PKCS1_PADDING);
        cp.init(Cipher.ENCRYPT_MODE, keyPublic);
        return cp.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey 密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
        // 得到私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PrivateKey keyPrivate = kf.generatePrivate(keySpec);
        // 数据加密
        Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, keyPrivate);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data      待解密数据
     * @param publicKey 密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        // 得到公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PublicKey keyPublic = kf.generatePublic(keySpec);
        // 数据解密
        Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, keyPublic);
        return cipher.doFinal(data);
    }


    /**
     * 使用私钥进行解密
     */
    public static byte[] decryptByPrivateKey(byte[] encrypted, byte[] privateKey) throws Exception {
        // 得到私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PrivateKey keyPrivate = kf.generatePrivate(keySpec);

        // 解密数据
        Cipher cp = Cipher.getInstance(ECB_PKCS1_PADDING);
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        byte[] arr = cp.doFinal(encrypted);
        return arr;
    }

}
