package com.dou.server.service;

import com.dou.server.model.Notice;

import java.util.List;

/**
 * @author yangjd
 */
public interface NoticeService extends BaseService<Notice> {

    List<Notice> getList(Notice temp);
}