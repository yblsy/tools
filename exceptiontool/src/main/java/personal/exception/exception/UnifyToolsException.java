package personal.exception.exception;/**
 * Created by liuchen on 2018/4/18.
 */

import personal.exception.enums.UnifyToolsEnum;

/**
 * @author 刘晨
 * @create 2018-04-18 15:05
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class UnifyToolsException extends RuntimeException{

    private static final long serialVersionUID = 682495844098760822L;

    private String errorCode;

    private String errorMsg;

    private Throwable cause;

    public UnifyToolsException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public UnifyToolsException(String errorCode, String errorMsg, Throwable cause) {
        this(errorCode,errorMsg);
        this.cause = cause;
    }

    public UnifyToolsException(UnifyToolsEnum unifyToolsEnum){
        this.errorCode = unifyToolsEnum.getErrorCode();
        this.errorMsg = unifyToolsEnum.getErrorMsg();
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
