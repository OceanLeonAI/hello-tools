package com.leon.digest.mac;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: MACSunTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 16:29
 * @Version 1.0
 * @DESCRIPTION: HmacMD5、HmacSHA1、HmacSHA384、HmacSHA512
 * Message Authentication Code 消息认证码算法
 **/
public class MACSunTest {

    /**
     * 初始化 HmacMD5 秘钥密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initHmacMD5Key() throws NoSuchAlgorithmException {

        // 初始化 KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");

        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获得秘钥
        byte[] encoded = secretKey.getEncoded();

        return encoded;
    }

    /**
     * HmacMD5 消息摘要
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encodeHmacMD5(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {

        // 还原秘钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");

        // 实例化 MAC
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 初始化 MAC
        mac.init(secretKey);

        // 执行消息摘要
        byte[] bytes = mac.doFinal(data);

        return bytes;
    }

    /**
     * 初始化 HmacSHA1 秘钥密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initHmacSHAKey() throws NoSuchAlgorithmException {

        // 初始化 KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");

        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获得秘钥
        byte[] encoded = secretKey.getEncoded();

        return encoded;
    }

    /**
     * HmacSHA1 消息摘要
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encodeHmacSHA(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {

        // 还原秘钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");

        // 实例化 MAC
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 初始化 MAC
        mac.init(secretKey);

        // 执行消息摘要
        byte[] bytes = mac.doFinal(data);

        return bytes;
    }

    /**
     * 初始化 HmacSHA256 秘钥密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initHmacSHA256Key() throws NoSuchAlgorithmException {

        // 初始化 KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");

        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获得秘钥
        byte[] encoded = secretKey.getEncoded();

        return encoded;
    }

    /**
     * HmacSHA256 消息摘要
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {

        // 还原秘钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");

        // 实例化 MAC
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 初始化 MAC
        mac.init(secretKey);

        // 执行消息摘要
        byte[] bytes = mac.doFinal(data);

        return bytes;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String str = "HmacSHA256信息摘要";

        // 初始化秘钥
        byte[] sha256Key = initHmacSHA256Key();

        // 获取摘要
        byte[] bytes1 = encodeHmacSHA256(str.getBytes(), sha256Key);
        System.out.println("bytes1.length ---> " + bytes1.length);

        byte[] bytes2 = encodeHmacSHA256(str.getBytes(), sha256Key);
        System.out.println("bytes2.length ---> " + bytes2.length);

        System.out.println("Arrays.equals(bytes1, bytes2) ---> " + Arrays.equals(bytes1, bytes2));
    }

}
