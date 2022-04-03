package com.dou.server.service.impl;

import com.dou.server.mapper.DictDataMapper;
import com.dou.server.model.DictData;
import com.dou.server.model.Pagination;
import com.dou.server.service.DictDataService;
import com.dou.server.sql.ICriteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
        Example example = new Example(DictData.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andEqualTo("pid", temp.getPid());
        example.orderBy("sort").asc();
        List<DictData> dictDatas = dictDataMapper.selectByExample(example);
        return new PageInfo<>(dictDatas);
    }

    @Override
    public void add(List<DictData> dictDatas) throws Exception {
        // 先删除多余的
        List<DictData> list = super.getList(new DictData().setPid(dictDatas.get(0).getPid()));
        Set<Integer> formSet = dictDatas.stream().map(DictData::getId).filter(Objects::nonNull).collect(Collectors.toSet());
        List<Integer> deleteIds = list.stream().map(DictData::getId).filter(id -> !formSet.contains(id)).collect(Collectors.toList());
        if (deleteIds.size() != 0) {
            super.delete(deleteIds);
        }
        for (DictData dictData : dictDatas) {
            if (dictData.getId() == null) {
                super.add(dictData);
            } else {
                super.update(dictData);
            }
        }
    }
}
