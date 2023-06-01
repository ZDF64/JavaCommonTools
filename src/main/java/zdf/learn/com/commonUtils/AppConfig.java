package zdf.learn.com.commonUtils;

import org.springframework.context.annotation.Bean;
/**
 * 配置类
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import zdf.learn.com.commonUtils.Files.DefangFileHandle;
import zdf.learn.com.commonUtils.tags.TimerCutAop;
//@ComponentScan("zdf.learn.com.commonUtils")
//@Configuration
public class AppConfig {
	@Bean(name="timeCutAop")
	public TimerCutAop aop() {
		return new TimerCutAop();
	}
	@Bean(name="dfTools")
	public DefangFileHandle defang() {
		return new DefangFileHandle();
	}
}
