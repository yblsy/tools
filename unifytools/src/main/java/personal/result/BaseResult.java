package personal.result;

import java.io.Serializable;

/**
 * @author 刘晨
 * @create 2018-04-13 13:43
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1767327487645930392L;
    public String code;
    public String message;
    private Object data;

    public BaseResult() {
    }

    public BaseResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}