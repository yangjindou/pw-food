package com.dou.server.service;

import com.dou.server.model.Dept;
import com.dou.server.tag.ResultEnums;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangjd
 */
public interface DeptService {

    /**
     * 获取分页
     * @param page 页数
     * @param limit 页量
     * @param temp 实体类
     * @return 分页查询
     */
    PageInfo<?> get(Integer page, Integer limit, Dept temp);

    /**
     * 添加
     * @param temp 实体类
     * @return 状态码
     * @throws Exception 异常处理
     */
    ResultEnums add(Dept temp) throws Exception;

    /**
     * 修改
     * @param temp 实体类
     * @return 状态码
     * @throws Exception 异常处理
     */
    ResultEnums update(Dept temp) throws Exception;

    /**
     * 删除
     * @param ids 多条id
     * @return 状态码
     */
    ResultEnums delete(List<Integer> ids);
}
