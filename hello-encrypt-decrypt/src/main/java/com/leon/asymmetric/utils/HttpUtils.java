package com.leon.asymmetric.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: HttpUtils
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/29 10:52
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class HttpUtils {

    public static final String CHARACTER_ENCODING = "UTF-8";

    public static final String METHOD_POST = "POST";

    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * 打印数据
     *
     * @param response
     * @param data
     */
    public static void responseWriter(HttpServletResponse response, byte[] data) throws IOException {
        if (null != data) {
            response.setContentLength(data.length);
            DataOutputStream dataOutputStream = new DataOutputStream(response.getOutputStream());
            dataOutputStream.write(data);
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    /**
     * 从请求中读取流字节
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] requestRead(HttpServletRequest request) throws IOException {
        int length = request.getContentLength();
        byte[] data = null;
        if (length > 0) {
            data = new byte[length];
            ServletInputStream inputStream = request.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(data);
            dataInputStream.close();
        }
        return data;
    }

    /**
     * 以POST方式指定地址发送数据包请求，并取得返回的数据包
     *
     * @param urlString   请求地址
     * @param requestData 请求数据
     * @return byte[] 数据包
     */
    public static byte[] postRequest(String urlString, byte[] requestData) throws IOException {
        Properties requestProperties = new Properties();
        requestProperties.setProperty(CONTENT_TYPE,
                "application/octet-stream;" + // 二进制流方式提交
                        "charset=" + CHARACTER_ENCODING); // 字符编码

        return postRequest(urlString, requestData, requestProperties);
    }

    public static byte[] postRequest(String urlString, byte[] requestData, Properties requestProperties) throws IOException {
        byte[] responseData = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // 设置请求参数
            if (null != requestProperties && requestProperties.size() > 0) {
                for (Map.Entry<Object, Object> entry : requestProperties.entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    connection.setRequestProperty(key, value);
                }
            }

            // 设置请求方式
            connection.setRequestMethod(METHOD_POST);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // 获取数据输出对象
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

            // 请求参数
            if (null != dataOutputStream) {
                dataOutputStream.write(requestData);
            }
            dataOutputStream.flush();
            dataOutputStream.close();

            // 获取数据输入对象
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            int contentLength = connection.getContentLength();
            if (contentLength > 0) {
                responseData = new byte[contentLength];
                dataInputStream.readFully(responseData);
            }
            dataInputStream.close();
        } finally {
            if (null != connection) {
                connection.disconnect();
                connection = null; // help GC
            }
        }
        return responseData;
    }
}
