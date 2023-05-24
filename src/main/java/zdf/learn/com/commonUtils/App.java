package zdf.learn.com.commonUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		System.out.println("=======START=======");
		
		try {
			
			try (AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(AppConfig.class)) {
				DefangFileHandle file = (DefangFileHandle) application.getBean("dfTools");
//			List<String> rsList = file.readToLine("D:\\home\\apuser\\toyata-hcr-devOpsTool\\dbmanager\\querySql.json");
				file.testCut("123","asd","qz");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========FIN========");
	}
}
