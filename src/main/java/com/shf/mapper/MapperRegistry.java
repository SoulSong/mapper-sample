package com.shf.mapper;

import com.shf.client.DaasClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description :
 * 注册每个mapper接口对应的{@link MapperProxyFactory}，通过MapperProxyFactory获取mapper代理对象。
 *
 * @author songhaifeng
 * @date 2022/4/22 15:59
 */
public class MapperRegistry {

    private final Map<Class<? extends DaasMapper>, MapperProxyFactory<? extends DaasMapper>> knownMappers = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends DaasMapper> T getMapper(Class<T> mapperInterface, DaasClient daasClient) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(mapperInterface);
        if (mapperProxyFactory == null) {
            throw new IllegalArgumentException("Type " + mapperInterface + " is not known to the MapperRegistry.");
        }
        try {
            // 通过MapperProxyFactory获取mapper代理对象
            return mapperProxyFactory.newInstance(daasClient);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T extends DaasMapper> boolean hasMapper(Class<T> mapperInterface) {
        return knownMappers.containsKey(mapperInterface);
    }

    public <T extends DaasMapper> void addMapper(Class<T> mapperInterface) {
        if (mapperInterface.isInterface()) {
            if (hasMapper(mapperInterface)) {
                throw new IllegalArgumentException("Type " + mapperInterface + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(mapperInterface, new MapperProxyFactory<>(mapperInterface));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(mapperInterface);
                }
            }
        }
    }

}
