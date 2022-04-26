package com.shf.mapper;

import com.shf.vo.BaseDaasVo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/21 20:37
 */
@Slf4j
public class MapperHelper {

    /**
     * 获取Mapper泛型中具体类型
     *
     * @param mapperInterface mapper接口
     * @param <M>             mapper类型
     * @param <V>             vo类型
     * @return VO的实现类
     */
    @SuppressWarnings("unchecked")
    public static <M extends DaasMapper, V extends BaseDaasVo> Class<V> currentVoClass(Class<M> mapperInterface) {
        if (!mapperInterface.isInterface()) {
            throw new IllegalArgumentException("mapperInterface must be a interface.");
        }
        ParameterizedType genericInterface = (ParameterizedType) mapperInterface.getGenericInterfaces()[0];
        Class targetClass = (Class) genericInterface.getRawType();
        log.info("{} -> {}", targetClass.getTypeParameters()[0], genericInterface.getActualTypeArguments()[0]);
        return (Class<V>) genericInterface.getActualTypeArguments()[0];
    }
}
