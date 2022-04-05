package com.dou.server.service;

import com.dou.server.model.WasteDisposal;

import java.util.List;

/**
 * @author yangjd
 */
public interface WasteDisposalService extends BaseService<WasteDisposal> {

    List<WasteDisposal> getList(WasteDisposal temp);
}