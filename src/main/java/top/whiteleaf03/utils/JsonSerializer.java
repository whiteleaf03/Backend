package top.whiteleaf03.utils;

import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author WhiteLeaf03
 */
public class JsonSerializer<T> implements RedisSerializer<T> {
    private Class<T> clazz;

    public JsonSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        System.out.println(JSONUtil.toJsonStr(t));
        return JSONUtil.toJsonStr(t).getBytes();
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        String jsonStr = new String(bytes);
        return JSONUtil.toBean(jsonStr, clazz);
    }
}
