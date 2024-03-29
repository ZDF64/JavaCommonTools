package zdf.learn.com.commonUtils.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import com.alibaba.fastjson2.JSONObject;

import lombok.extern.slf4j.Slf4j;
import zdf.learn.com.commonUtils.data.avro.schema.MaTripPojo;
import zdf.learn.com.commonUtils.tools.ComputeTools;

@Slf4j
public class DefangFileHandle {

	public String WirteUrl;
	public String readUrl;
	public String fileType;
	public String fileName;

	/**
	 * 读取指定文件内容 tips:按行读取
	 * 
	 * @param FileUrl 传入文件地址
	 * @return
	 */
	public List<String> readToLine(String FileUrl) {
		File file = new File(FileUrl);
		return readToLine(file);
	}

	/**
	 * 传入文件类
	 * 
	 * @param file 传入文件类
	 * @return 按行返回数组
	 */
	public List<String> readToLine(File file) {
		List<String> returnList = new ArrayList<String>();
		FileInputStream in = null;
		BufferedReader reader = null;
		try {
			// 一次读一个字节
			in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				returnList.add(tempStr);
			}
		} catch (IOException e) {
			log.error(" dfTool-error-001,read error,{}", e.getMessage());
			return null;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				log.error(" dfTool-error-002,read stream close error,{}", e.getMessage());
			}
		}
		return returnList;
	}

	public List<String> readToLine(InputStream fis) {
		List<String> returnList = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			// 一次读一行
			reader = new BufferedReader(new InputStreamReader(fis));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				returnList.add(tempStr);
			}
		} catch (IOException e) {
			log.error(" dfTool-error-001,read error,{}", e.getMessage());
			return null;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				log.error(" dfTool-error-002,read stream close error,{}", e.getMessage());
			}
		}
		return returnList;
	}

	public void readToLine(File file, Consumer<String> consumer) {
		FileInputStream in = null;
		BufferedReader reader = null;
		try {
			// 一次读一个字节
			in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				consumer.accept(tempStr);
			}
		} catch (IOException e) {
			log.error(" dfTool-error-001,read error,{}", e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				log.error(" dfTool-error-002,read stream close error,{}", e.getMessage());
			}
		}
	}

	public JSONObject readJsonToMap(String url) throws Exception {
		File f = new File("D:\\home\\apuser\\toyata-hcr-devOpsTool\\dbmanager\\querySql.json");
		if (!f.exists()) {
			throw new FileNotFoundException("sql json file is unreachable");
		}
		try (FileInputStream filesIn = new FileInputStream(f)) {
			DefangFileHandle df = new DefangFileHandle();
			byte[] rb = new byte[8];
			StringBuilder sbuilder = new StringBuilder();
			while (filesIn.read(rb) >= 0) {
				sbuilder.append(new String(rb));
				rb = new byte[16];
			}

			filesIn.close();
			return JSONObject.parse(sbuilder.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String readToString(String url) {

		File file = new File(url);
		StringBuilder sbuilder = new StringBuilder();
		InputStream in = null;
		try (FileInputStream filesIn = new FileInputStream(file)) {
			byte[] rb = new byte[8];
			/**
			 * 循环，每次抓取8bit数据
			 */
			while (filesIn.read(rb) >= 0) {
				sbuilder.append(new String(rb));
				rb = new byte[8];
			}
			filesIn.close();
		} catch (IOException e) {
			log.error("dfTool-error-007, read to string error,{}", e.getMessage());
			e.printStackTrace();
			return "";
		}

		return sbuilder.toString();
	}

	/**
	 * 输出长多行模式
	 * 
	 * @param outPutStrList
	 * @param lineSplite
	 * @return
	 */
	public String toWrite(List<String> outPutStrList, String lineSplite, boolean isContinue) {
		try {
			if (WirteUrl == null || WirteUrl.isEmpty()) {
				log.error("dfTool-error-009, 文件路径为空");
				return "";
			}
			File makeFile = new File(WirteUrl);
			if (!makeFile.isDirectory()) {
				makeFile.mkdirs();
			}
			String tagetUrl = WirteUrl + fileName + "." + fileType;
			log.info("tagetUrl:::{}", tagetUrl);

			toWrite(outPutStrList, tagetUrl, lineSplite, isContinue);
		} catch (Exception e) {
			log.error("dfTool-error-010, toWrite异常,{}", e.getMessage());
			e.printStackTrace();
		}

		return "";
	}

	public String testCut(String a, String b, String c) throws Exception {
		int flg = (int) (Math.random() * 20 % 7);
		System.out.println(String.format("a:%s,b:%s,c:%s", a, b, c));
		System.out.println("flg:::" + flg);
		if (flg == 1) {
			System.out.println("success");
			return "success";
		} else {
			System.out.println("error");
			throw new Exception("失败了");
		}

	}

	public String toWrite(String outPutStr, String url, boolean isContinue) {
		BufferedWriter out = null;
		try {
			if (url == null || url.isEmpty()) {
				url = "/home/apuser/defults.log";
			}
			File fs = new File(url);
			if (fs.isDirectory()) {
				url = url + "defult.log";
				fs = new File(url);
			} else if (!fs.exists()) {
				fs.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(url, isContinue));
			out.write(outPutStr);
			out.newLine();
			out.close();
		} catch (IOException e) {
			log.error("dfTool-error-1010, toWrite异常,{}", e.getMessage());
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("dfTool-error-1011, stream close error,{}", e.getMessage());
				}
			}
		}
		return outPutStr;

	}

	/**
	 * 输出长多行模式
	 * 
	 * @param outPutStrList
	 * @param lineSplite
	 * @return
	 */
	public String toWrite(List<String> outPutStrList, String url, String lineSplite, boolean isContinue) {
		BufferedWriter out = null;
		try {
			if (url == null || url.isEmpty()) {
				log.error("dfTool-error-1009, 文件路径为空");
				return "";
			}
			out = new BufferedWriter(new FileWriter(url, isContinue));
			out.write(lineSplite);
			out.newLine();
			for (String StrObj : outPutStrList) {
				out.write(StrObj);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			log.error("dfTool-error-1010, toWrite异常,{}", e.getMessage());
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("dfTool-error-1011, stream close error,{}", e.getMessage());
				}
			}
		}
		return "";
	}

	/**
	 * 输出成表格形式
	 * 
	 * @param titleList
	 * @param outPutMap
	 * @return
	 */
	public String toWriteAsTable(List<String> titleList, Map<String, List<String>> outPutMap, String baseName) {
		BufferedWriter out = null;
		try {
			if (WirteUrl == null || WirteUrl.isEmpty()) {
				return "";
			}
			File makeFile = new File(WirteUrl);
			if (!makeFile.isDirectory()) {
				makeFile.mkdirs();
			}
			String tagetUrl = WirteUrl + fileName + "." + fileType;
			out = new BufferedWriter(new FileWriter(tagetUrl, true));

			String rsStrTitle = baseName + ",";
			for (String str : titleList) {
				rsStrTitle = rsStrTitle + str + ",";
			}
			out.write(rsStrTitle);
			out.newLine();
			Set<String> keySets = outPutMap.keySet();
			for (String keystr : keySets) {
				List<String> listStr = outPutMap.get(keystr);
				String rsStr = keystr + ",";
				for (String str : listStr) {
					rsStr = rsStr + str + ",";
				}
				out.write(rsStr);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			log.error("dfTool-error-012,toWriteAsTable异常" + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("dfTool-error-013, stream close error,{}", e.getMessage());
				}
			}
		}
		return "";
	}

	/**
	 * 显示输入流中还剩的字节数
	 */
	private static void showAvailableBytes(InputStream in) {
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			log.error("dfTool-error-014,Gz parse error,{}", e.getMessage());
		}
	}

	/**
	 * 将压缩的gz文件内容读取出来，转成字符串队列
	 * 
	 * @param gzin
	 * @return
	 */
	public List<String> parseGzFileStreamToList(GZIPInputStream gzin) {
		List<String> returnList = new ArrayList<String>();
		String lenCode = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(gzin));
			if (gzin != null) {
				while ((lenCode = reader.readLine()) != null) {
					returnList.add(lenCode);
				}
			}
		} catch (Exception e) {
			log.error("dfTool-error-015,Gz parse error,{}", e.getMessage());
		}

		return returnList;
	}
	/**
	 * 文件去重
	 * @param oldPath
	 * @param newPath
	 */
	public void distinctToNew(String oldPath,String newPath) {
		readToLine(oldPath)
		.stream()
		.distinct()
		.collect(Collectors.toList())
		.forEach(ls->{
			toWrite(ls, newPath, true);
		});
	}
	
	/**
	 * 清除文件内容
	 * 
	 * @return
	 */
	public String clearTextFile() {
		BufferedWriter out = null;
		try {
			if (WirteUrl == null || WirteUrl.isEmpty()) {
				log.error("dfTool-error-013, 文件路径为空");
				return "";
			}
			File makeFile = new File(WirteUrl);
			if (!makeFile.isDirectory()) {
				makeFile.mkdirs();
			}
			String tagetUrl = WirteUrl + fileName + "." + fileType;
			out = new BufferedWriter(new FileWriter(tagetUrl, false));
			out.write("");
			out.newLine();
			out.close();
		} catch (IOException e) {
			log.error("dfTool-error-016,clearTextFile异常" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("dfTool-error-017, stream close error,{}", e.getMessage());
				}
			}
		}
		return "";
	}

	/**
	 * 删除文件
	 * 
	 * @return
	 */
	public boolean deleteFile() {
		if (WirteUrl == null || WirteUrl.isEmpty()) {
			log.error("dfTool-error-018, 文件路径为空");
			return false;
		}
		String tagetUrl = WirteUrl + fileName + "." + fileType;
		File delFile = new File(tagetUrl);
		try {
			delFile.deleteOnExit();
		} catch (Exception e) {
			log.error("dfTool-error-019, 文件删除失败{}", e);
			return false;
		}
		return true;

	}

	/**
	 * 分割文件
	 * 
	 * @param url
	 * @param size "D:\\home\\apuser\\datamake\\vinlistforAvroAll.csv"
	 */
	public void splitFile(String url, int size) {
		try {
			FileInputStream ins = new FileInputStream(new File(url));
			byte[] b = new byte[4];
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String tempStr;
			int part = 0;
			int sizeInner = 0;
			int lines = 0;
			while ((tempStr = reader.readLine()) != null) {
				try {
					if (sizeInner < size) {
						if(lines>13500000) {
							toWrite(tempStr, url.substring(0, url.lastIndexOf(".")) + part + url.substring(url.lastIndexOf("."), url.length()), true);
						}
						sizeInner++;
					} else {
						part++;
						if(lines>13500000) {
							toWrite(tempStr, url.substring(0, url.lastIndexOf(".")) + part + url.substring(url.lastIndexOf("."), url.length()), true);
						}
						sizeInner = 0;
					}
					lines ++;
				} catch (Exception e) {
					System.out.println("error line:" + tempStr);
				}
			}
//			List<String> allData = new ArrayList<>();
//			while ((tempStr = reader.readLine()) != null) {
//				allData.add(tempStr);
//			}
//			List<List<String>>  matrixList = ComputeTools.SplitList(100, allData);
//			for(List<String> child : matrixList){
//				int partcnt = part;
//				new Thread(()->{
//					for(String tempString : child) {
//						toWrite(tempString, "D:\\home\\apuser\\data\\splits\\vinList"+partcnt+url.substring( url.lastIndexOf("."),url.length()), true);
//					}
//					
//				}).start();
//				part ++;
//			}
//			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 行数统计
	 * 
	 * @param url
	 * @param baseType
	 */
	public static void fileNumberCount(String url, String baseType) {
		File dirFiles = new File(url);
		AtomicLong lines = new AtomicLong(0);
		try {
			if (dirFiles.isDirectory()) {
				File[] files = dirFiles.listFiles();
				for (File f : files) {
					lines.getAndAdd(Files.lines(f.toPath()).count());
				}

			}
			System.out.println(baseType + "行数:::" + lines);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public static void findAllFiles(String rootPath, Predicate<String> typeCheck) {
		File rootFs = new File(rootPath);
		if(rootFs.isDirectory()) {
			File[] childFs = rootFs.listFiles();
			for(File fs : childFs) {
				if(fs.isDirectory()) {
					findAllFiles(fs.getPath(),typeCheck);
				}else {
					if(typeCheck.test(fs.getPath())) {
						System.out.println(fs.getPath());
					}
					
				}
			}
		}else {
			System.out.println(rootFs.getPath() +""+rootFs.getName());
		}
	}
	
	public static void main(String[] args) {
		System.out.println("=======START=======");
		DefangFileHandle deTools = new DefangFileHandle();
		DefangFileHandle.findAllFiles("E:\\console\\can-a\\data\\outside\\",fileUrl->{
			return fileUrl.endsWith(".parquet");
		});
		System.out.println("========FIN========");
	}
}
