package com.shf.mapper.impl;

import com.shf.mapper.DaasMapper;
import com.shf.vo.impl.MockResponseVo;

import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/21 20:20
 */
public interface UserMapper extends DaasMapper<MockResponseVo> {

    List<MockResponseVo> selectVoList(List<Integer> ids, String name);

    List<Map<String, Object>> selectMapList(List<String> ids, String name);

}
