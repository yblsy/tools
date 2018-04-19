package personal.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘晨
 * @create 2018-04-07 20:49
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

@Component
public class RedisUtils {

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private static final long REDIS_DEFAULT_EXPIRE = 600L;

    private static final TimeUnit REDIS_DEFAULT_EXPIRE_TYPE = TimeUnit.SECONDS;


    private void setDefaultExpire(String key){
        redisTemplate.expire(key,REDIS_DEFAULT_EXPIRE,REDIS_DEFAULT_EXPIRE_TYPE);
    }
    /**
     * 向redis里面塞值
     * @param key
     * @param value
     */
    public void setRedis(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
        this.setDefaultExpire(key);
    }

    public void setRedis(String key,Object value,long expireTime,TimeUnit expireType){
        redisTemplate.opsForValue().set(key,value);
        if(expireTime > 0){
            redisTemplate.expire(key,expireTime,expireType);
        }
    }

    public Object getRedisValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public Object getRedisValue7ResetTime(String key){
        Object value = redisTemplate.opsForValue().get(key);
        if(value != null){
            this.setDefaultExpire(key);
        }
        return value;
    }

    /**
     * 添加list到Redis的头部或尾部
     *
     * @param key
     * @param value
     */
    public void setRedisListRight(String key,List<Object> value){
        redisTemplate.opsForList().rightPush(key,value);
        this.setDefaultExpire(key);
    }

    public void setRedisListRight(String key,List<Object> value,long expireTime,TimeUnit expireType){
        redisTemplate.opsForList().rightPush(key,value);
        if(expireTime > 0){
            redisTemplate.expire(key,expireTime,expireType);
        }
    }

    public void setRedisListLeft(String key,List<Object> value){
        redisTemplate.opsForList().leftPush(key,value);
        this.setDefaultExpire(key);
    }

    public void setRedisListLeft(String key,List<Object> value,long expireTime,TimeUnit expireType){
        redisTemplate.opsForList().leftPush(key,value);
        if(expireTime > 0){
            redisTemplate.expire(key,expireTime,expireType);
        }
    }

    public List getRedisList(String key){
        long size = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key,0L,size);
    }

    public List getRedisList7Reset(String key){
        List result =  this.getRedisList(key);
        this.setDefaultExpire(key);
        return result;
    }
}
