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
    private int pageNum;

    @ApiModelProperty("状态码")
    private int pageSize;

    public int getPageNum() {
        return pageNum == 0 ? 1 : pageNum;
    }

    public int getPageSize() {
        return pageSize == 0 ? 10 :pageSize;
    }
}
