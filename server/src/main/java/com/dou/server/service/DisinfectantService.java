package com.dou.server.service;

import com.dou.server.model.Disinfectant;

import java.util.List;

/**
 * @author yangjd
 */
public interface DisinfectantService extends BaseService<Disinfectant> {


    List<Disinfectant> getList(Disinfectant temp);
}