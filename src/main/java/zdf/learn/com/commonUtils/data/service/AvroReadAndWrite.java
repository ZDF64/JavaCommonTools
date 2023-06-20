package zdf.learn.com.commonUtils.data.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.specific.SpecificDatumReader;

import cn.cbdc.canconvert.avro.schema.can300.Can300_19dcm;

public class AvroReadAndWrite {
	List<String> vinlist =  Arrays.asList("JTHACABB8N8013487","JTHACABB9N8012378","JTHACABBXL8002083","JTHB11B10L3001779","JTHB11B10L3001992","JTHB11B11K2021155","JTHB11B13L3002814","JTHB11B17N2046341","JTHB11B11K2021155");
	
	/**
	 * 读取种子文件
	 * @param paths
	 * @param modifier 种子修改方法 | OPTIONAL
	 * @return
	 */
	public List<Can300_19dcm> readAvroSeed(List<String> paths,Function<Can300_19dcm,Can300_19dcm> modifier) {
		SpecificDatumReader<Can300_19dcm> datum = new SpecificDatumReader<>();
		List<Can300_19dcm> returnList = new ArrayList<>();
		for(String path : paths) {
			try {
				DataFileReader<Can300_19dcm> reader = new DataFileReader<>(new File(path), datum);
				if(modifier!=null) {
					//传入改造方法
					returnList.add(modifier.apply(reader.next()) );
				}else {
					returnList.add(reader.next());
				}
			} catch (Exception e) {
				System.err.println("read seed failed,"+e.getMessage());
			}
		}
		return returnList;
	}
	
	/**
	 * 种子扩增
	 * @param seeds 种子队列
	 * @param rate  扩增倍率
	 * @param modifier  扩增方案
	 * @return
	 */
	public List<List<Can300_19dcm>> seedAmplification(List<Can300_19dcm> seeds, int rate, Function<Can300_19dcm, Can300_19dcm> modifier){
		List<List<Can300_19dcm>> returnList = new ArrayList<>();
		for(Can300_19dcm seed : seeds) {
			List<Can300_19dcm> innerList = new ArrayList<>();
			for(int i = 0 ; i < rate ; i ++) {
				if(modifier !=null) {
					modifierVin
					.andThen(modifierRequestDateTime)
					.apply(seed);
					innerList.add(modifier.apply(seed));
				}else {
					innerList.add(seed);
				}
			}
			returnList.add(innerList);
		}
		
		return returnList;
	}
	/**
	 * 针对Vin的modifier
	 */
	public Function<Can300_19dcm, Can300_19dcm> modifierVin = can->{
		Map<CharSequence,CharSequence> header = can.getHeaders();
		for(CharSequence key : header.keySet()) {
			if(key.toString().equals("Company")) {
				header.put(key, "ftms");
			}
		}
		
		return can;
	};
	public Function<Can300_19dcm, Can300_19dcm> modifierRequestDateTime = can->{
		Map<CharSequence,CharSequence> header = can.getHeaders();
		for(CharSequence key : header.keySet()) {
			if(key.toString().equals("RequestDateTime")) {
				header.put(key, "20230616170116378");
			}
		}
		return can;
	};
}
