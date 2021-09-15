package com.leon.digest.md;

import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: MessageDigestTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/14 17:55
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class MessageDigestTest {

    @Test
    public void testEncodeMD2() throws NoSuchAlgorithmException {

        String str = "MD2消息摘要";

        MessageDigest md2 = MessageDigest.getInstance("MD2");
        byte[] digest1 = md2.digest(str.getBytes());
        byte[] digest2 = md2.digest(str.getBytes());

        // 校验
        Assert.assertArrayEquals(digest1, digest2);

        // System.out.println(digest1 == digest2);
    }
}
