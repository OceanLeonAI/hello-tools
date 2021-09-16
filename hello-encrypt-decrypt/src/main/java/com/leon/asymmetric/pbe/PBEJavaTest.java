package com.leon.asymmetric.pbe;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: PBEJavaTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/16 11:30
 * @Version 1.0
 * @DESCRIPTION: Password Based Encryption 基于口令加密
 **/
public class PBEJavaTest {

    /**
     * Java7 支持以下任意一种算法
     * PBEWithMD5AndDES
     * PBEWithMD5AndAndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     * PBKDF2WithHmacSHA1
     */
    public static final String ALGORITHM = "PBEWithMD5andDES";

    /**
     * 迭代次数
     */
    public static final int ITERATION_COUNT = 100;

    /**
     * 初始化盐
     * <p>
     * 盐长度必须为8字节
     *
     * @return
     */
    public static byte[] initSalt() {

        // 实例化安全随机数
        SecureRandom secureRandom = new SecureRandom();

        // 产出盐
        byte[] bytes = secureRandom.generateSeed(8);

        return bytes;

    }

    /**
     * 转换秘钥
     *
     * @param password 密码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     */
    public static Key toKey(String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {

        // 秘钥材料转换
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

        // 实例化PBE参数材料
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);

        // 生成秘钥
        SecretKey secretKey = keyFactory.generateSecret(pbeKeySpec);

        return secretKey;

    }

    /**
     * 加密
     *
     * @param data     待加密数据
     * @param password 密码
     * @param salt     盐
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] data, String password, byte[] salt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 转换秘钥
        Key key = toKey(password);

        // 实例化PBE参数材料
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);

        // 实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);

        // 加密
        byte[] bytes = cipher.doFinal(data);

        return bytes;

    }

    /**
     * 解密
     *
     * @param data     待加密数据
     * @param password 密码
     * @param salt     盐
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decrypt(byte[] data, String password, byte[] salt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 转换秘钥
        Key key = toKey(password);

        // 实例化PBE参数材料
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);

        // 实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);

        // 解密
        byte[] bytes = cipher.doFinal(data);

        return bytes;

    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        // 原始数据
        String str = "PBE对称加密测试";
        System.out.println("加密前数据 ---> " + str);
        byte[] input = str.getBytes();

        // 密码
        String password = "hello world! @123!@#";
        System.out.println("密码 ---> " + password);

        // 初始化盐
        byte[] salt = initSalt();

        // 加密
        byte[] encrypt = encrypt(input, password, salt);
        System.out.println("加密后数据 ---> " + Base64.encodeBase64String(encrypt));

        // 解密
        byte[] decrypt = decrypt(encrypt, password, salt);
        System.out.println("解密后数据 ---> " + new String(decrypt));
    }


}
