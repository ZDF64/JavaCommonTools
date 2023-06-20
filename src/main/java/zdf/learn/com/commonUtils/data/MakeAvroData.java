package zdf.learn.com.commonUtils.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.obs.services.ObsClient;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ObjectListing;
import com.obs.services.model.ObsObject;

import cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.data.ObjectStorage.HuaweiObs.HuaweiObsModule;
import zdf.learn.com.commonUtils.data.avro.schema.MaTripPojo;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Can300_19nev;
import zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus;
import zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList;
import zdf.learn.com.commonUtils.data.service.AvroUtilTool;
import zdf.learn.com.commonUtils.tools.AwsS3Client;
import zdf.learn.com.commonUtils.tools.ComputeTools;
import zdf.learn.com.commonUtils.tools.DataMakeUtils;
@Slf4j
public class MakeAvroData {
	private static Broadcast<ObsClient> broadcastObsClient;
	private ObsClient obsDev = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
	public static String readBucketName = "g-cb-canvp-19dcm-a-g";
	public static String awsAk="AKIASILNB7UB24JXU65F";
	public static String awsSk="H9UCNuikAnXV7o2aubVsKYZhblZwwepFu4pAkHMG";
	public static String awsEndpoint="cn-north-1";
	public static int MaxSize = 18000000;
	public static int parallelism = 400; 
	public static int blockSize = 4000;
	public static String bucketName = "g-tbdccm-gtmc";
	public static int indexCnt;
	public static String prefixStartUrl = "procdata/CN/real/can_External/";
	public static String suffixDate = "2023/06/16/09/";
	private AvroUtilTool avroTools = new AvroUtilTool();
//	private static Broadcast<String> broadcastSchema;
//	public StructType structType ;
//	public List<StructType> StructTypeList = new ArrayList<StructType>();
	/**
	 * 一些接口方法
	 */
	/**
	 * 当前时间串
	 */
	Supplier<String> createDateString = ()->{
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
	};
	Supplier<String> createJuneFirstString = ()->{
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.parse("2023-06-01T16:20:00").atZone(ZoneId.of("Asia/Shanghai")));
	};
	/**
	 * 
	 * @param jsc
	 * @param keys
	 */
	public void makeAvroData(long sum) {
		// reset schema, different bucket different schema
        String schemaJson = null;
        try {
        	Schema schema = Can300_19nev.SCHEMA$;
        	String modelArvoUrl = "E:\\DataStorage\\S3Avro\\newFile\\";
        	/**
        	 * 新方法
        	 */
        	List<Can300_19nev> all = makeFewEntity(0,sum);
        	long startIn = System.currentTimeMillis();
//        	List<Can300_19nev> all = makeEntity(0,sum);
//        	all.addAll(makeFewEntity(1,sum));
//        	all.addAll(makeFewEntity(2,sum));
//        	all.addAll(makeFewEntity(3,sum));
        	System.out.println("all::::"+all.size());
        	long endin = System.currentTimeMillis();
        	DatumWriter<Can300_19nev> userDatumWriter = new SpecificDatumWriter<Can300_19nev>(Can300_19nev.class);
           
        	ArrayList<ArrayList<Can300_19nev>> rsList = ComputeTools.SplitList(36, all);
            System.out.println("splite large list into:" + rsList.size()+",cost:"+(endin-startIn));
			
            for(List<?> listChild :rsList) {
            	new Thread(()->{
            		long start = System.currentTimeMillis();
            		DataFileWriter<Can300_19nev> dataFileWriter = new DataFileWriter<Can300_19nev>(userDatumWriter);
        	        try {
						dataFileWriter.create(Can300_19nev.getClassSchema(), new File(modelArvoUrl+((int)(Math.random()*100000))+"_data.avro"));
						listChild.forEach(f->{
	        				try {
	        					dataFileWriter.append((Can300_19nev)f);
	        				} catch (Exception e) {
	        					// TODO Auto-generated catch block
	        					e.printStackTrace();
	        				} 
	        			});
						
						dataFileWriter.close();
						long end = System.currentTimeMillis();
						DefangFileHandle df = new DefangFileHandle();
						System.out.println("make save for :"+listChild.size()+">>> "+String.format("%s file time cost %d", ""+listChild.size(),end-start));
						df.toWrite(String.format("%s file time cost %d", ""+listChild.size(),end-start), "E:\\DataStorage\\timecost.log", false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            		
            	}).start();
            	
            }
           
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void readAvro(SparkSession spark, String url) {
		spark.read().format("avro").load(url).show(10000,true);
	}
	public List<Can300_19nev> makeFewEntity(int indexRedis,long sum) {
		return Stream.iterate(0, x->x+1).limit(sum).map(x->{
			Can300_19nev can300 = new Can300_19nev();
			String vin = String.format("VINFEW%03dB00%04d",(int)Math.random()*1000,(int)Math.random()*10000);
			can300.setBody(avroTools.makeDcm19.apply(vin));
			can300.setCorrelationId("Core001");		
			can300.setDecoderWarningList(makeDecoderWarningList(10));
			can300.setDispatchModelType("TEST-HEV001");
			can300.setGroupNumber("TEST00001");
			can300.setVehicleName("UX");
			can300.setHeaders(avroTools.makeHeaderMap.apply(vin));
			return can300;
		}).collect(Collectors.toList());		
	}
	public List<Can300_19nev> makeEntity(int indexRedis,long sum) {
		return makeVinByRedis(indexRedis,sum).stream().map(vin->{
			Can300_19nev can300 = new Can300_19nev();
			can300.setBody(avroTools.makeDcm19.apply(vin));
//			can300.setBody(new Dcm19Message());
			can300.setCorrelationId("Core001");		
			can300.setDecoderWarningList(makeDecoderWarningList(10));
			can300.setDispatchModelType("TEST-HEV001");
			can300.setGroupNumber("TEST00001");
			can300.setVehicleName("UX");
			can300.setHeaders(avroTools.makeHeaderMap.apply(vin));
			return can300;}
		).collect(Collectors.toList());		
	}
	
	
	public List<MaTripPojo> makeVinByObsCsv(ObsClient obsClient,String objectKey) {
		ObsObject obsJ = obsClient.getObject("prd-tbdccm-obs-infra01-g-app", objectKey);
//		ObsObject obsJ = obsClient.getObject("b-tbdccm-gtmc-r", objectKey);
		InputStream ins = obsJ.getObjectContent();
		List<MaTripPojo> returnList = new ArrayList<MaTripPojo>();
		try {
//			FileInputStream ins = new FileInputStream(new File("D:\\home\\apuser\\datamake\\vinlistforAvroAll.csv"));
			byte[] b = new byte[4];
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				try {
					returnList.add(MaTripPojo.builder()
							.vehicle_id(tempStr.split(",")[0])
							.target_date("2023-06-14")
							.igOff("2023-06-14 16:30:01")
							.igOn("2023-06-14 16:10:01")
							.build());
				} catch (Exception e) {
					System.out.println("error line:"+tempStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	public List<MaTripPojo> makeVinByLimit(int limit) {
		List<MaTripPojo> returnList = new ArrayList<MaTripPojo>();
		DataMakeUtils dmu = new DataMakeUtils();
		try {
//			
			for(int i = 0  ; i <limit ; i++) {
				returnList.add(MaTripPojo.builder()
							.vehicle_id(dmu.makeVin.get())
							.target_date(dmu.createDateStringByFormat.apply("yyyyMMdd"))
							.igOn(dmu.createDateAfterNowTime.get().toString())
							.igOff(dmu.createDateAfterNowTime.get().toString())
							.build());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	/**
	 * 
	 * @param indexRedis
	 * @param sum
	 * @return
	 */
	public List<String> makeVinByRedis(int indexRedis ,long sum) {
		Jedis javaRedis = new Jedis("192.168.52.73",6379);
		
		javaRedis.select(indexRedis);
		List<String> returnList = javaRedis.keys("*").stream().limit(sum).map(m->{
			return m.split("_").length==2?m.split("_")[1]:"TESTVINXXXXX00000001";
		}).collect(Collectors.toList());
		
		javaRedis.close();
		return returnList;
	}
	/**
	 * 合成DecoderWarningList队列数据
	 * @param sum
	 * @return
	 */
	public List<DecoderWarningList> makeDecoderWarningList(int sum){
		return Stream.iterate(0,  x->x+1)
		.limit(sum)
		.map(map->{
			return DecoderWarningList.newBuilder().setMessage(MessageFormat.format("test data make @core:{0}", map)).setType(DecodeWarningStatus.UNSETTLED).build();
		}).collect(Collectors.toList());
	}
	
	
	public static void main(String[] args) {
		
		
		for (String arg : args) {
            if (arg.startsWith("--MaxSize")) {
            	MaxSize = Integer.parseInt(arg.split("=")[1]) ;
            } else if (arg.startsWith("--parallelism")) {
            	parallelism = Integer.parseInt(arg.split("=")[1]);
            } else if(arg.startsWith("--blockSize")) {
            	blockSize = Integer.parseInt(arg.split("=")[1]);
            }else if(arg.startsWith("--bucketName")) {
            	bucketName  = arg.split("=")[1];
            }else if(arg.startsWith("--prefix")) {
            	prefixStartUrl = arg.split("=")[1];
            }else if(arg.startsWith("--indexCnt")) {
            	indexCnt = Integer.parseInt(arg.split("=")[1]) ;
            }
            
        }
		MakeAvroData mads = new MakeAvroData();
		mads.fetchPrefix.apply(3);
		System.out.println(String.format("MaxSize=%s,parallelism=%s,blockSize=%s", MaxSize,parallelism,blockSize));
		System.out.println(String.format("readBucketName=%s,bucketName=%s,prefixStartUrl=%s", readBucketName,bucketName,prefixStartUrl));
//		
		try(SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date())
				.getOrCreate(); 
			JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());){
			HuaweiObsModule obsModule = new HuaweiObsModule();
			ObsClient obsCsv = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
//			ObsClient obsCsv = obsModule.buildMrsObs("obs.cn-north-4.myhuaweicloud.com");
			MakeAvroData mad = new MakeAvroData();
			mad.FetchListBySize(readBucketName, prefixStartUrl,suffixDate, (bucket,objKeys)->{
				mad.submitToTransfer(jsc, bucket, objKeys, parallelism);
			}, blockSize);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private Can300_19nev updateAndCopy(Can300_19nev can300Seed,MaTripPojo vin) {
		can300Seed.setHeaders(avroTools.makeHeaderMapByMaTrip.apply(vin));
		return can300Seed;
	}
	/**
	 * 
	 */
	
	/**
	 * 81818组
	 * @param parallelism
	 */
	private static void makeDataNew(int parallelism) {
		int maxBlock = 81818;
		MakeAvroData mdCsv = new MakeAvroData();
		int crx = 0;
		
//		try(SparkSession spark = SparkSession.builder()
//				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date())
//				.getOrCreate(); 
//			JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());){
			ObsClient obsSeedReader = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
					//MakeAvroData.buildMrsObs("obs.cn-north-4.myhuaweicloud.com");
//			ListObjectsRequest request = new ListObjectsRequest("prd-tbdccm-obs-infra01-g-app");
			ListObjectsRequest request = new ListObjectsRequest(readBucketName);
			request.setPrefix("seeds/");
			List<String> objectKeyList = new ArrayList<>();
			ObjectListing resultList = null;
			do {
				resultList = obsSeedReader.listObjects(request);
				resultList.getObjects().parallelStream().forEach(f -> {
					if(f.getObjectKey().endsWith(".avro")) {
						System.out.println(f.getObjectKey());
						objectKeyList.add(f.getObjectKey());
					}
				});
				request.setMarker(resultList.getNextMarker());
			} while (resultList.isTruncated());
			
			List<ObsObject> objectList = new ArrayList<>();
			for(String key : objectKeyList) {
				objectList.add(obsSeedReader.getObject(readBucketName, key));
			}
			ReadAvroFile readAndWrite = new ReadAvroFile();
			
			
			for(int i = 0 ; i < 81818/parallelism ; i ++) {
				
			}
			
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
	/**
	 * 
	 * @param parallelism
	 */
	private static void makeDataOld(int parallelism) {
		MakeAvroData mad = new MakeAvroData();
		HuaweiObsModule obsModule = new HuaweiObsModule();
		int crx = 0;
		int maxRate = 50;
		try(SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date())
				.getOrCreate(); 
			JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());){
//			ObsClient obsCsv = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
			ObsClient obsCsv = obsModule.buildMrsObs("obs.cn-north-4.myhuaweicloud.com");
			for(int i = 0 ; i <5 ; i ++) {
				String objectKey = "meta/vinlistforAvroAll"+i+".csv";
				List<MaTripPojo> subList = mad.makeVinByObsCsv(obsCsv,objectKey).stream().limit(180000).collect(Collectors.toList());
				ArrayList<ArrayList<MaTripPojo>> subMatrix = ComputeTools.SplitList(10, subList);
				System.out.println("makeVinByObsCsv subMatrix Size: "+subMatrix.size());
				for(List<MaTripPojo> innerSubList : subMatrix) {
					System.out.println("makeVinByObsCsv innerSubList Size: "+innerSubList.size());
					ArrayList<ArrayList<MaTripPojo>> subVinMatrix = ComputeTools.SplitList(parallelism, innerSubList);
					
					for(int rate = 0 ; rate < maxRate; rate ++) {
						//提交spark任务
						mad.submit(jsc,subVinMatrix);
					}
				}
				System.out.println("make data group index: "+crx);
				crx ++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param bucket
	 * @param prefix   前半段prefix,用于遍历这个目录下所有文件，获取所有哈希
	 * @param suffixDate
	 * @param consumer
	 * @param batchSize  最大提取文件数量
	 */
	public void FetchListBySize(String bucket , String prefix,String suffixDate,BiConsumer<String,List<String>> consumer,int batchSize) {
		List<String> perfixList = new ArrayList<>();
		List<String> submitList = new ArrayList<>();
		MakeAvroData mads = new MakeAvroData();
		AmazonS3 awsSrcS3Client = new AwsS3Client(awsAk, awsSk, awsEndpoint).getS3ClientDefault();
//		awsSrcS3Client.listBuckets().forEach(buckets->{
//			System.out.println(buckets.getName());
//		});
//		ListObjectsV2Request request = new ListObjectsV2Request()
//                .withBucketName(bucket)
//                .withPrefix(prefix).withDelimiter("/")
//				;
//		ListObjectsV2Result result = null;

//		do {
//            result = awsSrcS3Client.listObjectsV2(request);
//            result.getCommonPrefixes().forEach(cp -> {
//            	System.out.println(cp + suffixDate);
//            	perfixList.add(cp + suffixDate);
//            	});
//            request.setContinuationToken(result.getNextContinuationToken());
//        } while (result.isTruncated());
		perfixList = mads.fetchPrefix.apply(indexCnt);
		perfixList.stream().forEach(perfixIn->{
			System.out.println("start to pares by prefix:"+perfixIn);
			ListObjectsV2Request request = new ListObjectsV2Request()
				      .withBucketName(bucket)
				      .withPrefix(perfixIn).withDelimiter("/");
			ListObjectsV2Result resultInner = null;
			ListObjectsV2Request requestInner = new ListObjectsV2Request()
	                .withBucketName(bucket)
	                .withPrefix(perfixIn);
			do {
				resultInner = awsSrcS3Client.listObjectsV2(requestInner);
		        List<S3ObjectSummary> summary = resultInner.getObjectSummaries();
				summary.forEach(objs->{
					submitList.add(objs.getKey());
					if(batchSize<=submitList.size()) {
//						开始执行
						System.out.println("凑足Batch，开始执行,"+submitList.size());
						consumer.accept(bucket, submitList);
						submitList.clear();
					}
				});
				request.setContinuationToken(resultInner.getNextContinuationToken());
			}while(resultInner.isTruncated());
		});
		
	}
	
	public void submitToTransfer(JavaSparkContext jsc,String bucket, List<String> keys ,int parallelism) {
		Long start = System.currentTimeMillis();
		ArrayList<ArrayList<String>> subKeyMatrix = ComputeTools.SplitList(parallelism, keys);
		JavaRDD<ArrayList<String>> rdd = jsc.parallelize(subKeyMatrix);
		rdd.foreachPartition(fp->{
			HuaweiObsModule obsModule = new HuaweiObsModule();
			AmazonS3 awsSrcS3Client = new AwsS3Client(awsAk, awsSk, awsEndpoint).getS3ClientDefault();
			ObsClient obsClient = obsModule.buildMrsObs("obs.cn-north-4.myhuaweicloud.com");
//			ObsClient obsClient = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
			DataMakeUtils dmu = new DataMakeUtils();
			List<File> upoadFile = new ArrayList<>();
			System.out.println("foreachPartition start to send obs:"+bucketName);
			while(fp.hasNext()) {
				List<String> innerList = fp.next();
				System.out.println("foreachPartition next innerList:"+innerList.size());
				System.out.println("foreachPartition next innerList example:"+innerList.get(0));
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
						obsClient.putObject(bucketName, newPath, TempFile);
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
			obsClient.close();
		});
		Long end = System.currentTimeMillis();
		System.out.println("batch work time cost,"+(end-start)/1000);
	}
	
	
	private void submit(JavaSparkContext jsc,ArrayList<ArrayList<MaTripPojo>> subVinMatrix) {
		JavaRDD<ArrayList<MaTripPojo>> rdd = jsc.parallelize(subVinMatrix);
		rdd.mapPartitions(m->{
			MakeAvroData mds = new MakeAvroData();
			List<Can300_19nev> listRsAll = new ArrayList<Can300_19nev>();
            while(m.hasNext()) {
            	
            }
            return listRsAll.iterator();
        }).foreachPartition(fs->{
        	HuaweiObsModule obsModulefp = new HuaweiObsModule();
        	System.out.println("foreachPartition start to send obs:"+bucketName);
        	ObsClient obsPartition = obsModulefp.buildMrsObs("obs.cn-north-4.myhuaweicloud.com");
            DatumWriter<Can300_19nev> userDatumWriter = new SpecificDatumWriter<Can300_19nev>(Can300_19nev.class);
            List<File> upoadFile = new ArrayList<>();
            DataFileWriter<Can300_19nev> dataFileWriter = new DataFileWriter<Can300_19nev>(userDatumWriter);
            DataMakeUtils mdu = new DataMakeUtils();
        	fs.forEachRemaining(can300->{
                try {
                	File TempFile = File.createTempFile(can300.getHeaders().get("VIN")+"_"+can300.getHeaders().get("DispatchModelType")+"-"+Math.random()*1000000, ".avro");
                    dataFileWriter.create(Can300_19nev.getClassSchema(),TempFile);
                    dataFileWriter.append(can300);
                    upoadFile.add(TempFile);
                    dataFileWriter.flush();
	            	obsPartition.putObject(bucketName, prefixStartUrl+mdu.makeHexStrByMax.apply(255)+"/2023/06/14/08/"+TempFile.getName(), TempFile);
                    dataFileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            });
        	System.out.println("foreachPartition make File finished:"+upoadFile.size());
            upoadFile.forEach(fsAvro->{
            	fsAvro.delete();
            });
        });
	}
	public Function<Integer, List<String>> fetchPrefix = (index) ->{
		List<String> retrunList = new ArrayList<String>();
		DefangFileHandle dfTools = new DefangFileHandle();
		InputStream ins = MakeAvroData.class.getResourceAsStream("/Prefix.log");
		List<String> cacheList = dfTools.readToLine(ins);
		for(int i = 0 ; i < cacheList.size(); i++) {
			if(i>index) {
				retrunList.add(cacheList.get(i));
			}
		}
		retrunList.forEach(System.out::println);
		return retrunList;
	};
}

/**
 * 获取一个Schema模版
 * 读取Avro文件，获取schema和数据
 * 这个方法有问题，在map方法处理后，发现错误Compiling "GeneratedClass": Two non-abstract methods "
 * public int scala.collection.TraversableOnce.size()" have the same parameter types, declaring type and return type。：
 */
//String modelArvoUrl = "E:\\DataStorage\\S3Avro\\Can300_19nev.asvc";
//Dataset<Row> modelData = spark.read().format("avro").load(modelArvoUrl);
//DataFileStream<Object> dfs = new DataFileStream<>(new FileInputStream(new F.ile(modelArvoUrl)),new GenericDatumReader<>());
//Schema schema = new Schema.Parser().parse(dfs.getSchema().toString());
////
//StructType schemaDs = modelData.schema();
//System.out.println("schemaDs structTypes"+schemaDs);
//modelData.limit(1).foreach(f->{
//	StructType structTypes = f.schema();
//	System.out.println("inner structTypes"+structTypes);
//});
//Encoder<Row> encoder = RowEncoder.apply(schemaDs);
//System.out.println(structType);
//JavaRDD<Row> parseRdd = modelData.javaRDD();
//Configuration cfg = new Configuration();
//Row row = (Row) new AvroDeserializer(schema, SchemaConverters.toSqlType(schema).dataType(),"EXCEPTION").deserialize(dfs.next()).get();
