package zdf.learn.com.commonUtils.data;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3FilesHandler {
	private String ak ="";
	private String sk ="";
	private String endpoint ="";
	private String bucket = "g-tbdccm-gtmc";
	public void fileListInner(List<String> perfixList,AmazonS3 awsSrcS3Client) {
		List<String> submitList = new ArrayList<>();
		perfixList.stream().forEach(perfixIn->{
			System.out.println("start to pares by prefix:"+perfixIn);
			ListObjectsV2Result resultInner = null;
			ListObjectsV2Request requestInner = new ListObjectsV2Request()
	                .withBucketName(bucket)
	                .withPrefix(perfixIn);
			do {
				resultInner = awsSrcS3Client.listObjectsV2(requestInner);
		        List<S3ObjectSummary> summary = resultInner.getObjectSummaries();
				summary.forEach(objs->{
					submitList.add(objs.getKey());
					
				});
				requestInner.setContinuationToken(resultInner.getNextContinuationToken());
			}while(resultInner.isTruncated());
		});
	}
}
