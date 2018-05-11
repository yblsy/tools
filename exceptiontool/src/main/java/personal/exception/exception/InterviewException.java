package personal.exception.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 刘晨
 * @create 2018-05-06 21:43
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class InterviewException extends RuntimeException {

    private static final long serialVersionUID = -266612837821531645L;

    private String errorCode;
    private String errorMsg;
    private Throwable cause;

    public InterviewException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public InterviewException(String errorCode, String errorMsg, Throwable cause) {
        this(errorCode, errorMsg);
        this.cause = cause;
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
