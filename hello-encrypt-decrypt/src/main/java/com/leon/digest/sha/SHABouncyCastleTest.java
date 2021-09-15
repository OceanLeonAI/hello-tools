package com.leon.digest.sha;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: SHABouncyCastleTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 15:52
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class SHABouncyCastleTest {

    /**
     * SHA-224 消息摘要
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encodeSHA224(byte[] data) throws NoSuchAlgorithmException {

        // 加入 BouncyCastleProvider 支持
        Security.addProvider(new BouncyCastleProvider());

        // 初始化 MessageDigest
        MessageDigest sha = MessageDigest.getInstance("SHA-224");

        // 执行信息只要
        byte[] digest = sha.digest(data);

        return digest;
    }

    public static String encodeSHA512Hex(byte[] data) throws NoSuchAlgorithmException {

        // 执行消息摘要
        byte[] bytes = encodeSHA224(data);

        // 十六进制编码处理
        String s = new String(Hex.encode(bytes));

        return s;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String str = "SHA-224消息摘要";
        System.out.println("str ---> " + str);

        String s1 = encodeSHA512Hex(str.getBytes());
        System.out.println("s1 ---> " + s1);

        String s2 = encodeSHA512Hex(str.getBytes());
        System.out.println("s2 ---> " + s2);

        System.out.println("s1.equals(s2) ---> " + s1.equals(s2));
    }

}
