package personal.annotation;

import java.lang.annotation.*;

/**
 * @author 刘晨
 * @create 2018-04-16 21:21
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseResultAnnotation {

}
