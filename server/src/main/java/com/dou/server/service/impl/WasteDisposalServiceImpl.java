package com.dou.server.service.impl;

import com.dou.server.mapper.WasteDisposalMapper;
import com.dou.server.model.WasteDisposal;
import com.dou.server.service.WasteDisposalService;
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
public class WasteDisposalServiceImpl extends BaseServiceImpl<WasteDisposal> implements WasteDisposalService {

    private final WasteDisposalMapper wasteDisposalMapper;

    @Override
    public List<WasteDisposal> getList(WasteDisposal temp) {
        return wasteDisposalMapper.getList(temp);
    }

    @Override
    public ByteArrayOutputStream export(WasteDisposal temp) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<WasteDisposal> list = this.getList(temp);
        HSSFWorkbook workbook = new HSSFWorkbook();
        CellStyle defaultCellStyle = workbook.createCellStyle();
        Font defaultFont = workbook.createFont();
        defaultFont.setBold(false);
        defaultFont.setFontHeightInPoints((short) 12);
        defaultCellStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        defaultCellStyle.setFont(defaultFont);
        HSSFSheet sheet = workbook.createSheet("列表");
        if (list != null && !list.isEmpty()) {
            Row rowFirst = sheet.createRow(0);
            rowFirst.createCell(0).setCellValue("物品名称");
            rowFirst.createCell(1).setCellValue("物品数量");
            rowFirst.createCell(2).setCellValue("存放地方");
            rowFirst.createCell(3).setCellValue("是否存放48小时");
            rowFirst.createCell(4).setCellValue("录入时间");
            rowFirst.createCell(5).setCellValue("操作人");
            rowFirst.createCell(6).setCellValue("备注");
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 1);
                WasteDisposal data = list.get(i);
                row.createCell(0).setCellValue(data.getItemName());
                row.createCell(1).setCellValue(data.getItemAmount());
                row.createCell(2).setCellValue(data.getSavePlace());
                row.createCell(3).setCellValue(data.getIsSave48hour());
                row.createCell(4).setCellValue(DateUtils.dateToFormatStr(data.getCreateDate() ,"yyyy-MM-dd HH:mm:ss"));
                row.createCell(5).setCellValue(data.getCreateUserName());
                row.createCell(6).setCellValue(data.getRemark());
            }
        }
        workbook.write(outputStream);
        return outputStream;
    }
}