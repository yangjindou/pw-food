package com.dou.server.service.impl;

import com.dou.server.mapper.NoticeMapper;
import com.dou.server.model.Dict;
import com.dou.server.model.Notice;
import com.dou.server.service.NoticeService;
import com.dou.server.sql.ICriteria;
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
    public List<Notice> getList(Notice temp) {
        Example example = new Example(Dict.class);
        ICriteria criteria = new ICriteria(example);
        return noticeMapper.selectByExample(example);
    }
}