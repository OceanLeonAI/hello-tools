package com.leon.symmetric.elgamal;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: ElGamalBouncyCastleTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/27 22:28
 * @Version 1.0
 * @DESCRIPTION: ElGamal 非对称加密算法案例
 * 基于大数因子分解
 **/
public class ElGamalBouncyCastleTest {

    /**
     * 非对称加密算法
     */
    public static final String KEY_ALGORITHM = "ElGamal";

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "ElGamalPublicKey";

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY = "ElGamalPrivateKey";

    /**
     * 秘钥长度
     * 默认1024
     * 秘钥长度必须是8的整数倍
     * 范围是160~16384
     */
    public static final int KEY_SIZE = 256;

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException {

        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());

        //  实例化算法参数生成器
        AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance(KEY_ALGORITHM);

        // 初始化算法生成器
        apg.init(KEY_SIZE);

        // 生成算法参数
        AlgorithmParameters parameters = apg.generateParameters();

        // 构建参数材料
        DHParameterSpec elParameterSpec = parameters.getParameterSpec(DHParameterSpec.class);

        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        // 初始化秘钥对生成器
        keyPairGenerator.initialize(elParameterSpec, new SecureRandom());

        // 生成秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 公钥
        PublicKey publicKey = keyPair.getPublic();

        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();

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

    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

        // 私钥材料转换
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);

        // 实例化秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
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
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

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

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidParameterSpecException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

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

        String inputData = "ElGamal 非对称加密算法";

        System.out.println("加密前数据 ---> " + inputData);

        System.out.println("========================== 公钥加密私钥解密 ==========================");

        // 公钥加密
        byte[] encryptByPublicKey = encryptByPublicKey(inputData.getBytes(), publicKey);
        System.out.println("私钥加密后数据 ---> " + Base64.encodeBase64String(encryptByPublicKey));

        // 私钥解密
        byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey, privateKey);
        System.out.println("私钥解密后数据 ---> " + new String(decryptByPrivateKey));
    }
}
