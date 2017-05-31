package com.yq.datadriver;

import com.yq.log4j.LoggerControler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Excel作为数据驱动（poi支持到2007）
 */
public class ExcelTest {
    final static LoggerControler log = LoggerControler.getlogger(ExcelUtils.class);


    /**
     * @param file 读取某个excel文件的数据（第一行为名称）
     * @return 返回Object[][]
     */
    public Object[][] readFromExcel(String file) {
        ArrayList<String> arrkey = new ArrayList<String>();
        Workbook workbook = ExcelUtils.getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
//        获取总行数
        int rowTotalNum = sheet.getLastRowNum() + 1;
//        总列数
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();

        HashMap<String, String>[][] map = new HashMap[rowTotalNum - 1][1];
        // 对数组中所有元素hashmap进行初始化
        if (rowTotalNum > 1) {
            for (int i = 0; i < rowTotalNum - 1; i++) {
                map[i][0] = new HashMap();
            }
        } else {
            log.error("测试的Excel" + file + "中没有数据");
        }
        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < columns; c++) {
            String cellvalue = ExcelUtils.getCellValue(sheet, 0, c);
            arrkey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rowTotalNum; r++) {
            for (int c = 0; c < columns; c++) {
                String cellvalue = ExcelUtils.getCellValue(sheet, r, c);
                map[r - 1][0].put(arrkey.get(c), cellvalue);
            }
        }
        return map;

    }

    @DataProvider(name = "excelData")
    public Object[][] data1() {
//        String file = ".\\excels\\testdata.xlsx";
        ExcelTest excelTest = new ExcelTest();
        return excelTest.readFromExcel(".\\excels\\testdata.xlsx");//提供的数据不对，testNG会忽略！！！！
//        return excelTest.readFromExcel(".\\excels\\a.xlsx");
    }

    @Test(dataProvider = "excelData")
    public void testCase(HashMap<String, String> data) {
        String fileName = data.get("excelName");
        String bpSheetName = data.get("Benefits Package Sheet");
        int bpRowNum = Integer.parseInt(data.get("BP sheet RowNum"));
        String csvSheetName = data.get("Cost Share Variances Sheet");
        int csvRowNum = Integer.parseInt(data.get("CSV Sheet RowNum"));
        String hiosPlanID = data.get("HIOS Plan ID");
        String isPass = data.get("isPass");
        System.out.println(fileName + " " + bpSheetName + " "
                + bpRowNum + " " + csvSheetName + " " + csvRowNum + " " + hiosPlanID + " " + isPass);
    }

/*    @Test(dataProvider = "excelData")
    public void testCase2(HashMap<String, String> data) {
        System.out.println(data.get("username"));
        System.out.println(data.get("pwd"));
    }*/


}
