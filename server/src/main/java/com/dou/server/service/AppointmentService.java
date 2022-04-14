package com.dou.server.service;

import com.dou.server.model.Appointment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
public interface AppointmentService extends BaseService<Appointment> {

    List<Appointment> getList(Appointment temp);

    void add(Appointment temp);

    void update(Appointment temp);

    ByteArrayOutputStream export(Appointment temp) throws IOException;
}