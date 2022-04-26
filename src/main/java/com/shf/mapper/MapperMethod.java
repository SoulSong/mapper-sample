package com.shf.mapper;

import com.google.common.collect.ImmutableMap;
import com.shf.client.DaasClient;
import com.shf.vo.BaseDaasVo;
import com.shf.vo.impl.MockResponseVo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/22 9:48
 */
@Slf4j
public class MapperMethod<T extends DaasMapper> {

    private final Class<T> mapperInterface;
    private final Method method;

    public MapperMethod(Class<T> mapperInterface, Method method) {
        this.mapperInterface = mapperInterface;
        this.method = method;
    }

    /**
     * 最终执行被invoke的method
     *
     * @param args       参数列表
     * @param daasClient daasClient
     * @return object
     */
    public Object execute(Object[] args, DaasClient daasClient) {
        // 获取当前执行mapper接口对应的实体泛型类型
        Class<? extends BaseDaasVo> voClass = MapperHelper.currentVoClass(mapperInterface);
        log.info("Current voClass is {}, current method is {}, it's parameters are {} and values are {}", voClass.getName(), method.getName(), method.getParameters(), args);
//        daasClient.selectList(new RawSqlRequest());
        // 根据被invoke方法的返回类型mock返回值
        Type type = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
        if (type.getTypeName().equals(MockResponseVo.class.getName())) {
            return Collections.singletonList(MockResponseVo.builder().name("a").build());
        } else {
            return Collections.singletonList(ImmutableMap.of(1, 2));
        }
    }
}
