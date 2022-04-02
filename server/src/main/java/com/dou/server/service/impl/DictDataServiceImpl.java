package com.dou.server.service.impl;

import com.dou.server.mapper.DictDataMapper;
import com.dou.server.model.DictData;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.dou.server.service.DictDataService;
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
public class DictDataServiceImpl extends BaseServiceImpl<DictData> implements DictDataService {

    private final DictDataMapper dictDataMapper;

    @Override
    public PageInfo<DictData> getPage(Pagination pagination, DictData temp) {
        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        Example example = new Example(User.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andLike("pid", temp.getPid());
        List<DictData> dictDatas = dictDataMapper.selectByExample(example);
        return new PageInfo<>(dictDatas);
    }
}
