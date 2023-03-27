package zdf.learn.com.commonUtils.MultiTask.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import zdf.learn.com.commonUtils.MultiTask.impl.TaskBean;

public class TaskMultiInvocationHandler implements InvocationHandler{

	//被代理类的对象-这里使用泛型，可以灌入多种目标对象，实现灵活的代理任务
	private TaskBean target;
	
	public TaskMultiInvocationHandler(TaskBean target){
		this.target = target;
	}
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),new Class<?>[] {TaskBean.class}, this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		
		
		return method.invoke(target, args);
	}
	
}
