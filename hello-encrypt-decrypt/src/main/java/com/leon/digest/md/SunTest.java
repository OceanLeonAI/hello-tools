package com.leon.digest.md;

import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: SunTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/14 17:30
 * @Version 1.0
 * @DESCRIPTION: 无论 MD2、MD3、MD5算法，最终产生一个 128 位的信息摘要
 **/
public class SunTest {

    private static final String DATA = "这是测试代码";

    /**
     * MD2 消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return 消息摘要
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encodeMD2(byte[] data) throws NoSuchAlgorithmException {

        // 初始化 MessageDigest
        MessageDigest md2 = MessageDigest.getInstance("MD2");

        // 执行消息摘要
        byte[] digest = md2.digest(data);

        return digest;

    }

    /**
     * MD5 消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return 消息摘要
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {

        // 初始化 MessageDigest
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        // 执行消息摘要
        byte[] digest = md5.digest(data);

        return digest;

    }

    public static void testEncodeMD2() throws NoSuchAlgorithmException {

        String str = "MD2信息摘要";
        // 获取摘要
        byte[] data1 = encodeMD2(str.getBytes());
        byte[] data2 = encodeMD2(str.getBytes());

        // 校验
        System.out.println(Arrays.equals(data1, data2));

        // 校验
        Assert.assertArrayEquals("不相同", data1, data2);
    }

    public static void testEncodeMD5() throws NoSuchAlgorithmException {

        String str = "MD5信息摘要";

        // 获取摘要
        byte[] data1 = encodeMD5(str.getBytes());
        byte[] data2 = encodeMD5(str.getBytes());

        // 校验
        System.out.println(Arrays.equals(data1, data2));


        // 校验
        Assert.assertArrayEquals("不相同", data1, data2);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        testEncodeMD2();

        System.out.println("========================");

        testEncodeMD5();
    }
}
