package personal.configurs;/**
 * Created by liuchen on 2018/4/18.
 */

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import personal.tools.DESUtils;

import java.util.stream.Stream;

/**
 * @author 刘晨
 * @create 2018-04-18 10:36
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if(isEncryptProp(propertyName)){
            return DESUtils.getDecryptString(propertyValue);
        }else{
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String propertyName){
        return Stream.of(encryptPropNames).anyMatch(str -> str.equals(propertyName));
    }
}
