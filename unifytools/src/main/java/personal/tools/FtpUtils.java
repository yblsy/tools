package personal.tools;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import personal.exception.enums.UnifyToolsEnum;
import personal.exception.exception.UnifyToolsException;

import java.io.*;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 刘晨
 * @create 2018-04-18 14:29
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Setter
@Slf4j
public class FtpUtils {

    public FtpUtils(String ftpHost,String ftpUserName,String ftpPassword,int ftpPort){
        this.ftpHost = ftpHost;
        this.ftpUserName = ftpUserName;
        this.ftpPassword = ftpPassword;
        this.ftpPort = ftpPort;
    }

    //ftp url
    private String ftpHost;
    //ftp username
    private String ftpUserName;
    //ftp password
    private String ftpPassword;
    //ftp port
    private int ftpPort;

    //需要实例化的ftp对象
    private FTPClient ftpClient;

    //字节码
    private static final String ENCODEING_UTF_8 = "UTF-8";

    /**
     * 初始化ftp服务器对象
     *
     * @return
     */
    public void instanceFtpClient() throws IOException{
        FTPClient ftpClient = new FTPClient();
        //连接FTP服务器
        ftpClient.connect(this.ftpHost,this.ftpPort);
        //登录
        ftpClient.login(ftpUserName,ftpPassword);
        if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
            ftpClient.disconnect();
            throw new UnifyToolsException(UnifyToolsEnum.UT_FTP_ERROR_NO_CONNECT_WITH_USERNAME_OR_PASSWORD.getErrorCode(),UnifyToolsEnum.UT_FTP_ERROR_NO_CONNECT_WITH_USERNAME_OR_PASSWORD.getErrorMsg());
        }
        //log.info("success connect to ftp!");
        this.ftpClient = ftpClient;
    }

    /**
     * 文件下载
     * @param ftpPath
     * @param fileName
     * @return
     */
    public void download(String ftpPath, String fileName) throws IOException{
        //处理文件路径
        ftpClient.setControlEncoding(ENCODEING_UTF_8);
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalActiveMode();
        ftpClient.changeWorkingDirectory(ftpPath);

        //输出ftpClient
        Optional<FTPFile> ftpFileOptional = Stream.of(ftpClient.listFiles()).filter(ftpFile -> ftpFile.getName().equals(fileName)).findAny();
        if(ftpFileOptional.isPresent()){
            InputStream is = ftpClient.retrieveFileStream(ftpFileOptional.get().getName());
            ftpClient.completePendingCommand();
            is.close();
        }else{
            throw new UnifyToolsException(UnifyToolsEnum.UT_FTP_ERROR_FILE_NOT_FOUND.getErrorCode(),UnifyToolsEnum.UT_FTP_ERROR_FILE_NOT_FOUND.getErrorMsg());
        }
        ftpClient.logout();
    }

    public boolean upload(String ftpPath, String fileName,InputStream inputStream) throws IOException{
        ftpClient.setControlEncoding("UTF-8"); // 中文支持
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        //切换目录
        if(!ftpClient.changeWorkingDirectory(ftpPath)){
            ftpClient.makeDirectory(ftpPath);
            ftpClient.changeWorkingDirectory(ftpPath);
        }

        ftpClient.storeFile(fileName, inputStream);
        inputStream.close();
        ftpClient.logout();

        return true;
    }


}
