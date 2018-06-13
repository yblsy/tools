package personal.exception.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 刘晨
 * @create 2018-05-06 21:43
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class InterviewException extends ApplicationException {

    private static final long serialVersionUID = -266612837821531645L;

    public InterviewException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public InterviewException(String errorCode, String errorMsg, Throwable cause) {
        this(errorCode, errorMsg);
        this.cause = cause;
    }
}
