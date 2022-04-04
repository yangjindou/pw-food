package com.dou.server.service.impl;

import com.dou.server.mapper.NoticeMapper;
import com.dou.server.model.Notice;
import com.dou.server.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return noticeMapper.getList(temp);
    }
}