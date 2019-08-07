package com.mhm;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by MaHuiming on 2019/6/13.
 */
public class GenerateMeteScript {

    private static final String fileName = "C:\\Users\\Administrator\\Desktop\\物模型字典表V1.2-mhm.xlsx";

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
                XSSFSheet sheet = xssfWorkbook.getSheetAt(3);
                // 获取总共有多少行
                for (int i = 2; i < sheet.getLastRowNum() - 1; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        //处理每一行的值
                        String meteId = null, meteIdEn = null, meteName = null, unit;
                        int meteKind = 0;
                        String up_effect, low_effect;
                        //行从0开始
                        if (row.getCell(2) != null) {
                            meteId = String.valueOf(row.getCell(2).getStringCellValue());
//                            System.out.println(meteId);
                            if (StringUtils.isEmpty(meteId)) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                        if (row.getCell(3) != null) {
                            meteKind = getMeteKind(row.getCell(3).getStringCellValue());
//                            System.out.println(meteKind);
                        } else {
                            continue;
                        }

                        if (row.getCell(4) != null) {
                            meteIdEn = String.valueOf(row.getCell(4).getStringCellValue());
//                            System.out.println(meteIdEn);
                        } else {
                            continue;
                        }
                        if (row.getCell(5) != null) {
                            meteName = String.valueOf(row.getCell(5).getStringCellValue());
//                            System.out.println(meteName);
                        } else {
                            continue;
                        }

                        if (row.getCell(11) != null) {
                            if (row.getCell(11).getCellType() != org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {
                                up_effect = formatFloatNumber(
                                Float.valueOf((float) row.getCell(11).getNumericCellValue()));
                            } else {
                                if (StringUtils.isEmpty(row.getCell(11).getStringCellValue())) {
                                    up_effect = "NULL";
                                } else {
                                    up_effect = String.valueOf(row.getCell(11).getStringCellValue());
                                }
                            }
                        } else {
                            continue;
                        }

                        if (row.getCell(12) != null) {
                            if (row.getCell(11).getCellType() != org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {
                                low_effect = formatFloatNumber(
                                Float.valueOf((float) row.getCell(12).getNumericCellValue()));
                            } else {
                                if (StringUtils.isEmpty(row.getCell(11).getStringCellValue())) {
                                    low_effect = "NULL";
                                } else {

                                    low_effect = String.valueOf(row.getCell(12).getStringCellValue());
                                }
                            }
                        } else {
                            continue;
                        }

                        if (row.getCell(13) != null) {
                            unit = String.valueOf(row.getCell(13).getStringCellValue());
                        } else {
                            continue;
                        }

                        sb.append(
                        "INSERT INTO `t_cfg_model_mete` VALUES ('0101', '" + meteId + "','" + meteIdEn + "'," + meteKind
                        + ",'" + unit + "',NULL,'" + meteIdEn + "',NULL,NULL," + low_effect + "," + up_effect + ",'oc','"
                        + meteName + "');").append(line);

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

    private static int getMeteKind(String kind) {
        switch (kind) {
            case "遥信":
                return 0;
            case "遥测":
                return 1;
            case "遥控":
                return 2;
            case "遥调":
                return 3;
            case "告警":
                return 4;
            case "遥脉":
                return 5;
            default:
                return 9;
        }
    }

    /**
     * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
     *
     * @param value
     * @return Sting
     */
    public static String formatFloatNumber(float value) {
        if (value != 0.00) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("########");
            return df.format(value);
        } else {
            return "0.00";
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
