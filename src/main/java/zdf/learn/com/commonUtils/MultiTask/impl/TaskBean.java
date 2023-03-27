package zdf.learn.com.commonUtils.MultiTask.impl;

import java.util.function.Consumer;

/**
 * 任务封装的基本单元
 * @Project       CommonUtils
 * @CreatedTime   2023年3月23日
 * @Content       
 * @author        ZDF64
 * @param <V>
 *
 */
public interface TaskBean{
	public <V, T> V compute(Consumer<T> callback);
	
}
