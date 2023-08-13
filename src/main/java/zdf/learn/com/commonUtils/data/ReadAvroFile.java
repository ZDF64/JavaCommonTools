package zdf.learn.com.commonUtils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericDatumReader;

import com.obs.services.ObsClient;

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
		ObsClient obsPartition = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");

		try {
			File TempFile = File.createTempFile("VIN_data", ".avro");
			obsPartition.putObject("b-tbdccm-gtmc", "procdata/CN/real/can_External/00/2023/06/01/16/"+TempFile.getName(), TempFile);
//			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		ReadAvroFile.ReadAvroFileByPath("D:\\home\\apuser\\AvroData\\_data.avro");
//		File newFile = new File("\\home\\apuser\\avroData\\procdata\\CN\\real\\can_External\\08\\2023\\05\\07\\08\\");
//		newFile.mkdirs();
	}
}
