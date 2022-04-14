package com.dou.server.service;

import com.dou.server.model.Appointment;

import java.util.List;

/**
 * @author yangjd
 */
public interface AppointmentService extends BaseService<Appointment> {

    List<Appointment> getList(Appointment temp);
}