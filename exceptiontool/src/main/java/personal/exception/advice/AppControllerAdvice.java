package personal.exception.advice;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.exception.exception.InterviewException;
import personal.exception.exception.LoginAppException;
import personal.exception.result.ExceptionAdviceResult;

/**
 * @author 刘晨
 * @create 2018-04-17 15:10
 * 异常统一处理通知类
 **/
@ControllerAdvice
public class AppControllerAdvice {

    private Gson gson = new Gson();

    @ExceptionHandler(LoginAppException.class)
    @ResponseBody
    public String handleLoginAppException(LoginAppException exception){
        ExceptionAdviceResult exceptionAdviceResult = new ExceptionAdviceResult(exception.getErrorCode(),exception.getErrorMsg(),exception.getCause(),exception.getMessage());
        return gson.toJson(exceptionAdviceResult);
    }

    @ExceptionHandler(InterviewException.class)
    @ResponseBody
    public ExceptionAdviceResult handleInterviewAppException(InterviewException exception){
        ExceptionAdviceResult exceptionAdviceResult = new ExceptionAdviceResult(exception.getErrorCode(),exception.getErrorMsg(),exception.getCause(),exception.getMessage());
        return exceptionAdviceResult;
    }
}
