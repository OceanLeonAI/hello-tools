package com.leon.asymmetric.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: AESCoder
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/29 11:27
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class AESCoder {

    public static final String ALGORITHM = "AES";

    /**
     * 生成秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {

        // 实例化
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);

        // 初始化秘钥长度
        keyGenerator.init(256);

        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        return secretKey.getEncoded();
    }

    /**
     * 生成秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String initKeyString() throws NoSuchAlgorithmException {
        return Base64.encodeBase64String(initKey());
    }

    /**
     * 获取秘钥
     *
     * @param key
     * @return
     */
    public static byte[] getKey(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * 摘要处理
     *
     * @param data
     * @return
     */
    public static String shaHex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    public static boolean validate(byte[] data, String messageDigest) {
        return messageDigest.equals(shaHex(data));
    }

    /**
     * 转换秘钥
     *
     * @param key
     * @return
     */
    private static Key toKey(byte[] key) {
        // 实例化AES秘钥材料
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        return secretKeySpec;
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  秘钥
     * @return 解密后数据
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 还原秘钥
        Key k = toKey(key);

        // 实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, k);

        // 解密
        return cipher.doFinal(data);

    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static byte[] decrypt(String data, byte[] key) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return decrypt(data.getBytes(), key);
    }

    /**
     * 加密
     *
     * @param data 待解密数据
     * @param key  秘钥
     * @return 解密后数据
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 还原秘钥
        Key k = toKey(key);

        // 实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, k);

        // 解密
        return cipher.doFinal(data);

    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static byte[] encrypt(String data, byte[] key) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return encrypt(data.getBytes(), key);
    }


}
