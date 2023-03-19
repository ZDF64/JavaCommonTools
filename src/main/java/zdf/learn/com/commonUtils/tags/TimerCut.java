package zdf.learn.com.commonUtils.tags;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import zdf.learn.com.commonUtils.tags.enums.MonitorType;

@Target({ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TimerCut {
	String name();
	MonitorType type();
}