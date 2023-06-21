package com.manager.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/sys")
@RestController
public class FileUploadController {

    @Value("${tecent.cos.bucketName}")
    private String bucketName;

    @Value("${tecent.cos.accessKey}")
    private String accessKey;

    @Value("${tecent.cos.secretKey}")
    private String secretKey;

    @Value("${tecent.cos.regionName}")
    private String regionName;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public R upload(@RequestBody MultipartFile file, HttpServletRequest request) {
        String url = "";
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        // 获取文件类型
        String name = file.getOriginalFilename();
        String fileType = name.substring(name.lastIndexOf(".") + 1);

        // bucket名需包含appid

        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        String key = format + UUID.randomUUID() + "." + fileType;
        try {
            // 处理文件路径
            String filePath = request.getSession().getServletContext().getRealPath("/") + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            File localFile = new File(filePath);
            // 报错请求对象
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            // 设置节点
            appendObjectRequest.setPosition(0L);
            AppendObjectResult appendObjectResult = cosclient.appendObject(appendObjectRequest);
            // 文件大小
            long nextAppendPosition = appendObjectResult.getNextAppendPosition();
            // 获取返回对象
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            COSObject cosObject = cosclient.getObject(getObjectRequest);
            url = cosObject.getObjectContent().getHttpRequest().getURI().toString();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cosclient.shutdown();
        }
        return R.ok(url);
    }
}