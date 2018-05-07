package personal.tools;

/**
 * @author 刘晨
 * @create 2018-05-07 20:13
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class ObjectToolUtils {

    /**
     * @param param1
     * @param param2
     * @param <T>
     * @return
     */
    public static <T> T oneNull4Two(T param1,T param2){
        if(param1 == null) {
            return param2;
        }
        return param1;
    }
}
