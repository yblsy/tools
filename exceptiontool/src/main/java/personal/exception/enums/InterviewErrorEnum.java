package personal.exception.enums;/**
 * Created by liuchen on 2018/5/9.
 */

/**
 * @author 刘晨
 * @create 2018-05-09 15:26
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public enum InterviewErrorEnum {

    //项目前缀_表_状态_值
    INTER_BC_ER_000001("BC_ERROR_000001","没有查询到相应的父节点"),
    INTER_BC_ER_000002("BC_ERROR_000002","更新父节点失败"),
    INTER_BC_ER_000003("BC_ERROR_000003","新增节点失败"),
    INTER_BC_ER_000004("BC_ERROR_000004","节点名称不可以为空"),
    INTER_BC_ER_000005("BC_ERROR_000005","删除节点失败"),
    ;

    private String code;

    private String value;

    InterviewErrorEnum(String code, String value){
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
