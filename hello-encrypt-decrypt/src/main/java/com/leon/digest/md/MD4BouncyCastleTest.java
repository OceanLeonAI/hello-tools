package com.leon.digest.md;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: MD4BouncyCastleTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 15:15
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class MD4BouncyCastleTest {

    /**
     * MD4 摘要
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encodeMD4(byte[] data) throws NoSuchAlgorithmException {

        // 加入 BouncyCastle 支持
        Security.addProvider(new BouncyCastleProvider());

        // 初始化 MessageDigest
        MessageDigest md4 = MessageDigest.getInstance("MD4");

        // 执行摘要
        byte[] digest = md4.digest(data);

        return digest;

    }

    /**
     * MD4 信息只要
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeMD4Hex(byte[] data) throws NoSuchAlgorithmException {

        // 执行消息摘要
        byte[] bytes = encodeMD4(data);

        // 做十六进制编码处理
        return new String(Hex.encode(bytes));

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String str = "MD4Hex信息只要";

        byte[] bytes1 = encodeMD4(str.getBytes());
        byte[] bytes2 = encodeMD4(str.getBytes());
        System.out.println("原文 ---> " + str);
        System.out.println("bytes1 ---> " + bytes1);
        System.out.println("bytes2 ---> " + bytes2);
        System.out.println("bytes1 == bytes2 " + (bytes1 == bytes2));
        System.out.println("bytes1.length ---> " + bytes1.length);
        System.out.println("bytes2.length ---> " + bytes2.length);
        System.out.println("bytes1 Arrays.equals bytes2 " + Arrays.equals(bytes1, bytes2));

        System.out.println("==============================");

        // 获取摘要
        String s1 = encodeMD4Hex(str.getBytes());
        String s2 = encodeMD4Hex(str.getBytes());

        System.out.println("原文 ---> " + str);
        System.out.println("s1 ---> " + s1);
        System.out.println("s2 ---> " + s2);
        System.out.println("s1 == s2 " + (s1 == s2));
        System.out.println("s1 equal s2 " + s1.equals(s2));

        Assert.assertEquals(s1, s2);

    }
}
