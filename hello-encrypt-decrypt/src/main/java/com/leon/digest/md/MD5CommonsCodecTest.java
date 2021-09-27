package com.leon.digest.md;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: MD5CommonsCodecTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 15:28
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class MD5CommonsCodecTest {

    /**
     * MD5 消息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeMD5(String data) {
        // 执行消息摘要
        byte[] bytes = DigestUtils.md5(data);
        return bytes;
    }

    /**
     * MD5 消息摘要
     *
     * @param data
     * @return
     */
    public static String encodeMD5Hex(String data) {
        // 执行消息摘要
        String md5Hex = DigestUtils.md5Hex(data);
        return md5Hex;
    }

    public static void main(String[] args) {

        String str = "MD5信息消息摘要";

        byte[] encodeMD51 = encodeMD5(str);
        byte[] encodeMD52 = encodeMD5(str);
        System.out.println("Arrays.equals(encodeMD51, encodeMD52) ---> " + Arrays.equals(encodeMD51, encodeMD52));

        System.out.println("====================================");

        String md5Hex1 = encodeMD5Hex(str);
        System.out.println("md5Hex1 --->" + md5Hex1 + "bit length ---> " + md5Hex1.length() * 4); // 32 为十六进制字符串

        String md5Hex2 = encodeMD5Hex(str);
        System.out.println("md5Hex2 --->" + md5Hex2);

        System.out.println("md5Hex1.equals(md5Hex2) ---> " + md5Hex1.equals(md5Hex2));
    }
}
