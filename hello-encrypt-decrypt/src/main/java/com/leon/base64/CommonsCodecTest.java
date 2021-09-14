package com.leon.base64;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: CommonsCodecTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/14 16:13
 * @Version 1.0
 * @DESCRIPTION:
 * @see Base64
 * 比
 * @see org.bouncycastle.util.encoders.Base64
 * 更好
 **/
public class CommonsCodecTest {

    // 字符编码
    public final static String ENCODING = "UTF-8";

    // 测试数据
    public final static String DATA = "Java加密与解密的艺术";

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String data) throws UnsupportedEncodingException {
        byte[] encode = Base64.encodeBase64(data.getBytes(ENCODING));
        return new String(encode, ENCODING);
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeSafe(String data) throws UnsupportedEncodingException {
        byte[] encode = Base64.encodeBase64(data.getBytes(ENCODING), true);
        return new String(encode, ENCODING);
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String data) throws UnsupportedEncodingException {
        byte[] encodeBase64URLSafe = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
        return new String(encodeBase64URLSafe, ENCODING);
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String data) throws UnsupportedEncodingException {
        byte[] decode = Base64.decodeBase64(data.getBytes(ENCODING));
        return new String(decode, ENCODING);
    }

    public static void base64Test() throws UnsupportedEncodingException {
        String data = DATA;
        System.out.println("加密前 ---> " + data);

        String encode = encode(data);
        System.out.println("加密后 ---> " + encode);

        String decode = decode(encode);
        System.out.println("解密后 ---> " + decode);
    }

    public static void URLBase64Test() throws UnsupportedEncodingException {

        String data = DATA;
        System.out.println("加密前 ---> " + data);

        String encode = urlEncode(data);
        System.out.println("加密后 ---> " + encode);

        String decode = decode(encode);
        System.out.println("解密后 ---> " + decode);
    }

    /**
     * 测试
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        base64Test();

        System.out.println("======================");

        URLBase64Test();

    }

}
