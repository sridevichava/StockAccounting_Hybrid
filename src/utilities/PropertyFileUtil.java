package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
	
	
	public static String getValueForKey(String Key) throws Exception{
		Properties p=new Properties();
		
		FileInputStream fis=new FileInputStream("D:\\sridevi_82\\StockAccounting_Hybrid\\PropertiesFile\\Enviornment.properties");
		
		p.load(fis);
		return p.getProperty(Key);
		
		
	}

}
