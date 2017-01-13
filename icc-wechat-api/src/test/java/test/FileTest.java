package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

import com.icc.util.StrUtil;

public class FileTest {

	public static final ResourceManager ma=ResourceManager.getInstance("test.url");
	
	public static void main(String[] args) {
		new FileTest().exceptionTest("");
	}
	public void exceptionTest(String bucketName) {
        if (!StrUtil.isValidity(bucketName)) {
            throw new IllegalArgumentException(ma.getFormattedString(
                    "test", bucketName));
        }else{
        	throw new IllegalArgumentException(ma.getFormattedString(
        			"test", bucketName));	
        }
    }
	/**
	 * ResourceBundle 使用这种方式时,资源文件必须放到src的根目录下面，并且getBundle()里面的参数不需要后缀
	 */
	@Test
	public void testReadProperties(){
		Locale local=new Locale("zh", "CN");
		ResourceBundle rb=ResourceBundle.getBundle("config/wechat",local);
		System.err.println(rb.getString("tokenUrl"));
		
	}
	
	/**
	 * Class 使用这种方式时,path既可以使用绝对路径也可以使用相对路径,只要正确找到文件的路径即可
	 * @throws IOException
	 */
	@Test
	public void byClass() throws IOException{
		Properties p3 = new Properties();
		//InputStream is3 = MainTest.class.getResourceAsStream("../test.properties");
		InputStream is3 = FileTest.class.getResourceAsStream("url.properties");
		p3.load(is3);
		String result3 = p3.get("test").toString();
		System.out.println(result3);
	}
	
	/**
	 * ClassLoader 使用这种方式时,path一定不能用使用绝对路径（也就是以“/”开头),虽然不是绝对路径 但它会从跟目录下找 （也就是从src往下找）
	 * @throws IOException 
	 * 
	 */
	@Test
	public void byClassLoader() throws IOException{
		Properties p=new Properties();
		InputStream in=ClassLoader.getSystemResourceAsStream("test/url.properties");
		p.load(in);
		System.err.println(p.get("test"));
	}
	@Test
	public void byClassLoader2() throws IOException{
		Properties p=new Properties();
		InputStream in=FileTest.class.getClassLoader().getResourceAsStream("test/url.properties");
		p.load(in);
		System.err.println(p.get("test"));
	}
}
