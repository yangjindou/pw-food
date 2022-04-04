package com.dou.server.mapper;

import com.dou.server.model.Notice;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface NoticeMapper extends MyMapper<Notice> {

    List<Notice> getList(Notice temp);
}