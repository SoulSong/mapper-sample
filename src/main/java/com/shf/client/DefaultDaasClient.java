package com.shf.client;

import com.shf.vo.BaseDaasVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/20 10:57
 */
public class DefaultDaasClient implements DaasClient {

    private String daasAddress;

    public DefaultDaasClient(String daasAddress) {
        this.daasAddress = daasAddress;
    }

    @Override
    public <T extends BaseDaasVo, ID extends Serializable> List<T> selectListByIds(Class<T> voClass, List<ID> ids) {
        return new ArrayList<>();
    }

    @Override
    public <T extends BaseDaasVo, ID extends Serializable> List<T> selectListByIds(Class<T> voClass, List<ID> ids, List<String> projections) {
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> selectList(RawSqlRequest rawSqlRequest) {
        return new ArrayList<>();
    }
}
