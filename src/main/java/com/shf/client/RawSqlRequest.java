package com.shf.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/19 13:52
 */
@Data
@AllArgsConstructor
public class RawSqlRequest {

    /**
     * sql = "select id,name from user where id = ':id'"
     */
    private String sql;
    /**
     * params = "{\"id\":\"uuid\"}"
     */
    private Map<String, Object> params;

}
