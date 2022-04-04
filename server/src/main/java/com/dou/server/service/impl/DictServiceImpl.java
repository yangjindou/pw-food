package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.mapper.DictMapper;
import com.dou.server.model.Dict;
import com.dou.server.model.DictData;
import com.dou.server.service.DictDataService;
import com.dou.server.service.DictService;
import com.dou.server.sql.ICriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    private final DictMapper dictMapper;
    private final DictDataService dictDataService;

    @Override
    public List<Dict> getList(Dict temp) {
        Example example = new Example(Dict.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andLike("name", temp.getName())
            .andLike("sign", temp.getSign());
        return dictMapper.selectByExample(example);
    }

    @Override
    public void add(Dict temp) {
        if (dictMapper.selectCount(new Dict().setSign(temp.getSign())) > 0) {
            throw new LogicException("标识已存在");
        }
        super.add(temp);
    }

    @Override
    public void update(Dict temp) {
        Dict dict = dictMapper.selectOne(new Dict().setId(temp.getId()));
        if (dict == null) {
            throw new LogicException("没有数据");
        }
        if (!dict.getSign().equals(temp.getSign())) {
            if (dictMapper.selectCount(new Dict().setSign(temp.getSign())) > 0) {
                throw new LogicException("标识已存在");
            }
        }
        super.update(temp);
    }

    @Override
    public void delete(Collection<?> ids) {
        Set<Integer> sonIds = new HashSet<>();
        ids.forEach(id -> {
            List<DictData> list = dictDataService.getList(new DictData().setPid(Integer.parseInt(id.toString())));
            Set<Integer> listIds = list.stream().map(DictData::getId).collect(Collectors.toSet());
            sonIds.addAll(listIds);
        });
        if (sonIds.size() != 0) {
            dictDataService.delete(sonIds);
        }
        super.delete(ids);
    }
}