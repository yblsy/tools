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
    //BaseClass
    INTER_BC_ER_000001("BC_ERROR_000001","没有查询到相应的父节点"),
    INTER_BC_ER_000002("BC_ERROR_000002","更新父节点失败"),
    INTER_BC_ER_000003("BC_ERROR_000003","新增节点失败"),
    INTER_BC_ER_000004("BC_ERROR_000004","节点名称不可以为空"),
    INTER_BC_ER_000005("BC_ERROR_000005","删除节点失败"),
    INTER_BC_ER_000006("BC_ERROR_000006","更新节点失败"),

    //BaseResources
    INTER_BR_ER_000001("INTER_BR_ER_000001","新增资源失败"),
    INTER_BR_ER_000002("INTER_BR_ER_000002","更新资源失败"),
    INTER_BR_ER_000003("INTER_BR_ER_000003","删除资源失败"),
    INTER_BR_ER_000004("INTER_BR_ER_000004","资源名称不可为空"),
    INTER_BR_ER_000005("INTER_BR_ER_000005","至少填写一个关键词"),
    INTER_BR_ER_000006("INTER_BR_ER_000006","资源Url不可为空"),
    INTER_BR_ER_000007("INTER_BR_ER_000007","资源Id不可为空"),
    INTER_BR_ER_000008("INTER_BR_ER_000008","查询不到资源"),
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
