package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
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

import java.util.Collection;
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

    @Override
    public void add(Dict temp) throws Exception {
        if (dictMapper.selectCount(new Dict().setSign(temp.getSign())) > 0) {
            throw new LogicException("标识已存在");
        }
        super.add(temp);
    }

    @Override
    public void update(Dict temp) throws Exception {
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
    public void delete(Collection<?> ids) throws LogicException {
        super.delete(ids);
    }
}