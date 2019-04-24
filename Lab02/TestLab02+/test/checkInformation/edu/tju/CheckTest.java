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

public  class CheckTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  public static String id[] = new String[146];;
		public static String student_number[] = new String[146];
		public static String name[] = new String[146];
		public static String git[] = new String[146];

	    private static String filePath = System.getProperty("user.dir") + "/resources/file/软件测试名单.xlsx";


	  @Before
	  public void setUp() throws Exception {
		  String driverPath = System.getProperty("user.dir") + "/resources/driver/geckodriver.exe"; //找到驱动路径
		  System.setProperty("webdriver.gecko.driver", driverPath);
		  driver = new FirefoxDriver(); //new ChromeDriver(); 
		  baseUrl = "http://121.193.130.195:8800/"; //要登录的网址
		  driver.manage().timeouts().implicitlyWait(90000000, TimeUnit.SECONDS);
	  }

	  @Test
	  public  void testPerson() throws Exception {
		  
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
							id[rowNum]=String.valueOf(one.getStringCellValue());
							// 读取第二列数据
							XSSFCell two = xssfRow.getCell(1);
							two.setCellType(XSSFCell.CELL_TYPE_STRING);
							student_number[rowNum]=String.valueOf(two.getStringCellValue());
							// 读取第三列数据
							XSSFCell three = xssfRow.getCell(2);
							three.setCellType(XSSFCell.CELL_TYPE_STRING);
	   					    name[rowNum]=String.valueOf(three.getStringCellValue());
	   					    // 读取第四列数据
	   					    XSSFCell four = xssfRow.getCell(3);
	   					    four.setCellType(XSSFCell.CELL_TYPE_STRING);
	   					    git[rowNum]=String.valueOf(four.getStringCellValue());
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
		  
		  for(int i=2;i<10;i++) {
		  
		  
	    driver.get(baseUrl + "/"); //加载到网址
//	    WebElement we = driver.findElement(By.name("id"));
//	    we.click();
	    driver.findElement(By.name("id")).click();
	    driver.findElement(By.name("id")).clear(); //把输入框清空
	    driver.findElement(By.name("id")).sendKeys(Check.student_number[i]); //向此输入框中输入id
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).clear(); //把输入框清空
	    driver.findElement(By.name("password")).sendKeys(Check.student_number[i].substring(4, 9)); //向此输入框中输入密码
	    driver.findElement(By.id("btn_login")).click(); //点击登录
	//    assertEquals("天津大学_百度搜索", driver.getTitle());
	//   assertEquals("天津大学_百度搜索", driver.getTitle());
	//   assertEquals("赵健",driver.findElement(By.id("student-name")).toString());
	    assertEquals(Check.name[i],driver.findElement(By.id("student-name")).getText());
	    assertEquals(Check.git[i],driver.findElement(By.id("student-git")).getText());
		  driver.close() ;  //关闭网页
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