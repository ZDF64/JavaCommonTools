package zdf.learn.com.commonUtils.data;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

import com.obs.services.OBSCredentialsProviderChain;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.model.ObsObject;

import redis.clients.jedis.Jedis;
import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.data.avro.schema.MaTripPojo;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Can300_19nev;
import zdf.learn.com.commonUtils.data.avro.schema.can300.CanFrameNumber;
import zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList;
import zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader;
import zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message;
import zdf.learn.com.commonUtils.data.avro.schema.can300.GPS;
import zdf.learn.com.commonUtils.data.avro.schema.can300.MM;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Point;
import zdf.learn.com.commonUtils.data.avro.schema.can300.TimeAndCoordinate;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Type3OutsideUseData;
import zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition;
import zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader;
import zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus;
import zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList;
import zdf.learn.com.commonUtils.tools.ComputeTools;
import zdf.learn.com.commonUtils.tools.DataMakeUtils;
public class MakeAvroData {
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
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
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
           
            List<List<Can300_19nev>> rsList = ComputeTools.SplitList(36, all);
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
			can300.setBody(makeDcm19.apply(vin));
			can300.setCorrelationId("Core001");		
			can300.setDecoderWarningList(makeDecoderWarningList(10));
			can300.setDispatchModelType("TEST-HEV001");
			can300.setGroupNumber("TEST00001");
			can300.setVehicleName("UX");
			can300.setHeaders(makeHeaderMap.apply(vin));
			return can300;
		}).collect(Collectors.toList());		
	}
	public List<Can300_19nev> makeEntity(int indexRedis,long sum) {
		return makeVinByRedis(indexRedis,sum).stream().map(vin->{
			Can300_19nev can300 = new Can300_19nev();
			can300.setBody(makeDcm19.apply(vin));
//			can300.setBody(new Dcm19Message());
			can300.setCorrelationId("Core001");		
			can300.setDecoderWarningList(makeDecoderWarningList(10));
			can300.setDispatchModelType("TEST-HEV001");
			can300.setGroupNumber("TEST00001");
			can300.setVehicleName("UX");
			can300.setHeaders(makeHeaderMap.apply(vin));
			return can300;}
		).collect(Collectors.toList());		
	}
	
	Supplier<Map<CharSequence, Map<CharSequence, CharSequence>>> makeOutSideMap = () ->{
		DefangFileHandle dfTools = new DefangFileHandle();
		Map<CharSequence, Map<CharSequence, CharSequence>> returnMap = new HashMap<CharSequence, Map<CharSequence, CharSequence>>();
		InputStream ins = MakeAvroData.class.getResourceAsStream("/can_label_gtmc.csv");
		dfTools.readToLine(ins)
		.stream()
		.map(strCsv->{
 			String[] parts = strCsv.split(",");
			if(parts != null && parts.length>0) {
				Map<CharSequence, CharSequence> valueMap = new HashMap<>();
				if(parts.length>1) {
					valueMap.put(""+String.format("%.02f", Math.random()*1000) + "", parts[1]);
				}else {
					valueMap.put(""+String.format("%.02f", Math.random()*1000) + "", "");
				}
				returnMap.put(parts[0], valueMap);
			}
			return returnMap;
		}).collect(Collectors.toList());
		return returnMap;
	};
	
	Function<Integer,List<CanInformationList>> makeCanInformationList = size ->{
		return Stream.iterate(0, x->{return x+1;}).limit(size).map(x->{
			
			CanInformationList innerDc = new CanInformationList();
			innerDc.setCanId(x+"");
			innerDc.setCanType(10000L);
			innerDc.setCollectType(100L);
			innerDc.setDataLengthAfterCompression(10L);
			innerDc.setTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Shanghai"))));
			innerDc.setOutsideUseDataMap(makeOutSideMap.get());
			return innerDc;
		}).collect(Collectors.toList());
	};
	Function<Integer,List<Type3OutsideUseData>> makeType3OutsideUseDataList = size ->{
		DefangFileHandle dfTools = new DefangFileHandle();
		InputStream ins = MakeAvroData.class.getResourceAsStream("/can_label_gtmc.csv");
		return dfTools.readToLine(ins)
		.stream()
		.map(strCsv->{
 			String[] parts = strCsv.split(",");
 			Type3OutsideUseData innerDc = new Type3OutsideUseData();
			if(parts != null && parts.length>0) {
				if(parts.length>1) {
					innerDc.setUnit(parts[1]);
				}else {
					innerDc.setUnit("");
				}
				innerDc.setValue(String.format("%.02f", Math.random()*1000));
				innerDc.setLabel(parts[0]);
				innerDc.setDateTime(createDateString.get());
			}
			return innerDc;
		}).collect(Collectors.toList());
		
	};
	/**
	 * 
	 */
	Function<Integer,List<DataCapacity>> makeDataCapacityList = size ->{
		return Stream.iterate(0, x->{return x+1;}).limit(size).map(x->{
			DataCapacity innerDc = new DataCapacity();
			CanFrameNumber canFrame = new CanFrameNumber();
			innerDc.setCanFrameNumber(canFrame);
			innerDc.setCanInformationList(makeCanInformationList.apply(size));
			TimeAndCoordinate tac = new TimeAndCoordinate();
			GPS gps = new GPS();
			gps.setGpsDate(createDateString.get());
			gps.setHdop(100L);
			gps.setMeasureCount(90L);
			gps.setPdop(200L);
			gps.setVdop(999L);
			Point p = new Point(); 
			p.setLatitude(100.0d);
			p.setLongitude(90.0d);
			gps.setPoint(p);
			tac.setGps(gps);
			MM mm = new MM();
			mm.setPoint(p);
			mm.setRticLinkId(99999L);
			tac.setMm(mm);
			innerDc.setTimeAndCoordinate(tac);
			innerDc.setType3OutsideUseData(makeType3OutsideUseDataList.apply(10));
			return innerDc;
		}).collect(Collectors.toList());
	};
	Function<String,Dcm19Message> makeDcm19 = vin ->{
		
		Dcm19Message dcm19 = new Dcm19Message();
		CommonHeader CHear= new CommonHeader();
		CHear.setCommandType(10000L);
		CHear.setCommunicationModeFlag(10L);
		CHear.setDataVersion("ture");
		CHear.setDcu(99L);
		CHear.setDcuOrDcuMeuClassification(33L);
		CHear.setElectricPfInformation(256L);
		CHear.setFormatInformation(233L);
		CHear.setGeodeticSystemInformation(512L);
		CHear.setMapBasedVersion(1024L);
		CHear.setMeu(667L);
		CHear.setSize(998L);
		dcm19.setCommonHeader(CHear);
		dcm19.setDataCapacityList(makeDataCapacityList.apply(10));
		UpdCondition upd = new UpdCondition();
		dcm19.setUpdCondition(upd);
		VehicleInformationHeader vinInfo = new VehicleInformationHeader();
		dcm19.setVehicleInformationHeader(vinInfo);
		return dcm19;
	};
	
	Function<String,Map<CharSequence, CharSequence>> makeHeaderMap =  vin -> {
		DataMakeUtils dmu = new DataMakeUtils();
		HashMap<CharSequence, CharSequence> map = new HashMap<CharSequence, CharSequence>();
		map.put("Company", "FTMS");
		map.put("Dest", "BJ");
		map.put("TBDC-CorrelationId", "e9c4da3b-fa66-3a25-98e5-e135eea84c34");
		map.put("NaviModel", "14");
		map.put("ServiceDeviceId", "354301119051690");
		map.put("Maker", "FTMS");
		map.put("TBDC-APIM-UserName", "TSCPPcn-north-1");
		map.put("RequestDateTime", dmu.createDateStringByFormat.apply("yyyyMMddHHmmssSSS"));
		map.put("CarDataVersion", "STEP001");
		map.put("DispatchModelType", "KMA10L-AWDBSC");
		map.put("CarType", "2");
		map.put("VIN", vin);
		map.put("NaviMaker", "DN");
		return map;
		};
	
	public List<MaTripPojo> makeVinByObsCsv(ObsClient obsClient) {
		ObsObject obsJ = obsClient.getObject("g-tbdccm-gtmc-r", "dataApp/vinlistforAvroAll.csv");
		InputStream ins = obsJ.getObjectContent();
		List<MaTripPojo> returnList = new ArrayList<MaTripPojo>();
		try {
//			FileInputStream fis = new FileInputStream(new File("D:\\home\\apuser\\datamake\\vinlistforAvroAll.csv"));
			byte[] b = new byte[4];
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				try {
					returnList.add(MaTripPojo.builder()
							.vehicle_id(tempStr.split(",")[0])
							.target_date(tempStr.split(",")[1])
							.igOff(tempStr.split(",")[2])
							.igOn(tempStr.split(",")[3])
							.build());
				} catch (Exception e) {
					System.out.println("error line:"+tempStr);
				}
				if(returnList.size() >10000) {
					return returnList;
				}
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
		/**
		 * 普通模式
		 */
		MakeAvroData md = new MakeAvroData();
		ObsClient obs = MakeAvroData.build("obs.cn-north-4.myhuaweicloud.com");
		List<MaTripPojo> seed = md.makeVinByObsCsv(obs).stream().limit(100000).collect(Collectors.toList());
//		md.makeAvroData(36L);
		//md.makeFewEntity(0, 10).forEach(System.out::println);
		/**
		 * Spark集群模式
		 */
		List<List<MaTripPojo>> vinMatrix = ComputeTools.SplitList(64, seed);
//		
		try(SparkSession spark = SparkSession.builder()
				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date())
				.getOrCreate(); 
			
			JavaSparkContext jsc = JavaSparkContext
					.fromSparkContext(spark.sparkContext());
			/**
			 * 日立环境
			 */
//			ObsClient obsClient = new ObsClient("JH5P7FMV6YLBIAAZWDZY", "LrcacFcK5NhEL1b00YCUwetOMgJ66WIr0qOf5WYi", "obs.cn-north-4.myhuaweicloud.com")
				)
		{
			JavaRDD<List<MaTripPojo>> rdd = jsc.parallelize(vinMatrix);
			rdd.mapPartitions(m->{
				List<Can300_19nev> listRsAll = new ArrayList<Can300_19nev>();
                MakeAvroData mds = new MakeAvroData();
                while(m.hasNext()) {
                	
                	List<Can300_19nev> listRs = m.next().stream().map(vin->{
                        Can300_19nev can300 = new Can300_19nev();
                        can300.setBody(mds.makeDcm19.apply(vin.getVehicle_id()));
//						can300.setBody(new Dcm19Message());
                        can300.setCorrelationId("Core001");		
                        can300.setDecoderWarningList(mds.makeDecoderWarningList(10));
                        can300.setDispatchModelType("TEST-HEV001");
                        can300.setGroupNumber(String.format("TEST%05d", (int)Math.random()*10000));
                        can300.setVehicleName("UX");
                        can300.setHeaders(mds.makeHeaderMap.apply(vin.getVehicle_id()));
                        return can300;
                        }
                    ).collect(Collectors.toList());
                	listRsAll.addAll(listRs);
                }
                return listRsAll.iterator();
            }).foreachPartition(fs->{
            	ObsClient obsPartition = MakeAvroData.build("obs.cn-north-4.myhuaweicloud.com");
//                ObsClient obsPartition = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
                DatumWriter<Can300_19nev> userDatumWriter = new SpecificDatumWriter<Can300_19nev>(Can300_19nev.class);
                List<File> upoadFile = new ArrayList<>();
                DataFileWriter<Can300_19nev> dataFileWriter = new DataFileWriter<Can300_19nev>(userDatumWriter);
            	fs.forEachRemaining(can300->{
                    try {
                    	File TempFile = File.createTempFile(can300.getHeaders().get("VIN")+"_"+can300.getHeaders().get("RequestDateTime")+"_"+Math.random()*1000000, ".avro");
                        
                        dataFileWriter.create(Can300_19nev.getClassSchema(),TempFile);
                        dataFileWriter.append(can300);
                        upoadFile.add(TempFile);
                        dataFileWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                });
                upoadFile.forEach(fsAvro->{
                	obsPartition.putObject("g-tbdccm-gtmc", "procdata/CN/real/can_External/00/2023/06/01/16/"+fsAvro.getName(), fsAvro);
                });
                obsPartition.close();
                upoadFile.forEach(fsAvro->{
                	fsAvro.delete();
                });
            });
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public static ObsClient build(String endPoint) {
    	OBSCredentialsProviderChain obsChain = new OBSCredentialsProviderChain();
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
