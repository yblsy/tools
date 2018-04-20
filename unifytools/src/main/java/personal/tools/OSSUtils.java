package personal.tools;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

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
    public void getInstance(){
        this.ossClient = new OSSClient(this.endPoint,this.accessKeyId,this.accessKeySecret);
    }

    /**
     * OSS客户端关闭
     */
    public void close(){
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
     * multipartFile形式的
     *
     * @param key
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String ossSimpleUpload4MultiPartFile(String key, MultipartFile multipartFile) throws IOException{
        InputStream inputStream = multipartFile.getInputStream();
        return ossSimpleUpload(key,inputStream);
    }

    /**
     * OSS简单上传
     * @param inputStream
     * @return
     */
    public String ossSimpleUpload(String key,InputStream inputStream){
        return ossSimpleUpload(this.bucketName,key,inputStream);
    }

    /**
     *
     * @param bucketName
     * @param key
     * @param inputStream
     * @return
     */
    public String ossSimpleUpload(String bucketName,String key,InputStream inputStream){
        return ossSimpleUpload(bucketName,key,inputStream,null);
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
    public String ossSimpleUpload(String bucketName,String key,InputStream inputStream,ObjectMetadata objectMetadata){
        bucketName = bucketName != null?bucketName:this.bucketName;
        this.getInstance();
        if(objectMetadata == null){
            ossClient.putObject(bucketName,key,inputStream);
        }else{
            ossClient.putObject(bucketName,key,inputStream,objectMetadata);
        }
        this.close();
        return "http://" + bucketName + "." +this.endPoint +"/" + key;
    }

    /**
     * 创建模拟文件夹
     *
     * @param bucketName
     * @param dicName
     * @return
     */
    public String ossCreateDic(String bucketName,String dicName){
        bucketName = bucketName != null?bucketName:this.bucketName;
        this.getInstance();
        ossClient.putObject(bucketName, dicName, new ByteArrayInputStream(new byte[0]));
        this.close();
        return "http://" + bucketName + "." +this.endPoint +"/" + dicName;
    }

    /**
     * 追加上传，适合不停的追加上传的文件
     * 该方法需要自己去调用getInstance，和close方法
     *
     * @param bucketName
     * @param key
     * @param inputStream 要追加的内容
     * @param appendObjectResult 追加的对象结果
     * @return
     */
    public AppendObjectResult ossAppendObjUpload(String bucketName,String key,InputStream inputStream,AppendObjectResult appendObjectResult){
        bucketName = bucketName != null?bucketName:this.bucketName;
        long position = 0L;
        if(appendObjectResult != null){
            position = appendObjectResult.getNextPosition();
        }
        //需要追加的对象
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName,key, inputStream);
        appendObjectRequest.setPosition(position);
        return ossClient.appendObject(appendObjectRequest);
    }

    /**
     * 分片上传
     *
     * 需要支持断点上传
     * 超过100M文件
     *
     * @return
     */
    public InitiateMultipartUploadResult ossBigFileInstance(String bucketName,String key){
        bucketName = bucketName != null?bucketName:this.bucketName;
        //初始化一个上传请求
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName,key);
        //获得OSS服务器的上传回应
        InitiateMultipartUploadResult initiateMultipartUploadResult = ossClient.initiateMultipartUpload(initiateMultipartUploadRequest);
        return initiateMultipartUploadResult;
    }

    /**
     * 分片上传的
     *
     * @param bucketName
     * @param key
     * @param initiateMultipartUploadResult
     * @param uploadId
     * @param inputStream
     * @param partNumber
     * @return
     */
    public PartETag ossBigFileUploadPartETag(String bucketName,String key,InitiateMultipartUploadResult initiateMultipartUploadResult,String uploadId,InputStream inputStream,int partNumber){
        bucketName = bucketName != null?bucketName:this.bucketName;
        uploadId = uploadId == null ? initiateMultipartUploadResult.getUploadId():uploadId;

        UploadPartRequest uploadPartRequest = new UploadPartRequest();
        uploadPartRequest.setBucketName(bucketName);
        uploadPartRequest.setKey(key);
        uploadPartRequest.setUploadId(uploadId);
        uploadPartRequest.setInputStream(inputStream);
        // 设置分片大小，除最后一个分片外，其它分片要大于100KB
        uploadPartRequest.setPartSize(100 * 1024);
        // 设置分片号，范围是1~10000，
        uploadPartRequest.setPartNumber(partNumber);

        UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
        return uploadPartResult.getPartETag();
    }

    /**
     *
     * OSS分片上传的完成，一定要调用
     *
     * @param bucketName
     * @param key
     * @param initiateMultipartUploadResult
     * @param uploadId
     * @param partETags
     * @return
     */
    public CompleteMultipartUploadResult ossBigFileUploadComplete(String bucketName,String key,InitiateMultipartUploadResult initiateMultipartUploadResult,String uploadId,List<PartETag> partETags){
        bucketName = bucketName != null?bucketName:this.bucketName;
        uploadId = uploadId == null ? initiateMultipartUploadResult.getUploadId():uploadId;

        partETags = partETags.stream().sorted((p1,p2) -> p1.getPartNumber() - p2.getPartNumber()).collect(Collectors.toList());

        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
        return ossClient.completeMultipartUpload(completeMultipartUploadRequest);
    }

    /**
     * oss 取消分片上传
     *
     * @param bucketName
     * @param key
     * @param initiateMultipartUploadResult
     * @param uploadId
     */
    public void ossBigFileUploadAbort(String bucketName,String key,InitiateMultipartUploadResult initiateMultipartUploadResult,String uploadId){
        bucketName = bucketName != null?bucketName:this.bucketName;
        uploadId = uploadId == null ? initiateMultipartUploadResult.getUploadId():uploadId;

        AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest(bucketName, key, uploadId);
        ossClient.abortMultipartUpload(abortMultipartUploadRequest);
    }

    /**
     * 流式下载
     *
     * 需要手动关闭，同时一定需要手动将OssObject手动关闭
     *
     * @param bucketName
     * @param key
     * @return
     */
    public OSSObject ossDownloadFile4Stream(String bucketName,String key){
        this.getInstance();
        bucketName = bucketName != null?bucketName:this.bucketName;

        OSSObject ossObject = ossClient.getObject(bucketName,key);
        return ossObject;
    }
}
