package com.dou.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yangjindou
 * @date 2022-03-29
 */
@Data
@AllArgsConstructor
public class Pagination {

    @ApiModelProperty("状态码")
    private Integer pageNum;

    @ApiModelProperty("状态码")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 :pageSize;
    }
}
