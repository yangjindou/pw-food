package com.dou.server.service;

import com.dou.server.model.Appointment;
import com.dou.server.model.vo.AppointmentVO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
public interface AppointmentService extends BaseService<Appointment> {

    List<AppointmentVO> getList(AppointmentVO temp);

    void add(Appointment temp);

    void update(Appointment temp);

    ByteArrayOutputStream export(AppointmentVO temp) throws IOException;
}