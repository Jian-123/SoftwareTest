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

//���poi��jar
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; //�����poi��ooxlm.jar

public class Check {

	public static String id[] = new String[146];;
	public static String student_number[] = new String[146];
	public static String name[] = new String[146];
	public static String git[] = new String[146];

    private static String filePath = System.getProperty("user.dir") + "/resources/file/�����������.xlsx";


	public static void main(String[] args) {
		try {	
			InputStream is = new FileInputStream(filePath);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// ��ȡÿһ��������
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet); //д����һ����������������Sheet
				if (xssfSheet == null) {
					continue;
				}				
				int AllRowNum = xssfSheet.getLastRowNum(); //������144��,Ӧ����ֻ�����ֵ���

				// ��ȡ��ǰ��������ÿһ��
				for (int rowNum = 2; rowNum <= AllRowNum; rowNum++) { //Ҫ��2��ʼ����Ϊ��0����excel���ǿ��У���������������weinull
					XSSFRow xssfRow = xssfSheet.getRow(rowNum); //ͬ��Row
					if (xssfRow != null) {
						// ��ȡ��һ������
						XSSFCell one = xssfRow.getCell(0); //ͬ��Shell
						one.setCellType(XSSFCell.CELL_TYPE_STRING); //�ѵ�Ԫ���ʽǿ����ΪString�������ٱ�ĺ����и��ӵĸ�ʽ�ж�
						id[rowNum]=getValue(one);
						// ��ȡ�ڶ�������
						XSSFCell two = xssfRow.getCell(1);
						two.setCellType(XSSFCell.CELL_TYPE_STRING);
						student_number[rowNum]=getValue(two);
						// ��ȡ����������
						XSSFCell three = xssfRow.getCell(2);
						three.setCellType(XSSFCell.CELL_TYPE_STRING);
   					    name[rowNum]=getValue(three);
   					    // ��ȡ����������
   					    XSSFCell four = xssfRow.getCell(3);
   					    four.setCellType(XSSFCell.CELL_TYPE_STRING);
   					    git[rowNum]=getValue(four);
						// ��Ҫת�����ݵĻ�ֱ�ӵ���getValue��ȡ�ַ���
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


	// ת�����ݸ�ʽ,��Ϊmain�������Ѿ�ǿ��ת����String�������������ʵ��û��
		private static String getValue(XSSFCell xssfRow) {
			       
            
			if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) { //��ʵ�����ʸ�����
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue());
			} else {
				return String.valueOf(xssfRow.getStringCellValue());
			}

		}

	
}
