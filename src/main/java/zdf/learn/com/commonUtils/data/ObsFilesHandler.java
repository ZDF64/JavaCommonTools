package zdf.learn.com.commonUtils.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.obs.services.ObsClient;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ObjectListing;

import cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionUtil;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.tools.DataMakeUtils;

public class ObsFilesHandler {
//	 client = new ObsClient("H8XLOSB8HOWAXHPRRSFH","0Bs8al2OeGl1b0gcQnzaat5lOCWcKGwDvD0vVm1I", conf);

	private String ak ="H8XLOSB8HOWAXHPRRSFH";
	private String sk ="0Bs8al2OeGl1b0gcQnzaat5lOCWcKGwDvD0vVm1I";
	private String endpoint ="obs.cn-north-4.myhuaweicloud.com";
	private String bucket = "tmci-hw-hcr-prod-obs-candata-01";
	public static void main(String[] args) {
		ObsFilesHandler obsCheck = new ObsFilesHandler();
		obsCheck.fileList("gtmc/land/trip_can/targetdate=20230605", null);
		String pathWithoutObs = "tmci-hw-hcr-prod-obs-candata-01/gtmc/land/";
		String bucketName = pathWithoutObs.substring(0, pathWithoutObs.indexOf("/"));
		String prefix = pathWithoutObs.substring(pathWithoutObs.indexOf("/")+1)+"trip_can/";
		System.out.println(String.format("pathWithoutObs:%s,bucketName:%s,prefix:%s", pathWithoutObs,bucketName,prefix));
		String f = "gtmc/land/trip_can/targetdate=20230605/assist_data_time=20230609/part-00000-b3af3d02-99fa-4c93-8e71-c5cfc25a4cdc.c000.snappy.parquet";
		
		System.out.println(f.substring(f.indexOf("=")+1,f.indexOf("/assist_data_time")));
	}
	
	
	/**
	 * 一级列表分拣获取
	 * @param prefix
	 * @param outPutFile
	 */
	private void fileList(String prefix,String outPutFile) {
		try {
			ObsClient obsclient = new ObsClient(ak, sk, endpoint);
			ListObjectsRequest request = new ListObjectsRequest(bucket);
			request.setPrefix(prefix);
			request.setMaxKeys(1000);
			ObjectListing listObj = null;
			DefangFileHandle dfTool = new DefangFileHandle();
			
			do{
				listObj = obsclient.listObjects(request);
				listObj.getObjects().forEach(obs->{
					if(outPutFile==null) {
						System.out.println(obs.getObjectKey());
					}else {
						dfTool.toWrite(obs.getObjectKey(), outPutFile, true);
					}
					
				});
				request.setMarker(listObj.getNextMarker());
			}while(listObj.isTruncated());
			obsclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void parseS3ToObs(String bucketName,List<String> innerList,AmazonS3 awsSrcS3Client,ObsClient obsClient) {
		List<File> upoadFile = new ArrayList<>();
		DataMakeUtils dmu = new DataMakeUtils();
		System.out.println("foreachPartition next innerList:"+innerList.size());
//		System.out.println("foreachPartition next innerList example:"+innerList.get(0));
		for(String objKey : innerList) {
			SpecificDatumReader<Can300_19dcm> datum = new SpecificDatumReader<Can300_19dcm>();
			DatumWriter<Can300_19dcm> DatumWriter = new SpecificDatumWriter<Can300_19dcm>(Can300_19dcm.class);
			try {
				DataFileStream<Can300_19dcm> reader = null;
				try {
					reader = new DataFileStream<>(awsSrcS3Client.getObject(bucket, objKey).getObjectContent(), datum);
					
				} catch (Exception e) {
					System.out.println("start ot retry,"+e.getMessage());
					for(int i = 0 ; i < 3 ; i++) {
						try {
							reader = new DataFileStream<>(awsSrcS3Client.getObject(bucket, objKey).getObjectContent(), datum);
							System.out.println("retry success:"+i);
							break;
						} catch (Exception e2) {
							System.out.println("retry failed:"+i+","+e2.getMessage());
							Thread.sleep(5000);
						}
					}
				}
				if(reader==null) {
					System.out.println("retry failed, continue:"+objKey);
					continue;
				}
				Can300_19dcm can300 = reader.next();
				Map<CharSequence,CharSequence> header = can300.getHeaders();
				String newVin = "";
				String oldVin = "";
				String newPath = "";
				for(CharSequence key : header.keySet()) {
					if(key.toString().equals("VIN")) {
						oldVin = header.get(key).toString();
						newVin = dmu.makeVinByEncrypt.apply(oldVin);
						header.put(key, newVin);
					}
				}
				newPath = objKey.replace(oldVin, newVin);
				
				DataFileWriter<Can300_19dcm> dataFileWriter = new DataFileWriter<Can300_19dcm>(DatumWriter);
				File TempFile = File.createTempFile(can300.getHeaders().get("VIN")+"_"+can300.getHeaders().get("DispatchModelType")+"-"+Math.random()*1000000, ".avro");
                dataFileWriter.create(Can300_19dcm.getClassSchema(),TempFile);
                dataFileWriter.append(can300);
                upoadFile.add(TempFile);
                dataFileWriter.flush();
				obsClient.putObject(bucketName,newPath, TempFile);
				dataFileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("foreachPartition upload Files finished:"+upoadFile.size());
		upoadFile.forEach(fsAvro->{
        	fsAvro.delete();
        });
	}
}
