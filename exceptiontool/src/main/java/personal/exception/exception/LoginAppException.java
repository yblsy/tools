package personal.exception.exception;

import personal.exception.enums.LoginAppEnum;

/**
 * @author 刘晨
 * @create 2018-04-11 21:46
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class LoginAppException extends ApplicationException {

    private static final long serialVersionUID = 8151091752866096602L;

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
}
