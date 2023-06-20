package zdf.learn.com.commonUtils.data;

import java.io.File;
import java.util.Map;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificDatumReader;

import cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm;

public class ReadAvroFile {
	static String requiredLabels = "Speed,Speed_TypeA,Speed_TypeB,FuelConsumption,AccelPedalAngle_TypeA,AccelPedalAngle_TypeC,AccelPedalAngle_TypeB,AccelerationFB,AccelerationFB_TypeB,AccelerationFB_TypeC,EcoModeIndicator,DriveModeECO,DriveModeECO_TypeB,SportModeSelect,DriveModeSPORT,DriveModeSPORT_TypeB,PowerModeSelect_TypeA,PowerModeSelect_TypeB,DriveModePOWER,DriveModePOWER_TypeB,SnowModeSelect,RecIndicator,WiperControl,Odometer_km,AirConIndicator";
	public static String bucketName = "g-tbdccm-gtmc";
	public static String prefixUrl = "procdata/CN/real/can_External/";
	public void ReadAvroFileByPath(String path) {
		SpecificDatumReader<Can300_19dcm> datum = new SpecificDatumReader<>();
		try {
			DataFileReader<Can300_19dcm> reader = new DataFileReader<>(new File(path), datum);
			Can300_19dcm can300 = reader.next();
			Map<CharSequence,CharSequence> header = can300.getHeaders();
			for(CharSequence key : header.keySet()) {
				if(key.toString().equals("Company")) {
					header.put(key, "ftms");
				}
			}
			
			System.out.println(can300.getHeaders().toString());
			System.out.println(can300.getBody());
			System.out.println(can300.getSchema());
			System.out.println(can300.getCorrelationId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void readAvroFromS3ByMultiThread(String ak,String sk,String bucket,String path) {
		
	}
	public static void readAvroFromObs(String ak,String sk,String bucket,String path) {
		
	}
	
	public static void main(String[] args) {
		ReadAvroFile readAF = new ReadAvroFile();
//		
		
		readAF.ReadAvroFileByPath("D:\\home\\apuser\\data\\S3avro\\g-cb-canvp-19dcm-a-l\\procdata\\CN\\real\\can_External\\00\\2023\\06\\16\\09\\JTHACABB0N8013225-AYH36L-LFXVB-20230616090056790.avro");
//		File newFile = new File("\\home\\apuser\\avroData\\procdata\\CN\\real\\can_External\\08\\2023\\05\\07\\08\\");
//		newFile.mkdirs();
	}
}
