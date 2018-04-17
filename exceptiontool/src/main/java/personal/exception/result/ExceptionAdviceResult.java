package personal.exception.result;

import personal.result.BaseResult;

/**
 * @author 刘晨
 * @create 2018-04-17 15:25
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class ExceptionAdviceResult extends BaseResult {

    public ExceptionAdviceResult(){

    }

    public ExceptionAdviceResult(String errorCode,String errorMessage){
        super(errorCode,errorMessage,null);
    }

    public ExceptionAdviceResult(String errorCode,String errorMessage,Throwable throwable,String cause){
        super(errorCode,errorMessage,null);
        this.throwable = throwable;
        this.cause = cause;
    }

    private Throwable throwable;

    private String cause;

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
