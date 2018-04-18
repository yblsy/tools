package personal.exception.enums;/**
 * Created by liuchen on 2018/4/18.
 */

/**
 * @author 刘晨
 * @create 2018-04-18 15:08
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public enum UnifyToolsEnum {

    UT_FTP_ERROR_NO_CONNECT_WITH_USERNAME_OR_PASSWORD("UT_ERROR_000001","用户名已存在，请检查"),
    UT_FTP_ERROR_FILE_NOT_FOUND("UT_ERROR_000002","FTP上文件不存在，请检查"),
    ;

    private String errorCode;

    private String errorMsg;

    UnifyToolsEnum(String errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
