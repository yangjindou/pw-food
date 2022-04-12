package com.dou.server.service;

import com.dou.server.model.DisinfectionRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
public interface DisinfectionRecordService extends BaseService<DisinfectionRecord> {

    List<DisinfectionRecord> getList(DisinfectionRecord temp);

    ByteArrayOutputStream export(DisinfectionRecord temp) throws IOException;
}