package com.kuang.service.oss.service;

import java.io.InputStream;
import java.io.InputStreamReader;

public interface FileService {
    /**
     * @param inputStream 输入流
     * @param module 文件夹名称
     * @param originaFilename 原始文件名称
     *@return: 文件在oss服务器的url地址
     *@Author: Kuang
     *@date: 
    **/
    String upload(InputStream inputStream,String module,String originaFilename);

    void remove(String url);

}
