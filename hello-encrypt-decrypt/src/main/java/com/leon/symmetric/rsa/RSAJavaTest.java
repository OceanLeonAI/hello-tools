package com.leon.symmetric.rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: RSAJavaTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/27 16:53
 * @Version 1.0
 * @DESCRIPTION: RSA 非对称加密算法案例
 * 基于大数因子分解
 **/
public class RSAJavaTest {

    /**
     * 非对称加密算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 秘钥长度
     * 默认1024
     * 秘钥长度必须是64的整数倍
     * 范围是512~65536
     */
    public static final int KEY_SIZE = 512;

    /**
     * 初始化甲方秘钥
     *
     * @return 甲方秘钥 Map
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {

        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        // 初始化秘钥对生成器
        keyPairGenerator.initialize(KEY_SIZE);

        // 生成秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 存储
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 获取私钥
     *
     * @param keyMap 秘钥 Map
     * @return 私钥
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 获取公钥
     *
     * @param keyMap 秘钥 Map
     * @return 公钥
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 获得私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 获得私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 获得公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 获得公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        // 公钥
        byte[] publicKey;
        // 私钥
        byte[] privateKey;

        // 初始化秘钥
        Map<String, Object> keyMap = initKey();
        publicKey = getPublicKey(keyMap);
        privateKey = getPrivateKey(keyMap);

        System.out.println("公钥 ---> " + Base64.encodeBase64String(publicKey));
        System.out.println("私钥 ---> " + Base64.encodeBase64String(privateKey));

        String inputData = "RSA非对称加密算法";

        System.out.println("========================== 私钥加密公钥解密 ==========================");

        // 私钥加密
        byte[] encryptByPrivateKey = encryptByPrivateKey(inputData.getBytes(), privateKey);
        System.out.println("私钥加密后数据 ---> " + Base64.encodeBase64String(encryptByPrivateKey));

        // 公钥解密
        byte[] decryptByPublicKey = decryptByPublicKey(encryptByPrivateKey, publicKey);
        System.out.println("公钥解密后数据 ---> " + new String(decryptByPublicKey));

        System.out.println("========================== 公钥加密私钥解密 ==========================");

        // 公钥加密
        byte[] encryptByPublicKey = encryptByPublicKey(inputData.getBytes(), publicKey);
        System.out.println("私钥加密后数据 ---> " + Base64.encodeBase64String(encryptByPublicKey));

        // 私钥解密
        byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey, privateKey);
        System.out.println("私钥解密后数据 ---> " + new String(decryptByPrivateKey));

    }
}
