package com.leon.asymmetric.idea;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: IDEAJavaTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/16 11:25
 * @Version 1.0
 * @DESCRIPTION: International Data Encryption Algorithm
 **/
public class IDEAJavaTest {
    /**
     * 秘钥算法
     */
    public static final String KEY_ALGORITHM = "IDEA";

    /**
     * 加密/解密算法/工作模式/填充方式
     * <p>
     * Java7 支持 PKCS5Padding 填充方式
     * <p>
     * Bouncy Castle 支持 PKCS7Padding 填充方式
     */
    public static final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

    /**
     * 生成秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {

        // 加入 Bouncy Castle 支持
        Security.addProvider(new BouncyCastleProvider());

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

        keyGenerator.init(128);

        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 二进制形式返回
        return secretKey.getEncoded();
    }

    /**
     * 转换秘钥
     *
     * @param key
     * @return
     */
    private static Key toKey(byte[] key) {
        // 实例化 IDEA 秘钥材料
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 加入 Bouncy Castle 支持
        Security.addProvider(new BouncyCastleProvider());

        // 还原秘钥
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);

        // 执行操作
        return cipher.doFinal(data);

    }

    public static byte[] decrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // 加入 Bouncy Castle 支持
        Security.addProvider(new BouncyCastleProvider());

        // 还原秘钥
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.DECRYPT_MODE, k);

        // 执行操作
        return cipher.doFinal(data);

    }


    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        String data = "IDEAJavaTest对称加密测试";
        byte[] dataBytes = data.getBytes();

        System.out.println("加密前 data ---> " + data);

        // 初始化秘钥
        byte[] key = initKey();
        System.out.println("秘钥 ---> " + Base64.encodeBase64String(key)); // org.apache.commons.codec.binary

        // 加密
        byte[] encrypt = encrypt(dataBytes, key);
        System.out.println("加密后 ---> " + Base64.encodeBase64String(encrypt));

        // 解密
        byte[] decrypt = decrypt(encrypt, key);
        String decryptString = new String(decrypt);
        System.out.println("解密后 decryptString ---> " + decryptString);

        System.out.println("data == decryptString " + data.equals(decryptString));
    }

}
