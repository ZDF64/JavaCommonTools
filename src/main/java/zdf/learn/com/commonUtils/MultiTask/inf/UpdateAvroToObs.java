package zdf.learn.com.commonUtils.MultiTask.inf;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;

import lombok.extern.slf4j.Slf4j;
import zdf.learn.com.commonUtils.data.avro.schema.can300.Can300_19nev;
import zdf.learn.com.commonUtils.tools.DataMakeUtils;
@Slf4j
public class UpdateAvroToObs extends RecursiveTask<String> {
	static String requiredLabels = "Speed,Speed_TypeA,Speed_TypeB,FuelConsumption,AccelPedalAngle_TypeA,AccelPedalAngle_TypeC,AccelPedalAngle_TypeB,AccelerationFB,AccelerationFB_TypeB,AccelerationFB_TypeC,EcoModeIndicator,DriveModeECO,DriveModeECO_TypeB,SportModeSelect,DriveModeSPORT,DriveModeSPORT_TypeB,PowerModeSelect_TypeA,PowerModeSelect_TypeB,DriveModePOWER,DriveModePOWER_TypeB,SnowModeSelect,RecIndicator,WiperControl,Odometer_km,AirConIndicator";
	public static String bucketName = "g-tbdccm-gtmc";
	public static String prefixUrl = "procdata/CN/real/can_External/";
	/**
	 * 
	 */
	private ObsClient obsCli;
	private String obsBucket;
	private List<ObsObject> modelfiles;
	private static final long serialVersionUID = -4993137728686920876L;
	private int partitionSize;
	List<Can300_19nev> sendSeed = new ArrayList<>();
	public UpdateAvroToObs(ObsClient obsCli,String obsBucket,List<ObsObject> modelfiles,int partitionSize) {
		this.setForkJoinTaskTag((short) Math.round(Math.random()*20000));
		this.obsCli = obsCli;
		this.obsBucket = obsBucket;
		this.modelfiles = modelfiles;
		this.partitionSize = partitionSize;
	}
	@Override
	protected String compute(){
		
		if(modelfiles.size()>partitionSize) {
			List<ObsObject> left = new ArrayList<>();
			List<ObsObject> right = new ArrayList<>();
			for(int i = 0 ; i < modelfiles.size(); i++) {
				if(i%2 ==1) {
					left.add(modelfiles.get(i));
				}else{
					right.add(modelfiles.get(i));
				}
			}
			UpdateAvroToObs leftTask = new UpdateAvroToObs(obsCli,obsBucket,left,partitionSize);
			UpdateAvroToObs rightTask = new UpdateAvroToObs(obsCli,obsBucket,right,partitionSize);

			leftTask.fork();
			rightTask.fork();
			return leftTask.join() + rightTask.join();
		}else {
			for(ObsObject modelfile : modelfiles) {
				try {
					InputStream in = modelfile.getObjectContent();
					DataFileStream<Can300_19nev> dfs = new DataFileStream<>(in, new GenericDatumReader<>());
					while(dfs.hasNext()) {
						Can300_19nev model = dfs.next();
						sendSeed.add(updateCan(model));
					}
					dfs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return "";
	}
	public Can300_19nev updateCan(Can300_19nev in) {
		DataMakeUtils dmu = new DataMakeUtils();
		Map<CharSequence, CharSequence>  headers = in.getHeaders();
		headers.put("VIN", dmu.makeVin.get());
		in.setHeaders(headers);
		return in;
	}
	
	public void sendObs(ObsClient obs,List<Can300_19nev> sendSeed) {
		DataMakeUtils mdu = new DataMakeUtils();
		DatumWriter<Can300_19nev> userDatumWriter = new SpecificDatumWriter<Can300_19nev>(Can300_19nev.class);
		DataFileWriter<Can300_19nev> dataFileWriter = new DataFileWriter<Can300_19nev>(userDatumWriter);
		for(Can300_19nev can300 :sendSeed) {
			try {
				File TempFile = File.createTempFile(can300.getHeaders().get("VIN")+"_"+can300.getHeaders().get("DispatchModelType")+"-"+((long)Math.random()*10000000000L), ".avro");
				dataFileWriter.create(Can300_19nev.getClassSchema(),TempFile);
				dataFileWriter.append(can300);
                dataFileWriter.flush();
				obs.putObject(bucketName, prefixUrl+mdu.makeHexStrByMax.apply(255)+"/2023/06/14/08/"+TempFile.getName(),TempFile);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		try {
			dataFileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	public static void main(String[] args) {

	}

	
}
