package com.mhm;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 导出错误码
 * Created by MaHuiming on 2019/5/28.
 */
public class ExcelUtil {
    private static final String fileName = "C:\\Users\\Administrator\\Desktop\\电力\\525\\2.xlsx";
    private static final String writerFileName =
    System.getProperty("user.dir") + "\\src\\main\\java\\com\\znv\\user\\common\\exception\\ResultCodeEnum.java";

    private static StringBuffer sb = new StringBuffer();
    private static final String line = System.getProperty("line.separator");

    public static void main(String[] args) {
        readExcel(fileName);
    }

    public static void readExcel(String fileName) {

        sb.append("package com.znv.user.common.exception;").append(line);
        sb.append("public enum ResultCodeEnum {").append(line);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(fileName));
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            if (xssfWorkbook != null) {
                // 获取sheet个数
                // 获取第一个Sheet 索引为0,excel2007
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
                // 获取总共有多少行
                for (int i = 1; i < sheet.getLastRowNum() - 1; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        //处理每一行的值
                        String errorCode = null, descZh = null;
                        if (row.getCell(1) != null
                        && row.getCell(1).getCellType() != org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {
                            errorCode = String.valueOf(Double.valueOf(row.getCell(1).getNumericCellValue()).intValue());
                        } else {
                            continue;
                        }
                        if (row.getCell(2) != null) {
                            descZh = row.getCell(2).getStringCellValue();
                        } else {
                            continue;
                        }
                        sb.append("\t" + "CODE" + errorCode + "(" + errorCode + ",\"" + descZh + "\"),").append(line);
                        //                        if (i == sheet.getLastRowNum() - 2) {
                        //                            sb.append("\t\t" + "CODE" + errorCode + "(" + errorCode + ",\"" + descZh + "\");").append(line);
                        //                        }

                    }
                }
            }
            sb.delete(sb.length() - 3, sb.length()).append(";").append(line);
            sb.append("\t" + "private int code;\n" + "    private String name;\n" + "\n"
            + "    private ResultCodeEnum(int code, String name) {\n" + "        this.code = code;\n"
            + "        this.name = name;\n" + "    }\n" + "\n" + "    public int getCode() {\n"
            + "        return this.code;\n" + "    }\n" + "\n" + "    public String getName() {\n"
            + "        return this.name;\n" + "    }").append(line);
            sb.append("}").append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                fileInputStream = null;
                e.printStackTrace();
            }
        }
        writeJavaFile(sb.toString());

    }

    public static void writeJavaFile(String s) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(writerFileName, false);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                writer = null;
                e.printStackTrace();
            }
        }
    }

}
