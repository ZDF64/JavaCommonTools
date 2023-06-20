package zdf.learn.com.commonUtils.data.ObjectStorage.HuaweiObs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;

import com.huawei.mrs.MrsObsCredentialsProvider;
import com.obs.services.OBSCredentialsProviderChain;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;

public class HuaweiObsModule {
	
	public ObsClient build(String ak, String sk,String endPoint) {
		ObsClient returnObs = new ObsClient(ak,sk, endPoint);
    	return returnObs;
	}
	
	public ObsClient build(String endPoint) {
    	OBSCredentialsProviderChain obsChain = new OBSCredentialsProviderChain();
//    	MrsObsCredentialsProvider
    	ObsConfiguration conf = new ObsConfiguration();
    	conf.setEndPoint(endPoint);
    	conf.setConnectionTimeout(30000);
    	conf.setMaxErrorRetry(3);
    	conf.setMaxConnections(30000);
    	conf.setMaxIdleConnections(30);
    	conf.setKeepAlive(true);
     	ObsClient returnObs = new ObsClient(obsChain, conf);
    	return returnObs;
    }
	public ObsClient buildMrsObs(String endPoint) {
		try {
			MrsObsCredentialsProvider mrsObs = new MrsObsCredentialsProvider(new URI(""), new Configuration());
	    	// mrs-obs-provider.jar
	    	ObsConfiguration conf = new ObsConfiguration();
	    	conf.setEndPoint(endPoint);
	    	conf.setConnectionTimeout(30000);
	    	conf.setMaxErrorRetry(3);
	    	conf.setMaxConnections(30000);
	    	conf.setMaxIdleConnections(30);
	    	conf.setKeepAlive(true);
	     	ObsClient returnObs = new ObsClient(mrsObs, conf);
	    	return returnObs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
    }
}
