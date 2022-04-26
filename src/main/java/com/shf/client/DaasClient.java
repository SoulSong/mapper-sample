package com.shf.client;

import com.shf.vo.BaseDaasVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/19 13:27
 */
public interface DaasClient {

    <T extends BaseDaasVo, ID extends Serializable> List<T> selectListByIds(Class<T> voClass, List<ID> ids);

    <T extends BaseDaasVo, ID extends Serializable> List<T> selectListByIds(Class<T> voClass, List<ID> ids, List<String> projections);

    List<Map<String, Object>> selectList(RawSqlRequest rawSqlRequest);

}
