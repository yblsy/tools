package personal.enums;/**
 * Created by liuchen on 2018/5/21.
 */

/**
 * @author 刘晨
 * @create 2018-05-21 16:11
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public enum IsDeletedEnum {

    DELETED(0,"未删除"),
    HAS_DELETED(1,"已删除"),
    IN_DELETED(2,"删除中"),
    ;

    private Integer code;

    private String value;

    IsDeletedEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
