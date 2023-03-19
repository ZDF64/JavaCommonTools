package zdf.learn.com.commonUtils.data;

import java.nio.charset.Charset;

import com.google.protobuf.ByteString;
import com.google.protobuf.util.JsonFormat;

import zdf.learn.com.commonUtils.data.protobuf.FileBean;
import zdf.learn.com.commonUtils.data.protobuf.FileBean.Builder;

public class ProtoData {

	public static void main(String[] args) {
		Builder beanFile = FileBean.newBuilder();
		FileBean file = beanFile.setFile(ByteString.copyFrom("测试", Charset.forName("UTF-8"))).setFileName("测试名字").build();
		try {
			//反序列化
			FileBean demo1 = FileBean.parseFrom(file.toByteArray());
	        //转 json
			String jsonObject = JsonFormat.printer().print(demo1);
			System.out.println("Json格式化结果:\n" + jsonObject);
		    System.out.println("Json格式化数据大小: " + jsonObject.getBytes().length);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
