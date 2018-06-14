package personal.exception.check;

import personal.exception.exception.ApplicationException;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author 刘晨
 * @create 2018-06-13 15:20
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class ApplicationCheck {

    /**
     * 判断值是否为空
     *
     * @param value
     * @param applicationException
     * @param consumer
     * @param t
     * @return
     */
    public static <T> boolean checkApplication4NullOrEmpty(Object value, ApplicationException applicationException, Consumer<T> consumer, T t){
        return checkApplication4NullOrEmpty(value,val -> val == null || val.equals(""),applicationException,consumer,t);
    }

    public static <T,P> boolean checkApplication4NullOrEmpty(P p,Predicate<P> predicate,ApplicationException applicationException, Consumer<T> consumer, T t){
        if(predicate.test(p)){
            throw applicationException;
        }

        //如果consumer不为空的话，可能针对该实体做一下别的操作
        if(consumer != null){
            consumer.accept(t);
        }

        return true;
    }

    public static boolean checkApplication4NullOrEmpty(Object value,ApplicationException applicationException){
        return checkApplication4NullOrEmpty(value,applicationException,null,null);
    }

    /**
     * 比较两个值是否相同
     * @param value01
     * @param value02
     * @param applicationException
     * @param consumerT 可能做的一些额外操作
     * @param t 对象值
     * @param consumerP
     * @param p
     * @param <T>
     * @param <P>
     * @return
     */
    public static <T,P> boolean compareElements(Object value01,Object value02, ApplicationException applicationException,Consumer<T> consumerT,T t,Consumer<P> consumerP,P p){
        checkApplication4NullOrEmpty(value01,applicationException);
        checkApplication4NullOrEmpty(value02,applicationException);

        if(!value01.equals(value02)){
            throw applicationException;
        }

        if(consumerT != null){
            consumerT.accept(t);
        }

        if(consumerP != null){
            consumerP.accept(p);
        }

        return true;
    }

    public static boolean compareElements(Object value01,Object value02,ApplicationException applicationException){
        return compareElements(value01,value02,applicationException,null,null,null,null);
    }
}
