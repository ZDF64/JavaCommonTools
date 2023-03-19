package zdf.learn.com.commonUtils.tags;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryCoveryAop {
	/**
	 * 
	 * @param point
	 * @param error
	 * @return
	 */
	//execution(* zdf.learn.com.commonUtils.Files..*(..))&&  这个是用来圈定切口在哪里生效，不写就不限定
	@AfterThrowing(throwing = "error", value = "@annotation(zdf.learn.com.commonUtils.tags.RetryCovery)")
	public Object retryHandle(JoinPoint point,Throwable error) {
		System.out.println("retryHandle cut in");
		try {
			// 获得签名->转成方法签名
			MethodSignature methodSign = (MethodSignature) point.getSignature();
			Method med = point.getTarget()
					.getClass()
					.getMethod(methodSign.getName(), methodSign.getParameterTypes());
			RetryCovery retry = med.getAnnotation(RetryCovery.class);
			//获取切入方法的参数
//			for(Object obj : point.getArgs()) {
//				System.out.println("getArgs:::"+obj);
//			}
			//获取切入方法的名字
			System.out.println(methodSign.getName());
//			System.out.println(methodSign.getDeclaringType().getName());
//			System.out.println(methodSign.getReturnType().getName());
//			System.out.println("retryAOP:"+retry.name());
//			System.out.println("retryAOP:"+retry.maxRetry());
//			System.out.println("retryAOP:"+retry.retryTimeInterval());
			int startRetry = 0;
			while(error !=null && startRetry<retry.maxRetry()) {
				System.out.println("current retry cnt:"+startRetry);
				try {
					error = null;
					startRetry ++;
					String result = (String) med.invoke(methodSign.getDeclaringType().newInstance(), point.getArgs());
					System.out.println(String.format("%d time retry success:::%s",startRetry,result));
				} catch (Exception e) {
					System.out.println(String.format("%d time retry failed:::%s",startRetry,e.getCause()));
					error = e;
				}
			}
		} catch (Exception e) {
			System.out.println("aop exception"+e.getMessage());
		}
		return "123";
	}
}
