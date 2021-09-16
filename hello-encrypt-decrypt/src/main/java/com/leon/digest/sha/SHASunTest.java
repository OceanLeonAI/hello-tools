package com.leon.digest.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: SHASunTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 15:42
 * @Version 1.0
 * @DESCRIPTION:
 * Secure Hash Algorithm 安全散列算法
 **/
public class SHASunTest {

    /**
     * SHA-1 信息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeSHA(byte[] data) throws NoSuchAlgorithmException {

        // 初始化 MessageDigest
        MessageDigest sha = MessageDigest.getInstance("SHA");

        // 执行信息只要
        byte[] digest = sha.digest(data);

        return digest;
    }

    /**
     * SHA-256 信息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeSHA256(byte[] data) throws NoSuchAlgorithmException {

        // 初始化 MessageDigest
        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        // 执行信息只要
        byte[] digest = sha.digest(data);

        return digest;
    }

    /**
     * SHA-384 信息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeSHA384(byte[] data) throws NoSuchAlgorithmException {

        // 初始化 MessageDigest
        MessageDigest sha = MessageDigest.getInstance("SHA-384");

        // 执行信息只要
        byte[] digest = sha.digest(data);

        return digest;
    }

    /**
     * SHA-512 信息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeSHA512(byte[] data) throws NoSuchAlgorithmException {

        // 初始化 MessageDigest
        MessageDigest sha = MessageDigest.getInstance("SHA-512");

        // 执行信息只要
        byte[] digest = sha.digest(data);

        return digest;
    }

    public static void encodeSHA512Test() throws NoSuchAlgorithmException {

        String str = "SHA512信息摘要";

        byte[] bytes1 = encodeSHA512(str.getBytes());
        System.out.println("SHA512 bytes1 长度 ---> " + bytes1.length); // 换算成 2 进制，刚好 512 位

        byte[] bytes2 = encodeSHA512(str.getBytes());
        System.out.println("SHA512 bytes2 长度 ---> " + bytes2.length);

        System.out.println("SHA512 Arrays.equals(bytes1, bytes2) --->" + Arrays.equals(bytes1, bytes2));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        encodeSHA512Test();

    }

}
