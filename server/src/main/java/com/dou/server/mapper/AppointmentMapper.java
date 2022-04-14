package com.dou.server.mapper;

import com.dou.server.model.Appointment;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface AppointmentMapper extends MyMapper<Appointment> {

    List<Appointment> getList(Appointment temp);

    Integer getMaxOder();
}