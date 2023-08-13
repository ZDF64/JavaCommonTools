package zdf.learn.com.commonUtils.data.parquet;

import java.util.Arrays;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.FileStatusCache;
import org.apache.spark.sql.execution.datasources.HadoopFsRelation;
import org.apache.spark.sql.execution.datasources.InMemoryFileIndex;
import org.apache.spark.sql.execution.datasources.parquet.ParquetFileFormat;
import org.apache.spark.sql.types.StructType;

import lombok.extern.slf4j.Slf4j;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.collection.JavaConverters;
import scala.collection.Seq;

@Slf4j
public class DataSetReader {
	private SparkSession spark;
	private Option<StructType> userSpecifiedSchema = Option.empty();
	private scala.collection.mutable.Map<String, String> extraOptions = new scala.collection.mutable.HashMap<>();
	private Dataset<Row> cacheDs;

	public Dataset<Row> load(String[] path, FileFormat format) throws Exception {

//		long start;
//		long end;
//		scala.collection.immutable.Map<String, String> options = extraOptions.toMap(Predef.<Tuple2<String, String>>$conforms());
//
//		start = System.currentTimeMillis();
//		StructType type = format.inferSchema(spark, options, getFileStatus(path[0])).get();
//		end = System.currentTimeMillis();
//		log.info("Read schema: " + (end - start));
//		log.info("Read userSpecifiedSchema.get(): " + userSpecifiedSchema.get());
//		log.info("Read schema: " + type);
//		start = System.currentTimeMillis();
//		Seq<Path> rootPathsSpecified = JavaConverters
//				.asScalaIteratorConverter(Arrays.stream(path).map(Path::new).iterator()).asScala().toSeq();
//		// 使用PartitioningUtils创建PartitionSpec对象
//
//		InMemoryFileIndex index = new InMemoryFileIndex(spark, rootPathsSpecified, options, userSpecifiedSchema,
//				FileStatusCache.getOrCreate(spark), null, null);
//		log.info("Read InMemoryFileIndex: " + index);
//		log.info("Read InMemoryFileIndex.partitionSchema: " + index.partitionSchema());
//		end = System.currentTimeMillis();
//		log.info("Create InMemoryFileIndex: " + (end - start));
//		return spark.baseRelationToDataFrame(new HadoopFsRelation(index, index.partitionSchema(), type, Option.empty(),
//				format.getClass().newInstance(), options, spark));
		return null;

	}

	private Seq<FileStatus> getFileStatus(String filePath) throws Exception {

		FileSystem fs = FileSystem.get(new Path(filePath).toUri(), spark.sessionState().newHadoopConf());

		return JavaConverters.asScalaIteratorConverter(Arrays.asList(fs.listStatus(new Path(filePath))[0]).iterator())
				.asScala().toSeq();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(SparkSession spark = SparkSession.builder()
				.master("local[3]")
				.appName(DataSetReader.class.getName())
				.getOrCreate()){
			DataSetReader reader = new DataSetReader();
			String[] paths = new String[] {"E:\\DataStorage\\S3Parquet\\2022-10-02\\VIN0001MRS001TEST\\20221003",
					"E:\\DataStorage\\S3Parquet\\2022-10-02\\VIN0001MRS002TEST\\20221003",
					"E:\\DataStorage\\S3Parquet\\2022-10-02\\VIN0001MRS003TEST\\20221003"};
			Dataset<Row> rs = reader.load(paths,new ParquetFileFormat());
			rs.show();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
