package checkInformation.edu.tju;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//添加poi。jar
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; //再添加poi。ooxlm.jar

public class Check {

	public static String id[] = new String[146];;
	public static String student_number[] = new String[146];
	public static String name[] = new String[146];
	public static String git[] = new String[146];

    private static String filePath = System.getProperty("user.dir") + "/resources/file/软件测试名单.xlsx";


	public static void main(String[] args) {
		try {	
			InputStream is = new FileInputStream(filePath);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// 获取每一个工作薄
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet); //写法不一样，这里类名就是Sheet
				if (xssfSheet == null) {
					continue;
				}				
				int AllRowNum = xssfSheet.getLastRowNum(); //读到了144行,应该是只读有字的行

				// 获取当前工作薄的每一行
				for (int rowNum = 2; rowNum <= AllRowNum; rowNum++) { //要从2开始，因为第0行在excel里是空行，读不进来，所以weinull
					XSSFRow xssfRow = xssfSheet.getRow(rowNum); //同理Row
					if (xssfRow != null) {
						// 读取第一列数据
						XSSFCell one = xssfRow.getCell(0); //同理Shell
						one.setCellType(XSSFCell.CELL_TYPE_STRING); //把单元格格式强制设为String，避免再别的函数中复杂的格式判断
						id[rowNum]=getValue(one);
						// 读取第二列数据
						XSSFCell two = xssfRow.getCell(1);
						two.setCellType(XSSFCell.CELL_TYPE_STRING);
						student_number[rowNum]=getValue(two);
						// 读取第三列数据
						XSSFCell three = xssfRow.getCell(2);
						three.setCellType(XSSFCell.CELL_TYPE_STRING);
   					    name[rowNum]=getValue(three);
   					    // 读取第四列数据
   					    XSSFCell four = xssfRow.getCell(3);
   					    four.setCellType(XSSFCell.CELL_TYPE_STRING);
   					    git[rowNum]=getValue(four);
						// 需要转换数据的话直接调用getValue获取字符串
   					    //CheckTest c = new CheckTest();
   					    //c.setUp();
   					    //c.testPerson(student_number[rowNum],student_number[rowNum].substring(4,6));
					}
				}

				break;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 转换数据格式,因为main函数里已经强制转换成String，所以这个函数实际没用
		private static String getValue(XSSFCell xssfRow) {
			       
            
			if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) { //用实例访问给警告
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue());
			} else {
				return String.valueOf(xssfRow.getStringCellValue());
			}

		}

	
}
