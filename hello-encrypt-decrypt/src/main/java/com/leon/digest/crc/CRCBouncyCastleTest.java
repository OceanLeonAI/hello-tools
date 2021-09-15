package com.leon.digest.crc;

import java.util.zip.CRC32;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: CRCBouncyCastleTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/15 17:04
 * @Version 1.0
 * @DESCRIPTION: CRC32 得到一个8位十六进制字符串
 **/
public class CRCBouncyCastleTest {

    public static void main(String[] args) {

        String str = "测试CRC-32";
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        String hexString = Long.toHexString(crc32.getValue());

        System.out.println("str ---> " + str);
        System.out.println("hexString ---> " + hexString);
    }

}
