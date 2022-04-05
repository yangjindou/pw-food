package com.dou.server.service.impl;

import com.dou.server.mapper.DeviceMapper;
import com.dou.server.model.Device;
import com.dou.server.service.DeviceService;
import com.dou.server.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

    private final DeviceMapper deviceMapper;

    @Override
    public List<Device> getList(Device temp) {
        return deviceMapper.getList(temp);
    }

    @Override
    public ByteArrayOutputStream export(Device temp) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Device> list = deviceMapper.getList(temp);
        HSSFWorkbook workbook = new HSSFWorkbook();
        CellStyle defaultCellStyle = workbook.createCellStyle();
        Font defaultFont = workbook.createFont();
        defaultFont.setBold(false);
        defaultFont.setFontHeightInPoints((short) 12);
        defaultCellStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        defaultCellStyle.setFont(defaultFont);
        HSSFSheet sheet = workbook.createSheet("设备列表");
        if (list != null && !list.isEmpty()) {
            Row rowFirst = sheet.createRow(0);
            rowFirst.createCell(0).setCellValue("检验计量器");
            rowFirst.createCell(1).setCellValue("是否定期校验");
            rowFirst.createCell(2).setCellValue("校验日期");
            rowFirst.createCell(3).setCellValue("检测废弃物是否按规定存放");
            rowFirst.createCell(4).setCellValue("录入人");
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Device data = list.get(i);
                row.createCell(0).setCellValue(data.getInspectionMeter());
                row.createCell(1).setCellValue(data.getIsPeriodicInspection());
                row.createCell(2).setCellValue(DateUtils.dateToFormatStr(data.getInspectionDate() ,"yyyy-MM-dd"));
                row.createCell(3).setCellValue(data.getIsRequiredSave());
                row.createCell(4).setCellValue(data.getCreateUserName());
            }
        }
        workbook.write(outputStream);
        return outputStream;
    }
}