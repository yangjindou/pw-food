package com.dou.server.service.impl;

import com.dou.server.mapper.NoticeMapper;
import com.dou.server.model.Dict;
import com.dou.server.model.Notice;
import com.dou.server.model.Pagination;
import com.dou.server.service.NoticeService;
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
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public PageInfo<?> getPage(Pagination pagination, Notice temp) {
        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        Example example = new Example(Dict.class);
        ICriteria criteria = new ICriteria(example);
        List<Notice> list = noticeMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}