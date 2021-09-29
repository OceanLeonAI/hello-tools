package com.leon.sign.rsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

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
 * @CLASS_NAME: SignRSAJavaTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/29 15:00
 * @Version 1.0
 * @DESCRIPTION: RSA 签名算法
 **/
public class SignRSAJavaTest {

    /**
     * 数字签名
     * 秘钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 数字签名
     * 签名、验签算法那
     */
    public static final String SIGNATURE_ALGORITHM = "MD5WithRSA";

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
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        // 转换私钥材料
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);

        // 实例化秘钥工程
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 实例化 Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 初始化 Signature
        signature.initSign(priKey);

        // 更新
        signature.update(data);

        // 签名
        return signature.sign();

    }

    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        // 转换公钥材料
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);

        // 实例化秘钥工程
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取公钥对象
        PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 实例化 Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 初始化 Signature
        signature.initVerify(pubKey);

        // 更新
        signature.update(data);

        // 签名
        return signature.verify(sign);

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {

        // 公钥
        byte[] publicKey;

        // 私钥
        byte[] privateKey;

        Map<String, Object> keyMap = initKey();
        publicKey = getPublicKey(keyMap);
        privateKey = getPrivateKey(keyMap);

        System.out.println("publicKey ---> " + Base64.encodeBase64String(publicKey));
        System.out.println("privateKey ---> " + Base64.encodeBase64String(privateKey));

        String inputStr = "RSA数字签名";
        byte[] data = inputStr.getBytes();

        // 产生签名
        byte[] sign = sign(data, privateKey);
        System.out.println("签名 Base64 ---> " + Base64.encodeBase64String(sign));
        System.out.println("签名 Hex ---> " + Hex.encodeHexString(sign));

        // 验证
        boolean verify = verify(data, publicKey, sign);
        System.out.println("验签通过？ ---> " + verify);

    }


}
