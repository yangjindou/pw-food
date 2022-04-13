package com.dou.server.service.impl;

import com.dou.server.mapper.DisinfectionRecordMapper;
import com.dou.server.model.DisinfectionRecord;
import com.dou.server.service.DisinfectionRecordService;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.DateUtils;
import com.dou.server.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class DisinfectionRecordServiceImpl extends BaseServiceImpl<DisinfectionRecord> implements DisinfectionRecordService {

    private final DisinfectionRecordMapper disinfectionRecordMapper;

    @Override
    public List<DisinfectionRecord> getList(DisinfectionRecord temp) {
        return disinfectionRecordMapper.getList(temp);
    }

    @Override
    public ByteArrayOutputStream export(DisinfectionRecord temp) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<DisinfectionRecord> list = this.getList(temp);
        ExcelUtils etUtils = new ExcelUtils();
        HSSFSheet sheet = etUtils.getSheet();
        Row rowFirst = sheet.createRow(0);
        rowFirst.createCell(0).setCellValue("监管仓");
        rowFirst.createCell(1).setCellValue("消杀区域");
        rowFirst.createCell(2).setCellValue("消杀时间");
        rowFirst.createCell(3).setCellValue("电话号码");
        rowFirst.createCell(4).setCellValue("消毒液配比");
        if (CommonUtils.varIsNotBlank(list)) {
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 1);
                DisinfectionRecord data = list.get(i);
                row.createCell(0).setCellValue(data.getWarehouseName());
                row.createCell(1).setCellValue(data.getArea());
                row.createCell(2).setCellValue(DateUtils.dateToFormatStr(data.getDate() ,"yyyy-MM-dd HH:mm:ss"));
                row.createCell(3).setCellValue(data.getPhone());
                row.createCell(4).setCellValue(data.getDisinfectantRatio());
            }
        }
        etUtils.setAutoColumnWidth();
        etUtils.getWorkbook().write(outputStream);
        return outputStream;
    }
}