package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.mapper.DictMapper;
import com.dou.server.model.Dict;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.dou.server.service.DictService;
import com.dou.server.sql.ICriteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
        Example example = new Example(User.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andLike("name", temp.getName())
                .andLike("key", temp.getKey());
        List<Dict> dicts = dictMapper.selectByExample(example);
        return new PageInfo<>(dicts);
    }

    @Override
    public void add(Dict temp) throws Exception {
        temp.setCreateUser(User.getRequestUser().getId()).setCreateDate(new Date());
        if (dictMapper.insert(temp) == 0) {
            throw new LogicException("新增失败");
        }
    }

    @Override
    public void update(Dict temp) throws Exception {
        temp.setUpdateUser(User.getRequestUser().getId()).setUpdateDate(new Date());
        if (dictMapper.updateByPrimaryKeySelective(temp) == 0) {
            throw new LogicException("修改失败");
        }
    }

    @Override
    public void delete(List<?> ids) throws LogicException {
        Example example = new Example(User.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andIn("id",ids);
        if (dictMapper.deleteByExample(example) != ids.size()) {
            throw new LogicException("删除失败");
        }
    }
}
