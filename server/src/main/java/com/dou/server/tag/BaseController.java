package com.dou.server.tag;

import com.dou.server.model.ResultEntity;
import com.github.pagehelper.PageInfo;

/**
 * 基础控制层类
 * 所有controller都实现他
 * @author yangjd
 * @date 2021-05-24
 */
public interface BaseController {

    /**
     * 普通成功返回
     * @return ResultEntity
     */
    default ResultEntity success() {
        return new ResultEntity(ResultEnums.SUCCESS);
    }

    /**
     * 带有对象的成功返回
     * @param data 返回对象
     * @return ResultEntity
     */
    default ResultEntity success(Object data) {
        return new ResultEntity(ResultEnums.SUCCESS,data);
    }

    /**
     * 普通错误返回
     * @return ResultEntity
     */
    default ResultEntity error() {
        return new ResultEntity(ResultEnums.ERROR);
    }

    /**
     * 带有状态码的错误返回
     * @param resultEnums ResultEnums
     * @return ResultEntity
     */
    default ResultEntity error(ResultEnums resultEnums) {
        return new ResultEntity(resultEnums);
    }

    /**
     * 根据状态码返回
     * @param resultEnums ResultEnums
     * @return resultEntity
     */
    default ResultEntity setResult(ResultEnums resultEnums) {
        return new ResultEntity(resultEnums);
    }

    /**
     * 分页返回
     * @param pageInfo 分页对象PageInfo
     * @return ResultEntity
     */
    default ResultEntity setPage(PageInfo<?> pageInfo) {
        return new ResultEntity(ResultEnums.CODE_PAGE,"请求成功",(int) pageInfo.getTotal(),pageInfo.getList());
    }
}