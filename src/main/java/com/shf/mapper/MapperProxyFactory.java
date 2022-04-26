package com.shf.mapper;

import com.shf.client.DaasClient;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/22 9:40
 */
public class MapperProxyFactory<T extends DaasMapper> {
    private final Class<T> mapperInterface;
    /**
     * 缓存当前mapper接口中每个method对应的invoker
     */
    private final Map<Method, MapperProxy.MapperMethodInvoker> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public Map<Method, MapperProxy.MapperMethodInvoker> getMethodCache() {
        return methodCache;
    }

    /**
     * 获取实例化代理对象
     *
     * @param mapperProxy mapperProxy
     * @return
     */
    @SuppressWarnings("unchecked")
    private T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

    public T newInstance(DaasClient daasClient) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(daasClient, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }
}
