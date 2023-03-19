package zdf.learn.com.commonUtils.data;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

import redis.clients.jedis.Jedis;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Can300_19nev;
import zdf.learn.com.commonUtils.data.avro.schema.can300.CommonHeader;
import zdf.learn.com.commonUtils.data.avro.schema.can300.DataCapacity;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Dcm19Message;
import zdf.learn.com.commonUtils.data.avro.schema.can300.UpdCondition;
import zdf.learn.com.commonUtils.data.avro.schema.can300.VehicleInformationHeader;
import zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecodeWarningStatus;
import zdf.learn.com.commonUtils.data.avro.schema.can300_19nev.DecoderWarningList;
import zdf.learn.com.commonUtils.tools.ComputeTools;
public class MakeAvroData {
//	private static Broadcast<String> broadcastSchema;
//	public StructType structType ;
//	public List<StructType> StructTypeList = new ArrayList<StructType>();
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
        	List<Can300_19nev> all = makeEntity(0,sum);
        	all.addAll(makeEntity(1,sum));
        	all.addAll(makeEntity(2,sum));
        	all.addAll(makeEntity(3,sum));
        	System.out.println("all::::"+all.size());
        	DatumWriter<Can300_19nev> userDatumWriter = new SpecificDatumWriter<Can300_19nev>(Can300_19nev.class);
           
            List<List<?>> rsList = ComputeTools.SplitList(12, all);
            System.out.println("splite large list into:" + rsList.size());
            for(List<?> listChild :rsList) {
            	new Thread(()->{
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
						System.out.println("make save for :"+listChild.size());
						dataFileWriter.close();
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
	
	public List<Can300_19nev> makeEntity(int indexRedis,long sum) {
		return makeVinByRedis(indexRedis,sum).stream().map(vin->{
			Can300_19nev can300 = new Can300_19nev();
			
			can300.setBody(makeDcm19.apply(vin));
			can300.setCorrelationId("Core001");		
			can300.setDecoderWarningList(makeDecoderWarningList(10));
			can300.setDispatchModelType("TEST-HEV001");
			can300.setGroupNumber("TEST00001");
			can300.setVehicleName("UX");
			can300.setHeaders(makeHeaderMap.apply(vin));
			return can300;}).collect(Collectors.toList());		
	}
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
		List<DataCapacity> dcList = new ArrayList<DataCapacity>();
		dcm19.setDataCapacityList(dcList);
		UpdCondition upd = new UpdCondition();
		dcm19.setUpdCondition(upd);
		VehicleInformationHeader vinInfo = new VehicleInformationHeader();
		dcm19.setVehicleInformationHeader(vinInfo);
		return dcm19;
	};
	Function<String,Map<CharSequence, CharSequence>> makeHeaderMap =  vin -> {
		HashMap<CharSequence, CharSequence> map = new HashMap<CharSequence, CharSequence>();
		map.put("Company", "TMCI");
		map.put("Dest", "CN");
		map.put("TBDC-CorrelationId", "e9c4da3b-fa66-3a25-98e5-e135eea84c34");
		map.put("NaviModel", "14");
		map.put("ServiceDeviceId", "354301119051690");
		map.put("Maker", "LEXUS");
		map.put("TBDC-APIM-UserName", "TSCPPcn-north-1");
		map.put("RequestDateTime", "20220912085702247");
		map.put("CarDataVersion", "STEP001");
		map.put("DispatchModelType", "KMA10L-AWDBSC");
		map.put("CarType", "2");
		map.put("VIN", vin);
		map.put("NaviMaker", "DN");
		return map;
		};
	
	
	public List<String> makeVinByRandom() {
		return null;
	}
	
	public List<String> makeVinByRedis(int indexRedis ,long sum) {
		Jedis javaRedis = new Jedis("192.168.3.73",6379);
		javaRedis.select(indexRedis);
		return javaRedis.keys("*").stream().limit(sum).map(m->{
			return m.split("_").length==2?m.split("_")[1]:"TESTVINXXXXX00000001";
		}).collect(Collectors.toList());
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
			DecoderWarningList dwl= new DecoderWarningList();
			dwl.setMessage(MessageFormat.format("test data make @core:{0}", map));
			dwl.setType(DecodeWarningStatus.UNSETTLED);
			return dwl;
		}).collect(Collectors.toList());
	}
	
	
	
	
	public static void main(String[] args) {
		MakeAvroData md = new MakeAvroData();
		md.makeAvroData(2500000L);
		
//		try(SparkSession spark = SparkSession.builder()
//				.appName(MakeAvroData.class.getSimpleName() + " - " + new Date())
//				.master("local[4]")
//				.getOrCreate(); 
//			JavaSparkContext jsc = JavaSparkContext
//					.fromSparkContext(spark.sparkContext());
//			ObsClient obsClient = new ObsClient("JH5P7FMV6YLBIAAZWDZY", "LrcacFcK5NhEL1b00YCUwetOMgJ66WIr0qOf5WYi", "obs.cn-north-4.myhuaweicloud.com")){
//			
//			
//			ObsObject objs = obsClient.getObject("spark-can-data", "Avro/modal.avro");
//			System.out.println("objs"+objs.getObjectKey());
//			InputStream in = objs.getObjectContent();
//			DataFileStream<Object> dfs = new DataFileStream<>(in, new GenericDatumReader<>());
//			System.out.println(dfs.getSchema().toString());
//			md.readAvro(spark, "E:\\DataStorage\\S3Avro\\newFile\\data.avro");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
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
//DataFileStream<Object> dfs = new DataFileStream<>(new FileInputStream(new File(modelArvoUrl)),new GenericDatumReader<>());
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
