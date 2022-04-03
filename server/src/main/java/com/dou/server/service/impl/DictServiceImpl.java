package com.dou.server.service.impl;

import com.dou.server.mapper.DictMapper;
import com.dou.server.model.Dict;
import com.dou.server.model.Pagination;
import com.dou.server.service.DictService;
import com.dou.server.sql.ICriteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    private final DictMapper dictMapper;

    @Override
    public PageInfo<Dict> getPage(Pagination pagination, Dict temp) {
        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        Example example = new Example(Dict.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andLike("name", temp.getName())
                .andLike("sign", temp.getSign());
        List<Dict> dicts = dictMapper.selectByExample(example);
        return new PageInfo<>(dicts);
    }
}