package com.shf.mapper;

import com.shf.client.DaasClient;
import com.shf.client.DaasClientBuilder;
import com.shf.mapper.impl.UserMapper;
import com.shf.vo.impl.MockResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/21 20:22
 */
@Slf4j
public class MapperTest {

    @Test
    public void mapperTest() {
        DaasClient daasClient = DaasClientBuilder.create()
                .withDaasAddress("http://***")
                .build();
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMapper(UserMapper.class);
        UserMapper userMapper = mapperRegistry.getMapper(UserMapper.class, daasClient);
        List<MockResponseVo> responseVos = userMapper.selectVoList(Arrays.asList(1, 2, 3), "foo");
        log.info("{}", responseVos);
        List<Map<String, Object>> responseVos2 = userMapper.selectMapList(Arrays.asList("1", "2", "3"), "foo");
        log.info("{}", responseVos2);
    }

}
