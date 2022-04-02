package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.mapper.DictMapper;
import com.dou.server.mapper.UserMapper;
import com.dou.server.model.Dict;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.dou.server.service.DictService;
import com.dou.server.service.UserService;
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
    private final UserMapper userMapper;
    private final UserService userService;

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
}
