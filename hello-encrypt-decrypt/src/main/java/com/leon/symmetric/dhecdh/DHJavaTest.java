package com.leon.symmetric.dhecdh;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: DHJavaTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/16 14:57
 * @Version 1.0
 * @DESCRIPTION: DH&ECDH
 * Diffie-Hellman DH 秘钥交换算法
 **/
public class DHJavaTest {

    /**
     * 非对称加密秘钥算法
     */
    public static final String KEY_ALGORITHM = "DH";

    /**
     * 本地秘钥算法，对称秘钥加密
     * 可选 DES、DESede、AES
     */
    public static final String SECRET_ALGORITHM = "AES";

    /**
     * 秘钥长度
     * DH 默认1024
     * 秘钥长度必须是64的整数倍，范围是512~1024
     */
    public static final int KEY_SIZE = 512;

    // 公钥
    public static final String PUBLIC_KEY = "DHPublicKey";

    // 私钥
    public static final String PRIVATE_KEY = "DHPrivateKey";

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

        // 甲方公钥
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();

        // 甲方私钥
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();

        // 存储
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 初始化乙方秘钥
     *
     * @param key 甲方公钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     */
    public static Map<String, Object> initKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException {

        // 解析甲方秘钥
        // 转换公钥材料
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);

        // 实例化秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 由甲方公钥构建乙方秘钥
        DHParameterSpec dhParameterSpec = ((DHPublicKey) pubKey).getParams();

        // 实例化秘钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());

        // 初始化秘钥生成器
        keyPairGenerator.initialize(dhParameterSpec);

        // 产生秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 乙方公钥
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();

        // 乙方私钥
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();

        // 存储
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  秘钥
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 生成本地秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, SECRET_ALGORITHM);

        // 数据加密
        Cipher cipher = Cipher.getInstance(secretKeySpec.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] bytes = cipher.doFinal(data);

        return bytes;

    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  秘钥
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 生成本地秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, SECRET_ALGORITHM);

        // 数据加密
        Cipher cipher = Cipher.getInstance(secretKeySpec.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] bytes = cipher.doFinal(data);

        return bytes;

    }

    /**
     * 构建秘钥
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 本地秘钥
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     */
    public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

        // 实例化秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 初始化公钥
        // 秘钥材料转换
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);

        // 产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 初始化私钥
        // 秘钥材料转换
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);

        // 产生私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 实例化
        KeyAgreement keyAgreement = KeyAgreement.getInstance(keyFactory.getAlgorithm());

        // 初始化
        keyAgreement.init(priKey);
        keyAgreement.doPhase(pubKey, true);

        // 生成本地秘钥
        SecretKey secretKey = keyAgreement.generateSecret(SECRET_ALGORITHM);

        return secretKey.getEncoded();

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
     * 测试
     * 关注点
     * 1.甲乙方本地秘钥是否相同
     * 2.甲方加密数据乙方是否能解密，反之亦然
     *
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeySpecException, InvalidKeyException {

        // 甲方公钥
        byte[] publicKey1;

        // 甲方私钥
        byte[] privateKey1;

        // 甲方本地秘钥
        byte[] key1;

        // 乙方公钥
        byte[] publicKey2;

        // 乙方私钥
        byte[] privateKey2;

        // 乙方本地秘钥
        byte[] key2;


        // --------------------------------- 初始化秘钥 begin ---------------------------------

        // 生成甲方秘钥对
        Map<String, Object> keyMap1 = initKey();
        publicKey1 = getPublicKey(keyMap1);
        System.out.println("甲方公钥 ---> " + Base64.encodeBase64String(publicKey1));
        privateKey1 = getPrivateKey(keyMap1);
        System.out.println("甲方私钥 ---> " + Base64.encodeBase64String(privateKey1));

        // 由甲方公钥产生本地秘钥对
        Map<String, Object> keyMap2 = initKey(publicKey1);
        publicKey2 = getPublicKey(keyMap2);
        System.out.println("乙方公钥 ---> " + Base64.encodeBase64String(publicKey2));
        privateKey2 = getPrivateKey(keyMap2);
        System.out.println("乙方私钥 ---> " + Base64.encodeBase64String(privateKey2));

        key1 = getSecretKey(publicKey2, privateKey1);
        System.out.println("甲方本地秘钥 ---> " + Base64.encodeBase64String(key1));

        key2 = getSecretKey(publicKey1, privateKey2);
        System.out.println("乙方本地秘钥 ---> " + Base64.encodeBase64String(key2));

        System.out.println("甲方本地秘钥 == 乙方本地秘钥 " + Arrays.equals(key1, key2));

        // --------------------------------- 初始化秘钥   end ---------------------------------

    }


}
