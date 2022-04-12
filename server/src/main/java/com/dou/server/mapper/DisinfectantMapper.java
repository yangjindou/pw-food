package com.dou.server.mapper;

import com.dou.server.model.Disinfectant;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface DisinfectantMapper extends MyMapper<Disinfectant> {

    List<Disinfectant> getList(Disinfectant temp);
}