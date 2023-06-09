package zdf.learn.com.commonUtils.tags;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RetryCovery {
	int THREE = 3;
	int maxRetry() default  THREE;
	String name();
	int retryTimeInterval() default  30000;
}
