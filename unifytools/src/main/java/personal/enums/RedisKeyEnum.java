package personal.enums;/**
 * Created by liuchen on 2018/5/22.
 */

/**
 * @author 刘晨
 * @create 2018-05-22 15:09
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public enum RedisKeyEnum {

    IV_MENU("interview_menu","interview菜单"),
    ;

    private String code;

    private String value;

    RedisKeyEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
