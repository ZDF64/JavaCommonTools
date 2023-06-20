package zdf.learn.com.commonUtils.data.service;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import zdf.learn.com.commonUtils.data.inf.AvroDataHandleInf;

public class AvroUtilService implements AvroDataHandleInf {
//
//	@Override
//	public <T> Dataset<Row> makeAvro(SparkSession spark, JavaRDD<List<T>> rdd) {
//		JavaRDD<InternalRow> rows = rdd.mapPartitions(m -> {
//			AvroUtilTool mds = new AvroUtilTool();
//			List<InternalRow> listRsAll = new ArrayList<InternalRow>();
//            while(m.hasNext()) {
//            	List<InternalRow> listRs = m.next().stream().map(vin->{
//            		MaTripPojo trip = (MaTripPojo) vin;
//                    Can300_19nev can300 = new Can300_19nev();
//                    can300.setBody(mds.makeDcm19.apply(trip.getVehicle_id()));
//                    can300.setCorrelationId("Core001");		
//                    can300.setDecoderWarningList(mds.makeDecoderWarningList(10));
//                    can300.setDispatchModelType("TEST-HEV001");
//                    can300.setGroupNumber(String.format("TEST%05d", (int)Math.random()*10000));
//                    can300.setVehicleName("UX");
//                    can300.setHeaders(mds.makeHeaderMapByMaTrip.apply(trip));
//                    DataType dataType = SchemaConverters.toSqlType(Can300_19nev.SCHEMA$).dataType();
//                    Option<Object> obsEle = new AvroDeserializer(Can300_19nev.SCHEMA$, dataType).deserialize(can300);
//                    
//                    return (InternalRow) obsEle.get();
//                    }
//                ).collect(Collectors.toList());
//            	 System.out.println("make data mapPartitions listRs: "+listRs.size());
//            	listRsAll.addAll(listRs);
//            }
//            return listRsAll.iterator();
// 		});
//		
//		return spark.internalCreateDataFrame(rows.rdd(), (StructType) SchemaConverters.toSqlType(Can300_19nev.SCHEMA$).dataType(), spark.internalCreateDataFrame$default$3());
//	}



//        	ObsClient obsPartition = MakeAvroData.build("obs.cn-north-4.myhuaweicloud.com");
////            ObsClient obsPartition = new ObsClient("KHTCNRDCRQGCAQGGMLQX", "wN9ePh0A3JayYMQSXE3xNRWnga5A19FS3Kpwen5q", "obs.cn-north-4.myhuaweicloud.com");
//            DatumWriter<Can300_19nev> userDatumWriter = new SpecificDatumWriter<Can300_19nev>(Can300_19nev.class);
//            List<File> upoadFile = new ArrayList<>();
//            DataFileWriter<Can300_19nev> dataFileWriter = new DataFileWriter<Can300_19nev>(userDatumWriter);
//            DataMakeUtils mdu = new DataMakeUtils();
//        	fs.forEachRemaining(can300->{
//                try {
//                	File TempFile = File.createTempFile(can300.getHeaders().get("VIN")+"_"+Math.random()*1000000, ".avro");
//                    
//                    dataFileWriter.create(Can300_19nev.getClassSchema(),TempFile);
//                    dataFileWriter.append(can300);
//                    upoadFile.add(TempFile);
//                    dataFileWriter.flush();
//	            	obsPartition.putObject("g-tbdccm-gtmc", "procdata/CN/real/can_External/"+mdu.makeHexStrByMax.apply(255)+"/2023/06/01/16/"+TempFile.getName(), TempFile);
////                    obsPartition.putObject("b-tbdccm-gtmc", "procdata/CN/real/can_External/"+mdu.makeHexStrByMax.apply(255)+"/2023/06/01/16/"+TempFile.getName(), TempFile);
//                    dataFileWriter.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } 
//            });

	@Override
	public void uploadAvro(Dataset<Row> ds, String savePrefix) {
		// TODO Auto-generated method stub
		ds.write().format("avro").save(savePrefix);
	}



	@Override
	public <T> Dataset<Row> makeAvro(SparkSession spark, JavaRDD<List<T>> rdd) {
		// TODO Auto-generated method stub
		return null;
	}

}
