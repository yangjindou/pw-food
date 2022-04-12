package com.dou.server.service;

import com.dou.server.model.Disinfectant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
public interface DisinfectantService extends BaseService<Disinfectant> {


    List<Disinfectant> getList(Disinfectant temp);

    ByteArrayOutputStream export(Disinfectant temp) throws IOException;
}