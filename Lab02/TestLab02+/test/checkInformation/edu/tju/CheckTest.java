package checkInformation.edu.tju;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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

public  class CheckTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  public static String id[] = new String[146];;
		public static String student_number[] = new String[146];
		public static String name[] = new String[146];
		public static String git[] = new String[146];

	    private static String filePath = System.getProperty("user.dir") + "/resources/file/�����������.xlsx";


	  @Before
	  public void setUp() throws Exception {
		  String driverPath = System.getProperty("user.dir") + "/resources/driver/geckodriver.exe"; //�ҵ�����·��
		  System.setProperty("webdriver.gecko.driver", driverPath);
		  driver = new FirefoxDriver(); //new ChromeDriver(); 
		  baseUrl = "http://121.193.130.195:8800/"; //Ҫ��¼����ַ
		  driver.manage().timeouts().implicitlyWait(90000000, TimeUnit.SECONDS);
	  }

	  @Test
	  public  void testPerson() throws Exception {
		  
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
							id[rowNum]=String.valueOf(one.getStringCellValue());
							// ��ȡ�ڶ�������
							XSSFCell two = xssfRow.getCell(1);
							two.setCellType(XSSFCell.CELL_TYPE_STRING);
							student_number[rowNum]=String.valueOf(two.getStringCellValue());
							// ��ȡ����������
							XSSFCell three = xssfRow.getCell(2);
							three.setCellType(XSSFCell.CELL_TYPE_STRING);
	   					    name[rowNum]=String.valueOf(three.getStringCellValue());
	   					    // ��ȡ����������
	   					    XSSFCell four = xssfRow.getCell(3);
	   					    four.setCellType(XSSFCell.CELL_TYPE_STRING);
	   					    git[rowNum]=String.valueOf(four.getStringCellValue());
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
		  
		  for(int i=2;i<10;i++) {
		  
		  
	    driver.get(baseUrl + "/"); //���ص���ַ
//	    WebElement we = driver.findElement(By.name("id"));
//	    we.click();
	    driver.findElement(By.name("id")).click();
	    driver.findElement(By.name("id")).clear(); //����������
	    driver.findElement(By.name("id")).sendKeys(Check.student_number[i]); //��������������id
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).clear(); //����������
	    driver.findElement(By.name("password")).sendKeys(Check.student_number[i].substring(4, 9)); //������������������
	    driver.findElement(By.id("btn_login")).click(); //�����¼
	//    assertEquals("����ѧ_�ٶ�����", driver.getTitle());
	//   assertEquals("����ѧ_�ٶ�����", driver.getTitle());
	//   assertEquals("�Խ�",driver.findElement(By.id("student-name")).toString());
	    assertEquals(Check.name[i],driver.findElement(By.id("student-name")).getText());
	    assertEquals(Check.git[i],driver.findElement(By.id("student-git")).getText());
		  driver.close() ;  //�ر���ҳ
		  }

	  }  
		  
	  @After
	  public void tearDown() throws Exception {
		     
		  driver.quit();
//	    String verificationErrorString = verificationErrors.toString();
//	    if (!"".equals(verificationErrorString)) {
//	      fail(verificationErrorString);
//	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}