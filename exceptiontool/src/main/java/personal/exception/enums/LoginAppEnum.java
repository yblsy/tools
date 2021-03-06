package personal.exception.enums;

/**
 * @author 刘晨
 * @create 2018-04-11 22:28
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public enum LoginAppEnum {

    REGISTER_ERROR_USER_EXIST("LA_ERROR_000001","未能连接到ftp服务器，可能是用户名或密码错误"),
    ;

    private String errorCode;

    private String errorMsg;

    LoginAppEnum(String errorCode,String errorMsg){
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
