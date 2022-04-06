package com.dou.server.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author yangjindou
 * @date 2021-10-12
 */
public class ExcelTemplateUtils {

    private final HSSFSheet sheet;
    private final CellStyle defaultCellStyle;
    private final HSSFWorkbook workbook;

    public ExcelTemplateUtils(String fileUrl) throws IOException {
        Resource resource = new ClassPathResource(fileUrl);
        InputStream is = resource.getInputStream();
        workbook = new HSSFWorkbook(is);
        sheet = workbook.getSheetAt(0);

        defaultCellStyle = workbook.createCellStyle();
        Font defaultFont = workbook.createFont();
        defaultFont.setBold(false);
        defaultFont.setFontHeightInPoints((short) 12);
        defaultCellStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        defaultCellStyle.setFont(defaultFont);
    }

    public ExcelTemplateUtils() {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Sheet1");

        defaultCellStyle = workbook.createCellStyle();
        Font defaultFont = workbook.createFont();
        defaultFont.setBold(false);
        defaultFont.setFontHeightInPoints((short) 12);
        defaultCellStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        defaultCellStyle.setFont(defaultFont);
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    //poi设置自适应列宽
    public void setAutoColumnWidth() {
        //sheet的索引从0开始,获取sheet列数
        int maxColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int columnNum = 0; columnNum <= maxColumn; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            // 遍历列的数据，获取这一列的最长字符串
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(columnNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        //int length = currentCell.getStringCellValue().getBytes().length;
                        //上面是网上所有currentCell获取length答案，但试过后发现偏大，在一个文章下的评论区看到下面的计算,试了一下，列宽实现了自适应大小，还没搞懂原因
                        //length = (byte长度+string长度)/2，有了解的麻烦，麻烦留言解释一下，感谢！！
                        int length = (currentCell.getStringCellValue().getBytes(StandardCharsets.UTF_8).length + currentCell.toString().length()) / 2;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            //将最长的length*256设为列宽
            // sheet.setColumnWidth((short)列数,(short)(length*256));
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

}
