package zdf.learn.com.commonUtils.tools;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


public class AwsS3Client {
    private String awsId;
    private String awsKey;
    private String region;
    private int connectTimeout;
    private int clientExecutionTimeout;
    private int maxConnections;
    private int maxErrorRetry;
    private int maxConsecutiveRetriesBeforeThrottling;
    private boolean useGzip;
    private boolean useTcpKeepAlive;
    
	public AwsS3Client(String awsId,String awsKey,String region) {
		super();
		this.awsId=awsId;
		this.awsKey=awsKey;
		this.region=region;
	}
	
	public AwsS3Client(String awsId,String region,String awsKey,int connectTimeout,
			int clientExecutionTimeout,int maxConnections,int maxErrorRetry,
			int maxConsecutiveRetriesBeforeThrottling,boolean useGzip,boolean useTcpKeepAlive) {
		super();
		this.awsId=awsId;
		this.awsKey=awsKey;
		this.region=region;
		this.connectTimeout=connectTimeout;
		this.clientExecutionTimeout=clientExecutionTimeout;
		this.maxConnections=maxConnections;
		this.maxErrorRetry=maxErrorRetry;
		this.maxConsecutiveRetriesBeforeThrottling=maxConsecutiveRetriesBeforeThrottling;
		this.useGzip=useGzip;
		this.useTcpKeepAlive=useTcpKeepAlive;
	}
    

    public AmazonS3 getS3ClientDefault() {
    	
    	BasicAWSCredentials credentials = null;
        try {
        	credentials = new BasicAWSCredentials(awsId,awsKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        ClientConfiguration config=new ClientConfiguration();
        config.setConnectionTimeout(60000);
        config.setClientExecutionTimeout(20000);
        config.setMaxConnections(500);
        config.setMaxErrorRetry(10);
        config.setMaxConsecutiveRetriesBeforeThrottling(10);
        config.setUseGzip(true);
        config.setUseTcpKeepAlive(true);
      	
        
        
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .withClientConfiguration(config)
                .build();
        return s3Client;
    }
    
    public AmazonS3 getS3Client() {
    	
    	BasicAWSCredentials credentials = null;
        try {
        	credentials = new BasicAWSCredentials(awsId,awsKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        ClientConfiguration config=new ClientConfiguration();
        config.setConnectionTimeout(connectTimeout);
        config.setClientExecutionTimeout(clientExecutionTimeout);
        config.setMaxConnections(maxConnections);
        config.setMaxErrorRetry(maxErrorRetry);
        config.setMaxConsecutiveRetriesBeforeThrottling(maxConsecutiveRetriesBeforeThrottling);
        config.setUseGzip(useGzip);
        config.setUseTcpKeepAlive(useTcpKeepAlive);
    	
        
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .withClientConfiguration(config)
                .build();
        return s3Client;
    }
}