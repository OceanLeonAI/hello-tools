package com.leon.digest.sha;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: SHACommonsCodecTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 15:59
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class SHACommonsCodecTest {

    /**
     * SHA 消息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeSHA(String data) {
        // 执行消息摘要
        byte[] sha = DigestUtils.sha(data);
        return sha;
    }

    /**
     * SHA 消息摘要
     *
     * @param data
     * @return
     */
    public static String encodeSHAHex(String data) {
        // 执行消息摘要
        String shaHex = DigestUtils.shaHex(data);
        return shaHex;
    }

    /**
     * SHA-256 消息摘要
     *
     * @param data
     * @return
     */
    public static byte[] encodeSHA256(String data) {
        // 执行消息摘要
        byte[] sha = DigestUtils.sha256(data);
        // DigestUtils.sha384(data);
        // DigestUtils.sha512(data);
        return sha;
    }

    /**
     * SHA-256 消息摘要
     *
     * @param data
     * @return
     */
    public static String encodeSHA256Hex(String data) {
        // 执行消息摘要
        String shaHex = DigestUtils.sha256Hex(data);
        // DigestUtils.sha384Hex(data);
        // DigestUtils.sha512Hex(data);
        return shaHex;
    }

    public static void main(String[] args) {

        String str = "SHA-512消息摘要";
        System.out.println("str ---> " + str);

        String s1 = encodeSHA256Hex(str);
        System.out.println("s1 ---> " + s1);

        String s2 = encodeSHA256Hex(str);
        System.out.println("s2 ---> " + s2);

        System.out.println("s1.equals(s2) ---> " + s1.equals(s2));
    }
}
