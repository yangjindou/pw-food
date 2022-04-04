package com.dou.server.service;

import com.dou.server.model.Notice;
import com.dou.server.model.Pagination;
import com.github.pagehelper.PageInfo;

/**
 * @author yangjd
 */
public interface NoticeService extends BaseService<Notice> {

    PageInfo<?> getPage(Pagination pagination, Notice temp);
}