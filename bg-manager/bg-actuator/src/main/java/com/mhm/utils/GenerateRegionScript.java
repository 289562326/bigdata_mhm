package com.mhm.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by MHm on 2019/6/18.
 */
public class GenerateRegionScript {

    private static final String fileName = "C:\\Users\\Administrator\\Desktop\\企业设备需求响应基本信息填报8-15(2).xlsx";

    private static StringBuffer sb = new StringBuffer();
    private static final String line = System.getProperty("line.separator");

    public static void main(String[] args) {
        readExcel(fileName);
    }

    public static void readExcel(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(fileName));
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            if (xssfWorkbook != null) {
                // 获取sheet个数
                // 获取第一个Sheet 索引为0,excel2007
                XSSFSheet sheet = xssfWorkbook.getSheetAt(1);
                // 获取总共有多少行，行从0开始
                for (int i = 1; i < sheet.getLastRowNum() - 1; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        //处理每一行的值
                        String companyName = null, areaCode = null;
                        //行从0开始
                        if (row.getCell(0) != null) {
                            companyName = String.valueOf(row.getCell(0).getStringCellValue());
                            String[] companyNameArray = companyName.split("\\/");
                            companyName = companyNameArray[0];
                            System.out.println(companyName);
                            if (StringUtils.isEmpty(companyName)) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                        //                        if (row.getCell(1) != null) {
                        //                            if (row.getCell(1).getCellType() != org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {
                        //                                areaCode = formatFloatNumber(
                        //                                Float.valueOf((float) row.getCell(1).getNumericCellValue()));
                        //                            } else {
                        //                                if (StringUtils.isEmpty(row.getCell(11).getStringCellValue())) {
                        //                                    areaCode = "NULL";
                        //                                } else {
                        //                                    areaCode = String.valueOf(row.getCell(12).getStringCellValue());
                        //                                }
                        //                            }
                        //                            System.out.println(areaCode);
                        //                            if (StringUtils.isEmpty(areaCode)) {
                        //                                continue;
                        //                            }
                        //                        } else {
                        //                            continue;
                        //                        }

                        //                        sb.append("update from t_cfg_region set area_code="+areaCode+" where region_name='"+companyName+"';").append(line);
                        sb.append("update t_cfg_region set area_code=320582 where region_name='" + companyName + "';")
                        .append(line);

                    }
                }
            }
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String formatFloatNumber(Float value) {
        if (value != null) {
            if (value.doubleValue() != 0.00) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("########");
                return df.format(value.doubleValue());
            } else {
                return "0.00";
            }
        }
        return "";
    }
}
