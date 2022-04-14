package com.dou.server.service.impl;

import com.dou.server.mapper.AppointmentMapper;
import com.dou.server.model.Appointment;
import com.dou.server.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl extends BaseServiceImpl<Appointment> implements AppointmentService {

    private final AppointmentMapper appointmentMapper;

    @Override
    public List<Appointment> getList(Appointment temp) {
        return appointmentMapper.getList(temp);
    }

    @Override
    public void add(Appointment temp) {
        Integer orderNo = appointmentMapper.getMaxOder();
        orderNo = orderNo == null ? 1 : orderNo+1;
        temp.setFilingOrder(orderNo);
        super.add(temp);
    }

    @Override
    public void update(Appointment temp) {
        temp.setFilingOrder(null);
        super.update(temp);
    }
}