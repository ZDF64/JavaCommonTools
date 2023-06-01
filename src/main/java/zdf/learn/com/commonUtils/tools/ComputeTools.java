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
	 * @param <T>
	 * @param partisionSize  分组数量
	 * @param inputList      入口队列
	 */
	public static <T> List<List<T>> SplitList(int partisionSize, List<T> inputList) {
		List<List<T>>  returnList = new ArrayList<>();
		int startIndex = 0;
		int batchSize = inputList.size()/partisionSize;//每个子列有多少个
		int remainder = inputList.size()%partisionSize;//剩余没有分入组的
		for(int i = 0 ; i <partisionSize ; i ++ ) {
			int end = startIndex;
			List<T> child = new ArrayList<>();
			child = new ArrayList<>(inputList.subList(startIndex,startIndex+ batchSize + (i<remainder?1:0)));
			startIndex = end +child.size() ;
			returnList.add(child);
		}
		return returnList;
	}
}
