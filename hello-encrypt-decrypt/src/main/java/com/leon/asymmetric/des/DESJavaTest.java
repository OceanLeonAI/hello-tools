package com.leon.asymmetric.des;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: DESJavaTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 17:27
 * @Version 1.0
 * @DESCRIPTION: Java7 仅支持56位秘钥长度
 **/
public class DESJavaTest {

    /**
     * Java7 只支持56位秘钥
     * <p>
     * Bouncy Castle 支持64位秘钥
     */
    public static final String KEY_ALGORITHM = "DES";

    /**
     * 加密/解密算法/工作模式/填充方式
     */
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5padding";

    /**
     * 生成秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {

        /**
         *实例化秘钥生成器
         * 若需要使用64位秘钥
         * KeyGenerator.getInstance(CIPHER_ALGORITHM,"BC") // BC Bouncy Castle 缩写
         * 这段代码等同于
         * Security.addProvider(new BouncyCastleProvider());
         * KeyGenerator kg = KeyGenerator.getInstance(CIPHER_ALGORITHM);
         */

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

        /**
         * 如果是64位秘钥
         * keyGenerator.init(64);
         *
         * keyGenerator.init(new SecureRandom()); // 默认长度
         */
        keyGenerator.init(56);

        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();

        // 二进制形式返回
        return secretKey.getEncoded();
    }

    private static Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {

        // 实例化 DES 秘钥材料
        DESKeySpec desKeySpec = new DESKeySpec(key);

        // 实例化秘钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

        // 生成秘钥
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

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
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // 还原秘钥
        Key k = toKey(key);

        // 实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);

        // 执行操作
        return cipher.doFinal(data);

    }

    public static byte[] decrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        // 还原秘钥
        Key k = toKey(key);

        // 实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.DECRYPT_MODE, k);

        // 执行操作
        return cipher.doFinal(data);

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        String data = "DESJavaTest对称加密测试";
//        String data = "DES";
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
