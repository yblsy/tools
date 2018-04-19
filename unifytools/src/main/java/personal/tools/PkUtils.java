package personal.tools;/**
 * Created by liuchen on 2018/4/11.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-04-11 16:45
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class PkUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

    /**
     * 生成随机6位数
     * @return
     */
    private static String randomNum(){
        return "" + (int)((Math.random()*9+1) * 100000);
    }

    /**
     * 给base_users生成主键使用
     * @return
     */
    public static String pk4BaseUsers(){
        return sdf.format(new Date()) + randomNum();
    }

}
