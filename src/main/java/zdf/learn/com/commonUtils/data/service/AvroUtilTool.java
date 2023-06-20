package zdf.learn.com.commonUtils.data.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.data.MakeAvroData;
import zdf.learn.com.commonUtils.data.avro.schema.MaTripPojo;
import zdf.learn.com.commonUtils.data.avro.schema.can300.CanFrameNumber;
import zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList;
//import zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList;
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
import zdf.learn.com.commonUtils.tools.DataMakeUtils;

/**
 * 19nev数据生成
 * @Project       CommonUtils
 * @CreatedTime   2023年6月17日
 * @Content       
 * @author        ZDF64
 *
 */
public class AvroUtilTool {
	
	public Supplier<String> createDateString = () -> {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
				.format(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
	};
	public Supplier<String> createJuneFirstString = () -> {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
				.format(LocalDateTime.parse("2023-06-01T16:20:00").atZone(ZoneId.of("Asia/Shanghai")));
	};
	/**
	 * 生成Type3数据队列
	 */
	public Function<Integer,List<Type3OutsideUseData>> makeType3OutsideUseDataList = size ->{
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
				innerDc.setDateTime(createJuneFirstString.get());
			}
			return innerDc;
		}).collect(Collectors.toList());
		
	};
	/**
	 * 生成outSide Map数据
	 */
	public Supplier<Map<CharSequence, Map<CharSequence, CharSequence>>> makeOutSideMap = () ->{
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
	
	/**
	 * 生成can info数据
	 */
	public Function<Integer,List<CanInformationList>> makeCanInformationList = size ->{
		return Stream.iterate(0, x->{return x+1;}).limit(size).map(x->{
			
			CanInformationList innerDc = new CanInformationList();
			innerDc.setCanId(x+"");
			innerDc.setCanType(10000L);
			innerDc.setCollectType(100L);
			innerDc.setDataLengthAfterCompression(10L);
//			innerDc.setTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now(ZoneId.of("Asia/Shanghai"))));
			innerDc.setTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.parse("2023-06-01T16:20:00").atZone(ZoneId.of("UTC"))));
			innerDc.setOutsideUseDataMap(makeOutSideMap.get());
			return innerDc;
		}).collect(Collectors.toList());
	};
	
	public Function<Integer,List<DataCapacity>> makeDataCapacityList = size ->{
		return Stream.iterate(0, x->{return x+1;}).limit(size).map(x->{
			DataCapacity innerDc = new DataCapacity();
			CanFrameNumber canFrame = new CanFrameNumber();
			innerDc.setCanFrameNumber(canFrame);
//			innerDc.setCanInformationList(makeCanInformationList.apply(size));
			TimeAndCoordinate tac = new TimeAndCoordinate();
			GPS gps = new GPS();
			gps.setGpsDate(createJuneFirstString.get());
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
	/**
	 * 合成DecoderWarningList队列数据
	 * @param sum
	 * @return
	 */
//	public List<DecoderWarningList> makeDecoderWarningList(int sum){
//		return Stream.iterate(0,  x->x+1)
//		.limit(sum)
//		.map(map->{
//			return DecoderWarningList.newBuilder().setMessage(MessageFormat.format("test data make @core:{0}", map)).setType(DecodeWarningStatus.UNSETTLED).build();
//		}).collect(Collectors.toList());
//	}
	/**
	 * Header数据
	 */
	public Function<String, Map<CharSequence, CharSequence>> makeHeaderMap = vin -> {
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

	/**
	 * 使用ma trip数据生成Header数据
	 */
	public Function<MaTripPojo, Map<CharSequence, CharSequence>> makeHeaderMapByMaTrip = maTrip -> {
		DataMakeUtils dmu = new DataMakeUtils();
		HashMap<CharSequence, CharSequence> map = new HashMap<CharSequence, CharSequence>();
		map.put("Company", "FTMS");
		map.put("Dest", "BJ");
		map.put("TBDC-CorrelationId", "e9c4da3b-fa66-3a25-98e5-e135eea84c34");
		map.put("NaviModel", "14");
		map.put("ServiceDeviceId", "354301119051690");
		map.put("Maker", "FTMS");
		map.put("TBDC-APIM-UserName", "TSCPPcn-north-1");
		map.put("RequestDateTime", maTrip.getIgOff() + ".000");
		map.put("CarDataVersion", "STEP001");
		map.put("DispatchModelType", "KMA10L-AWDBSC");
		map.put("CarType", "2");
		map.put("VIN", maTrip.getVehicle_id());
		map.put("NaviMaker", "DN");
		return map;
	};

	/**
	 * 生成19DCM数据模版
	 */
	public Function<String, Dcm19Message> makeDcm19 = vin -> {

		Dcm19Message dcm19 = new Dcm19Message();
		CommonHeader CHear = new CommonHeader();
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

		
	
}
