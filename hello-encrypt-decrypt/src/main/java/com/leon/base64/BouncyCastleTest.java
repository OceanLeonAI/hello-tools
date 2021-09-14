package com.leon.base64;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

import java.io.UnsupportedEncodingException;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: BouncyCastleTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/14 16:13
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class BouncyCastleTest {

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
        // byte[] encode = Base64.encode(data.getBytes(StandardCharsets.UTF_8));
        byte[] encode = Base64.encode(data.getBytes(ENCODING));
        return new String(encode, ENCODING);
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String data) throws UnsupportedEncodingException {
        byte[] decode = Base64.decode(data.getBytes(ENCODING));
        return new String(decode, ENCODING);
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String data) throws UnsupportedEncodingException {
        byte[] encode = UrlBase64.encode(data.getBytes(ENCODING));
        return new String(encode, ENCODING);
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(String data) throws UnsupportedEncodingException {
        byte[] decode = UrlBase64.decode(data.getBytes(ENCODING));
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

        String decode = urlDecode(encode);
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
