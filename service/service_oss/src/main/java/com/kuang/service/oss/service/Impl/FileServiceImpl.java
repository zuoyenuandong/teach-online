package com.kuang.service.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.kuang.service.oss.service.FileService;
import com.kuang.service.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private OssProperties ossProperties;
    @Override
    public String upload(InputStream inputStream, String module, String originaFilename) {
        String endpoint = ossProperties.getEndpoint();
        String bucketname = ossProperties.getBucketname();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();

        String floder = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originaFilename.substring(originaFilename.lastIndexOf("."));
        String objectName = module+"/"+floder+"/"+fileName+fileExtension;

        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        if (!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        ossClient.putObject(bucketname, objectName, inputStream);

        ossClient.shutdown();
        return "https://"+bucketname+"."+endpoint+"/"+objectName;
    }

    @Override
    public void remove(String url) {
        String endpoint = ossProperties.getEndpoint();
        String bucketname = ossProperties.getBucketname();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();

        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        String host = "https://"+bucketname+"."+endpoint+"/";
        String objectName = url.substring(host.length());

        ossClient.deleteObject(bucketname, objectName);

        ossClient.shutdown();
    }
}
