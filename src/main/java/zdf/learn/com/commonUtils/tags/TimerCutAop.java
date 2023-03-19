package zdf.learn.com.commonUtils.tags;

import java.lang.reflect.Method;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSON;

@Aspect
@Component
public class TimerCutAop {

////	@Around(value = "within(zdf.learn.com.commonUtils..*)",argNames = "point, timer")
//	@Around(value = "execution(* zdf.learn.com.commonUtils.tags..*(TimerCut))  && args(timer)",argNames = "point, timer")
////	@Around("modifyReturn()")
////	@Around("within(zdf.learn.com.commonUtils..*)")
//	public Object monitor(ProceedingJoinPoint joinPoint,TimerCut timer) {
//		System.out.println("start to timer");
//		long start = System.currentTimeMillis();
//		Object result = null;
//		try {
//			System.out.println("start to cut"+JSON.toJSONString(joinPoint.getArgs()));
//			result = joinPoint.proceed();
//			System.out.println("cut fin :"+result);
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(timer.type().equals(MonitorType.TIMER)) {
//			Long end = System.currentTimeMillis();
//			System.out.println(String.format("Target %s finished,time cost: [%d]ms",timer.name(), (end-start)));
//		}
//		return result;
//	}
//	@Before("execution(* zdf.learn.com.commonUtils.Files.DefangFileHandle.*(..))")
//	public void before() {
//		System.out.println("=====方法执行前======");
//	}
//
//	@After("execution(* zdf.learn.com.commonUtils.Files.DefangFileHandle.*(..))")
//	public void after() {
//		System.out.println("=====方法执行后======");
//	}

	// 在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点；
	@Around(value = "execution(* zdf.learn.com.commonUtils.Files.DefangFileHandle.*(..)) && @annotation(zdf.learn.com.commonUtils.tags.TimerCut)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();// 获得签名
		MethodSignature method = (MethodSignature) signature;
		Method med = joinPoint.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
		TimerCut cut = med.getAnnotation(TimerCut.class);
		System.out.println(String.format("start to timer:name-[%s],type-[%s]", cut.name(),cut.type()));
		long start = System.currentTimeMillis();
		Object result = null;
		try {
			System.out.println("in params :"+JSON.toJSONString(joinPoint.getArgs()));
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Long end = System.currentTimeMillis();
		System.out.println(String.format("Target %s finished,time cost: [%d]ms","type", (end-start)));
		return result;
	}

}
