package com.leon.digest.mac;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: MACBouncyCastleTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 16:44
 * @Version 1.0
 * @DESCRIPTION: HmacMD2、HmacMD4、HmacSHA225
 **/
public class MACBouncyCastleTest {

    /**
     * 初始化 HmacMD2 秘钥密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initHmacMD2Key() throws NoSuchAlgorithmException {

        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

        // 初始化 KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD2");

        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获得秘钥
        byte[] encoded = secretKey.getEncoded();

        return encoded;
    }

    /**
     * HmacMD2 消息摘要
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encodeHmacMD2(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

        // 还原秘钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD2");

        // 实例化 MAC
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 初始化 MAC
        mac.init(secretKey);

        // 执行消息摘要
        byte[] bytes = mac.doFinal(data);

        return bytes;
    }

    /**
     * 初始化 HmacSHA224 秘钥密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initHmacSHA224Key() throws NoSuchAlgorithmException {

        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

        // 初始化 KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA224");

        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 获得秘钥
        byte[] encoded = secretKey.getEncoded();

        return encoded;
    }

    /**
     * HmacMD2 消息摘要
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] encodeHmacSHA224(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

        // 还原秘钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA224");

        // 实例化 MAC
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());

        // 初始化 MAC
        mac.init(secretKey);

        // 执行消息摘要
        byte[] bytes = mac.doFinal(data);

        return bytes;
    }

    /**
     * HmacMD2 消息摘要
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String encodeHmacSHA224Hex(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {

        // 执行消息摘要
        byte[] bytes = encodeHmacSHA224(data, key);
        // 做十六进制转换
        byte[] encode = Hex.encode(bytes);
        return new String(encode);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String str = "encodeHmacSHA224Hex摘要";

        System.out.println("str ---> " + str);

        // 初始化秘钥
        byte[] key = initHmacSHA224Key();

        // 获取摘要
        String s1 = encodeHmacSHA224Hex(str.getBytes(), key);
        System.out.println("s1 ---> " + s1
                + "\t s1.length ---> " + s1.length()
                + " \t s1.length * 8 ---> " + s1.length() * 4); // * 4 十六进制每位代表 4 bit

        String s2 = encodeHmacSHA224Hex(str.getBytes(), key);
        System.out.println("s2 ---> " + s2);

        System.out.println("s1.equals(s2) ---> " + s1.equals(s2));
    }
}
