package com.leon.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件的校验测试
 * 验证从网上下载的安装包是否被串改
 */
public class FileValidate {

    public static final String FILE_PATH = "E:\\test.txt";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        testByMessageDigest();

        System.out.println("============================");

        testByDigestUtil();

    }

    /**
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void testByMessageDigest() throws IOException, NoSuchAlgorithmException {

        // 构建文件流
        FileInputStream fis = new FileInputStream(new File(FILE_PATH));

        // 初始化 MessageDigest，并指定MD5算法
        DigestInputStream digestInputStream = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));

        // 设置缓冲流
        int buf = 1024;

        // 缓冲字节数组
        byte[] buffer = new byte[buf];

        int read = digestInputStream.read(buffer, 0, buf);
        while (read > -1) {
            read = digestInputStream.read(buffer, 0, buf);
        }

        // 关闭流
        digestInputStream.close();

        // 获取md5
        MessageDigest messageDigest = digestInputStream.getMessageDigest();

        // 摘要处理
        byte[] digest = messageDigest.digest();

        // 获取十六进制摘要
        String hexString = Hex.toHexString(digest);

        System.out.println("该文件的 md5 为 ---> " + hexString);


    }

    public static void testByDigestUtil() throws IOException, NoSuchAlgorithmException {

        // 构建文件流
        FileInputStream fis = new FileInputStream(new File(FILE_PATH));

        byte[] digest = DigestUtils.md5(fis);

        // 获取十六进制摘要
        String hexString = Hex.toHexString(digest);

        System.out.println("该文件的 md5 为 ---> " + hexString);


    }
}
