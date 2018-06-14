package personal.exception.exception;/**
 * Created by liuchen on 2018/6/13.
 */

import lombok.Getter;
import lombok.Setter;

/**
 * \
 *
 * @author 刘晨
 * @create 2018-06-13 15:46
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Getter
@Setter
public class ApplicationException extends RuntimeException{

    private static final long serialVersionUID = -2576624597263158432L;

    protected String errorCode;
    protected String errorMsg;
    protected Throwable cause;
}
