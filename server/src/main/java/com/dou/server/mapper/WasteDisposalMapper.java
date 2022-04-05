package com.dou.server.mapper;

import com.dou.server.model.WasteDisposal;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface WasteDisposalMapper extends MyMapper<WasteDisposal> {

    List<WasteDisposal> getList(WasteDisposal temp);
}