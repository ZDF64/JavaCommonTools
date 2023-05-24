package zdf.learn.com.commonUtils.MultiTask.impl;

/**
 * 任务分配工厂接口
 * @Project       CommonUtils
 * @CreatedTime   2023年5月4日
 * @Content       
 * @author        ZDF64
 *
 */
public interface BatchFactoryImpl {
	public void start();
	public String status();
	public void end();
}
