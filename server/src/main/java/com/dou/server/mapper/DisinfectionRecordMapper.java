package com.dou.server.mapper;

import com.dou.server.model.DisinfectionRecord;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface DisinfectionRecordMapper extends MyMapper<DisinfectionRecord> {

    List<DisinfectionRecord> getList(DisinfectionRecord temp);
}