package zdf.learn.com.commonUtils.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.ListBucketsRequest;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ObjectListing;
import com.obs.services.model.ObsObject;
import com.obs.services.model.PutObjectResult;

import cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionUtil;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.data.ObjectStorage.HuaweiObs.HuaweiObsModule;
import zdf.learn.com.commonUtils.tools.DataMakeUtils;

/**
 * obs的一些基本操作
 * @Project       CommonUtils
 * @CreatedTime   2023年8月17日
 * @Content       
 * @author        ZDF64
 *
 */
public class ObsFilesHandler {
//	 client = new ObsClient("H8XLOSB8HOWAXHPRRSFH","0Bs8al2OeGl1b0gcQnzaat5lOCWcKGwDvD0vVm1I", conf);

	private String ak ="KHTCNRDCRQGCAQGGMLQX";
	private String sk ="wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q";
	private String endpoint ="obs.cn-north-4.myhuaweicloud.com";
	private String bucket = "dev-tbdccm-obs-infra01-a-l";
	private Integer partitionSize = 8;
	public static void main(String[] args) {
		ObsFilesHandler obsCheck = new ObsFilesHandler();

		obsCheck.findNextPaths("dev-tbdccm-obs-infra01-a-l","header/", null, 
				paths->{
					
				}, 
				objects->{
					System.out.println();
				});
		obsCheck.downloadObsToFile("dev-tbdccm-obs-infra01-e-f",
				"last_trip_info/csv/distinctListBefore.csv",
				"E:\\gtmc\\Obs.csv", null);
	}
	
	public List<String> getAllBucket() {
		ObsClient obsclient = new ObsClient(ak, sk, endpoint);
		ListBucketsRequest request = new ListBucketsRequest();
		request.setMaxKeys(1000);
		return obsclient.listBuckets(request).stream().map(m->{
			return m.getBucketName();
		}).collect(Collectors.toList());
	}
	
	public static void readAndSaveToFileObs(String bucketName,String path) throws Exception  {
		ObsClient obsCanCli = HuaweiObsModule.build("ak","sk","obs.cn-north-4.myhuaweicloud.com");
		ObsObject theObj = obsCanCli.getObject(bucketName, path);
		InputStream in = theObj.getObjectContent();
		
		File fsTemp =new File(path, theObj.getObjectKey().split(",")[1]);
		OutputStream out = new FileOutputStream(fsTemp);
		
		byte buf[] = new byte[64];
        int bytesRead = in.read(buf);
        while (bytesRead >= 0) {
            out.write(buf, 0, bytesRead);
            bytesRead = in.read(buf);
        }
        out.flush();
        in.close();
        out.close();
		
	}
	
