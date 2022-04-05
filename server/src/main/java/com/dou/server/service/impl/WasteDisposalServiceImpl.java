package com.dou.server.service.impl;

import com.dou.server.mapper.WasteDisposalMapper;
import com.dou.server.model.WasteDisposal;
import com.dou.server.service.WasteDisposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WasteDisposalServiceImpl extends BaseServiceImpl<WasteDisposal> implements WasteDisposalService {

    private final WasteDisposalMapper wasteDisposalMapper;

    @Override
    public List<WasteDisposal> getList(WasteDisposal temp) {
        return wasteDisposalMapper.getList(temp);
    }
}