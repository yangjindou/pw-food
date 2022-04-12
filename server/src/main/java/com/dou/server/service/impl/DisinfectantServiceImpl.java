package com.dou.server.service.impl;

import com.dou.server.mapper.DisinfectantMapper;
import com.dou.server.model.Disinfectant;
import com.dou.server.service.DisinfectantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class DisinfectantServiceImpl extends BaseServiceImpl<Disinfectant> implements DisinfectantService {

    private final DisinfectantMapper disinfectantMapper;

    @Override
    public List<Disinfectant> getList(Disinfectant temp) {
        return disinfectantMapper.getList(temp);
    }
}