package zdf.learn.com.commonUtils.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 一些处理数据的方法
 * @Project       commonUtils
 * @CreatedTime   2023年3月17日
 * @Content       
 * @author        ZDF64
 *
 */
public class ComputeTools {
	
	/**
	 * 将队列平均分入指定数量的队列中
	 * @param partisionSize  分组数量
	 * @param inputList      入口队列
	 */
	public static List<List<?>> SplitList(int partisionSize, List<?> inputList) {
		List<List<?>>  returnList = new ArrayList<>();
		int startIndex = 0;
		int batchSize = inputList.size()/partisionSize;//每个子列有多少个
		int remainder = inputList.size()%partisionSize;//剩余没有分入组的
		for(int i = 0 ; i <partisionSize ; i ++ ) {
			int end = startIndex;
			List<?> child = new ArrayList<>();
			child = inputList.subList(startIndex, batchSize + (i<remainder?1:0));
			startIndex = end;
			returnList.add(child);
		}
		return returnList;
	}
}
