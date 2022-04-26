package com.shf.mapper;

import com.shf.client.DaasClient;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/22 9:45
 */
public class MapperProxy<T extends DaasMapper> implements InvocationHandler, Serializable {

    private final DaasClient daasClient;
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethodInvoker> methodCache;

    public MapperProxy(DaasClient daasClient, Class<T> mapperInterface, Map<Method, MapperMethodInvoker> methodCache) {
        this.daasClient = daasClient;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return cachedInvoker(method).invoke(proxy, method, args, daasClient);
        }
    }

    /**
     * 构造并缓存每个method对一个{@link MapperMethodInvoker}实例
     *
     * @param method method
     * @return MapperMethodInvoker
     * @throws Throwable t
     */
    private MapperMethodInvoker cachedInvoker(Method method) throws Throwable {
        try {
            // 获取当前method对应的invoker，如果不存在则实例化并新增至缓存中
            return methodCache.computeIfAbsent(method, m -> new PlainMethodInvoker(new MapperMethod<>(mapperInterface, method)));
        } catch (RuntimeException re) {
            Throwable cause = re.getCause();
            throw cause == null ? re : cause;
        }
    }

    interface MapperMethodInvoker {
        Object invoke(Object proxy, Method method, Object[] args, DaasClient daasClient) throws Throwable;
    }

    private static class PlainMethodInvoker implements MapperMethodInvoker {
        private MapperMethod mapperMethod;

        public PlainMethodInvoker(MapperMethod mapperMethod) {
            this.mapperMethod = mapperMethod;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args, DaasClient daasClient) throws Throwable {
            return mapperMethod.execute(args, daasClient);
        }
    }
}
