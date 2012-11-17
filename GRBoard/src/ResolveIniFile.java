import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ResolveIniFile {
	public static class ReturnValue{
		public static String mailAddr;
		public static String passWord;
		public static String GetItemNumber;
		public static String RefreshCycle;
		public static String FontSize;
		public static String WindowHeight;
		public static String WindowWidth;
	}
	
	public static void ReadIniFile(){
		String iniFileName = "PersonalInfo.ini";
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(iniFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//ReturnValue retErrVal1 = new ReturnValue();
			//return retErrVal1;
		} catch (IOException e) {
			e.printStackTrace();
			//ReturnValue retErrVal2 = new ReturnValue();
			//return retErrVal2;
		}
		
		//ReturnValue retVal = new ReturnValue();
		ReturnValue.mailAddr = prop.getProperty("MailAddress"); 
		ReturnValue.passWord = prop.getProperty("PassWord"); 
		ReturnValue.GetItemNumber = prop.getProperty("GetItemNumber"); 
		ReturnValue.RefreshCycle = prop.getProperty("RefreshCycle");
		ReturnValue.FontSize = prop.getProperty("FontSize");
		ReturnValue.WindowHeight = prop.getProperty("WindowHeight");
		ReturnValue.WindowWidth = prop.getProperty("WindowWidth");
		
		//return retVal;
	}
}
