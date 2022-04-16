package com.dou.server.mapper;

import com.dou.server.model.Appointment;
import com.dou.server.model.vo.AppointmentVO;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface AppointmentMapper extends MyMapper<Appointment> {

    List<AppointmentVO> getList(AppointmentVO temp);

    Integer getMaxOder();
}