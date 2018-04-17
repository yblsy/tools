package personal.exception.exception;

import personal.exception.enums.LoginAppEnum;

/**
 * @author 刘晨
 * @create 2018-04-11 21:46
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class LoginAppException extends RuntimeException {

    private static final long serialVersionUID = 8151091752866096602L;

    private String errorCode;

    private String errorMsg;

    private Throwable cause;

    public LoginAppException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public LoginAppException(String errorCode, String errorMsg, Throwable cause) {
        this(errorCode,errorMsg);
        this.cause = cause;
    }

    public LoginAppException(LoginAppEnum loginAppEnum){
        this.errorCode = loginAppEnum.getErrorCode();
        this.errorMsg = loginAppEnum.getErrorMsg();
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

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
