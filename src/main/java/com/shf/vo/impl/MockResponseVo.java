package com.shf.vo.impl;

import com.shf.annotation.DaasId;
import com.shf.annotation.DaasTable;
import com.shf.vo.BaseDaasVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/18 15:10
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DaasTable(tableName = "ads_mock_patent_data")
public final class MockResponseVo implements BaseDaasVo {
    @DaasId
    private Integer patentId;
    private String name;
    private List<String> address;
    private Map<String, Object> attributes;
    private List<MockDetail> details;
    private String hiddenField;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MockDetail {
        private String desc;
    }
}
