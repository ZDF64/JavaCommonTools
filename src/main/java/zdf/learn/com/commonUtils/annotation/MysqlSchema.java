package zdf.learn.com.commonUtils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MysqlSchema {
	
	String name() default "";
	String type() default "";
	String onCall() default "";
	
	@Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
	@interface Id{
		String name() default "";
	}
	
	@Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
	@interface Column{
		String name() default "";
	}
	
}