	/**
	 * 一级列表分拣获取
	 * @param bucketName        桶名
	 * @param prefix            路径前缀
	 * @param outPutFile        打印输出文件
	 * @param pathConsumer      收集下一级所有路径，待消费
	 * @param objectConsumer    收集当前路径下所有对象，待消费
	 */
	public void findNextPaths(String bucketName,String prefix,String outPutFile,Consumer<List<String>> pathConsumer,Consumer<List<ObsObject>> objectConsumer) {
		try {
			ObsClient obsclient = new ObsClient(ak, sk, endpoint);
			ListObjectsRequest request = new ListObjectsRequest(bucketName);
			request.setPrefix(prefix);
			request.setDelimiter("/");
			request.setMaxKeys(1000);
			ObjectListing listObj = null;
			DefangFileHandle dfTool = new DefangFileHandle();
			List<String> nextPaths = new ArrayList<>();
			List<ObsObject> objectSummarys = new ArrayList<>();
			do{
				listObj = obsclient.listObjects(request);
				listObj.getObjects().forEach(obecjtKey->{
					objectSummarys.add(obecjtKey);
					if(outPutFile!=null) {
						dfTool.toWrite(obecjtKey.getObjectKey(), outPutFile, true);
					}
				});
				listObj.getCommonPrefixes().forEach(path->{
					nextPaths.add(path);
					if(outPutFile!=null) {
						dfTool.toWrite(path, outPutFile, true);
					}
				});
				request.setMarker(listObj.getNextMarker());
			}while(listObj.isTruncated());
			objectConsumer.accept(objectSummarys);
			pathConsumer.accept(nextPaths);
			obsclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 下级列表分拣获取
	 * @param prefix
	 * @param outPutFile  为空则打印到控制台
	 */
	public void findRestAllPaths(String bucketName,String prefix,String outPutFile,Consumer<List<String>> pathConsumer,Consumer<List<ObsObject>> objectConsumer) {
		try {
			ObsClient obsclient = new ObsClient(ak, sk, endpoint);
			ListObjectsRequest request = new ListObjectsRequest(bucket);
			request.setPrefix(prefix);
			request.setMaxKeys(1000);
			ObjectListing listObj = null;
			DefangFileHandle dfTool = new DefangFileHandle();
			List<String> nextPaths = new ArrayList<>();
			List<ObsObject> objectSummarys = new ArrayList<>();
			
			do{
				listObj = obsclient.listObjects(request);
				listObj.getObjects().forEach(obecjtKey->{
					objectSummarys.add(obecjtKey);
					if(outPutFile!=null) {
						dfTool.toWrite(obecjtKey.getObjectKey(), outPutFile, true);
					}
					if(objectSummarys.size()%partitionSize == 0) {
						int partitionCnt = objectSummarys.size()/partitionSize;
						objectConsumer.accept(new ArrayList<>(objectSummarys.subList(partitionCnt*partitionSize, (partitionCnt+1)*partitionSize)));
					}
				});
				listObj.getCommonPrefixes().forEach(path->{
					nextPaths.add(path);
					if(outPutFile!=null) {
						dfTool.toWrite(path, outPutFile, true);
					}
					
					if(nextPaths.size()%partitionSize == 0) {
						int partitionCnt = nextPaths.size()/partitionSize;
						pathConsumer.accept(new ArrayList<>(nextPaths.subList(partitionCnt*partitionSize, (partitionCnt+1)*partitionSize)));
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
	
	/**
	 * 下载obs文件
	 * @param bucketName
	 * @param objectKey
	 * @param downloadFile
	 * @param consumer
	 */
	public void downloadObsToFile(String bucketName, String objectKey,String downloadFile,Consumer<File> consumer) {
		ObsClient obsclient = new ObsClient(ak, sk, endpoint);
		GetObjectRequest request = new GetObjectRequest(bucketName, objectKey);
		ObsObject obs = obsclient.getObject(request);
		InputStream in = obs.getObjectContent();
		File obsDownload = new File(downloadFile);
		try {
			FileOutputStream outPut = new FileOutputStream(obsDownload);
			byte[] readByte = new byte[64];
			int len = 0;
			while((len = in.read(readByte))>-1) {
				outPut.write(readByte,0,len);
			}
			outPut.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 文件地址上传
	 * @param bucketName
	 * @param objectKey
	 * @param uploadFilePath
	 * @param consumer     处理结果回调
	 */
	public void putObject(String bucketName, String objectKey,String uploadFilePath,Consumer<PutObjectResult> consumer) {
		ObsClient obsclient = new ObsClient(ak, sk, endpoint);
		PutObjectResult result = obsclient.putObject(bucket, objectKey, new File(uploadFilePath));
		consumer.accept(result);
	}
	/**
	 * 文件上传
	 * @param bucketName
	 * @param objectKey
	 * @param uploadFile
	 * @param consumer      处理结果回调
	 */
	public void putObject(String bucketName, String objectKey,File uploadFile,Consumer<PutObjectResult> consumer) {
		ObsClient obsclient = new ObsClient(ak, sk, endpoint);
		PutObjectResult result = obsclient.putObject(bucket, objectKey, uploadFile);
		consumer.accept(result);
	}
	
	
	
}
