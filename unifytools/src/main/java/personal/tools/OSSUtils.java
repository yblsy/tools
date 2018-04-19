package personal.tools;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author 刘晨
 * @create 2018-04-19 15:09
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Slf4j
@Setter
public class OSSUtils {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private OSSClient ossClient;
    private String bucketName;

    public OSSUtils(){

    }

    public OSSUtils(String endPoint,String accessKeyId,String accessKeySecret){
        this.endPoint = endPoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    /**
     * 实例化OSS客户端
     */
    private void getInstance(){
        this.ossClient = new OSSClient(this.endPoint,this.accessKeyId,this.accessKeySecret);
    }

    /**
     * OSS客户端关闭
     */
    private void close(){
        ossClient.shutdown();
    }

    /**
     * 创建bucket
     * @param bucketName
     * @param cannedAccessControlList
     * @param storageClass
     * @return
     */
    private Bucket createBucket(String bucketName, CannedAccessControlList cannedAccessControlList, StorageClass storageClass){
        this.getInstance();
        CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
        // 设置bucket权限为公共读，默认是私有读写
        createBucketRequest.setCannedACL(cannedAccessControlList);
        // 设置bucket存储类型为低频访问类型，默认是标准类型
        createBucketRequest.setStorageClass(storageClass);
        Bucket bucket = ossClient.createBucket(createBucketRequest);
        this.close();
        return bucket;
    }

    /**
     * OSS简单上传
     * @param inputStream
     * @return
     */
    public String ossSimpleOssUpload(String key,InputStream inputStream){
        return ossSimpleOssUpload(this.bucketName,key,inputStream);
    }

    /**
     *
     * @param bucketName
     * @param key
     * @param inputStream
     * @return
     */
    public String ossSimpleOssUpload(String bucketName,String key,InputStream inputStream){
        return ossSimpleOssUpload(bucketName,key,inputStream,null);
    }

    /**
     * 可以自定义文件头部的上传，方便之后设定的MD5效验  文件增加md5效验
     *
     * @param bucketName
     * @param key
     * @param inputStream
     * @param objectMetadata
     * @return
     */
    public String ossSimpleOssUpload(String bucketName,String key,InputStream inputStream,ObjectMetadata objectMetadata){
        this.getInstance();
        if(objectMetadata == null){
            ossClient.putObject(bucketName,key,inputStream);
        }else{
            ossClient.putObject(bucketName,key,inputStream,objectMetadata);
        }
        this.close();
        return "http://" + bucketName + "." +this.endPoint +"/" + key;
    }
}
