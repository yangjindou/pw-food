package com.dou.server.service.impl;

import com.dou.server.mapper.AppointmentMapper;
import com.dou.server.model.Appointment;
import com.dou.server.model.vo.AppointmentVO;
import com.dou.server.service.AppointmentService;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.DateUtils;
import com.dou.server.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl extends BaseServiceImpl<Appointment> implements AppointmentService {

    private final AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentVO> getList(AppointmentVO temp) {
        return appointmentMapper.getList(temp);
    }

    @Override
    public void add(Appointment temp) {
        Integer orderNo = appointmentMapper.getMaxOder();
        orderNo = orderNo == null ? 100000 : orderNo+1;
        temp.setFilingOrder(orderNo);
        super.add(temp);
    }

    @Override
    public void update(Appointment temp) {
        temp.setFilingOrder(null);
        if (CommonUtils.varIsNotBlank(temp.getSamplingGoodName())) {
            temp.setSamplingDate(new Date());
        }
        if (CommonUtils.varIsNotBlank(temp.getUninstallUser())) {
            temp.setUninstallDate(new Date());
        }
        if (CommonUtils.varIsNotBlank(temp.getWarehousingWeightPoultry())) {
            temp.setWarehousingDate(new Date());
        }
        if (CommonUtils.varIsNotBlank(temp.getWarehousedWeightPoultry())) {
            temp.setWarehousedDate(new Date());
        }
        super.update(temp);
    }

    @Override
    public ByteArrayOutputStream export(AppointmentVO temp) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<AppointmentVO> list = this.getList(temp);
        ExcelUtils etUtils = new ExcelUtils();
        HSSFSheet sheet = etUtils.getSheet();
        Row rowFirst = sheet.createRow(0);
        rowFirst.createCell(0).setCellValue("????????????");
        rowFirst.createCell(1).setCellValue("????????????");
        rowFirst.createCell(2).setCellValue("???????????????????????????");
        rowFirst.createCell(3).setCellValue("???????????????");
        rowFirst.createCell(4).setCellValue("????????????");
        rowFirst.createCell(5).setCellValue("????????????");
        rowFirst.createCell(6).setCellValue("????????????");
        rowFirst.createCell(7).setCellValue("????????????");
        rowFirst.createCell(8).setCellValue("????????????");
        rowFirst.createCell(9).setCellValue("????????????");
        rowFirst.createCell(10).setCellValue("?????????/??????");
        rowFirst.createCell(11).setCellValue("??????");
        rowFirst.createCell(12).setCellValue("?????????Kg???");
        rowFirst.createCell(13).setCellValue("????????????");
        rowFirst.createCell(14).setCellValue("?????????");
        rowFirst.createCell(15).setCellValue("????????????");
        rowFirst.createCell(16).setCellValue("????????????");
        if (CommonUtils.varIsNotBlank(list)) {
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 1);
                AppointmentVO data = list.get(i);
                row.createCell(0).setCellValue(data.getFilingOrder());
                row.createCell(1).setCellValue(data.getFilingState());
                row.createCell(2).setCellValue(data.getArea());
                row.createCell(3).setCellValue(data.getWarehouseName());
                if (CommonUtils.varIsNotBlank(data.getWarehousingDate())) {
                    row.createCell(4).setCellValue(DateUtils.dateToFormatStr(data.getWarehousingDate() ,"yyyy-MM-dd"));
                }
                row.createCell(5).setCellValue(data.getGoodTypeName());
                row.createCell(6).setCellValue(data.getGoodName());
                row.createCell(7).setCellValue(data.getGoodSourceName());
                row.createCell(8).setCellValue(data.getSourceName());
                row.createCell(9).setCellValue(data.getWarehousedProve());
                row.createCell(10).setCellValue(data.getOriginPlace());
                row.createCell(11).setCellValue(data.getAmount());
                row.createCell(12).setCellValue(data.getWeight());
                row.createCell(13).setCellValue(data.getDriver());
                row.createCell(14).setCellValue(data.getCarNumber());
                row.createCell(15).setCellValue(data.getDriverPhone());
                row.createCell(16).setCellValue(DateUtils.dateToFormatStr(data.getCreateDate() ,"yyyy-MM-dd HH:mm:ss"));
            }
        }
        etUtils.setAutoColumnWidth();
        etUtils.getWorkbook().write(outputStream);
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream exportWarehouse(AppointmentVO temp) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<AppointmentVO> list = this.getList(temp);
        ExcelUtils etUtils = new ExcelUtils();
        HSSFSheet sheet = etUtils.getSheet();
        Row rowFirst = sheet.createRow(0);
        rowFirst.createCell(0).setCellValue("????????????");
        rowFirst.createCell(1).setCellValue("?????????");
        rowFirst.createCell(2).setCellValue(temp.getFilterType() + "??????");
        rowFirst.createCell(3).setCellValue("????????????Kg???");
        rowFirst.createCell(4).setCellValue("????????????Kg???");
        rowFirst.createCell(5).setCellValue("?????????Kg???");
        rowFirst.createCell(6).setCellValue("????????????Kg???");
        rowFirst.createCell(7).setCellValue("?????????Kg???");
        if (CommonUtils.varIsNotBlank(list)) {
            if (temp.getFilterType().equals("??????")) {
                for (int i = 0; i < list.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    AppointmentVO data = list.get(i);
                    double sum = data.getWarehousingWeightPoultry() + data.getWarehousingWeightLivestock() + data.getWarehousingWeightAquatic() + data.getWarehousingWeightOther();
                    row.createCell(0).setCellValue(data.getFilingOrder());
                    row.createCell(1).setCellValue(data.getWarehouseName());
                    row.createCell(2).setCellValue(DateUtils.dateToFormatStr(data.getWarehousingDate(), "yyyy-MM-dd"));
                    row.createCell(3).setCellValue(sum);
                    row.createCell(4).setCellValue(data.getWarehousingWeightPoultry());
                    row.createCell(5).setCellValue(data.getWarehousingWeightLivestock());
                    row.createCell(6).setCellValue(data.getWarehousingWeightAquatic());
                    row.createCell(7).setCellValue(data.getWarehousingWeightOther());
                }
            } else if (temp.getFilterType().equals("??????")) {
                for (int i = 0; i < list.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    AppointmentVO data = list.get(i);
                    double sum = data.getWarehousedWeightPoultry() + data.getWarehousedWeightLivestock() + data.getWarehousedWeightAquatic() + data.getWarehousedWeightOther();
                    row.createCell(0).setCellValue(data.getFilingOrder());
                    row.createCell(1).setCellValue(data.getWarehouseName());
                    row.createCell(2).setCellValue(DateUtils.dateToFormatStr(data.getWarehousingDate(), "yyyy-MM-dd"));
                    row.createCell(3).setCellValue(sum);
                    row.createCell(4).setCellValue(data.getWarehousedWeightPoultry());
                    row.createCell(5).setCellValue(data.getWarehousedWeightLivestock());
                    row.createCell(6).setCellValue(data.getWarehousedWeightAquatic());
                    row.createCell(7).setCellValue(data.getWarehousedWeightOther());
                }
            }
        }
        etUtils.setAutoColumnWidth();
        etUtils.getWorkbook().write(outputStream);
        return outputStream;
    }
}