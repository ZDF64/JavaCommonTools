package zdf.learn.com.commonUtils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericDatumReader;

public class ReadAvroFile {
	public static void ReadAvroFileByPath(String filePath) {
		try {
			InputStream in = new FileInputStream(filePath);
			DataFileStream<Object> dfs = new DataFileStream<>(in, new GenericDatumReader<>());
			String schemaJson = dfs.getSchema().toString();
			System.out.println(schemaJson);
//			DataFileReader reader = new DataFileReader<>(new File(""), null) ;
//			reader.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readAvroFromS3ByMultiThread(String ak,String sk,String bucket,String path) {
		
	}
	public static void readAvroFromObs(String ak,String sk,String bucket,String path) {
		
	}
	
	public static void main(String[] args) {
		//ReadAvroFile.ReadAvroFileByPath("D:\\home\\apuser\\demo\\_k1.avro");
		File newFile = new File("\\home\\apuser\\avroData\\procdata\\CN\\real\\can_External\\08\\2023\\05\\07\\08\\");
		newFile.mkdirs();
	}
}
