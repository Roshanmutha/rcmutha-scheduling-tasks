package com.roshantest.scheduling;

import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*  
 * 
 * @author roshankumarm
   @Scheduled(cron = "0 1 1 * * ?")
   Below you can find example pattern,

		 "0 0 * * * *" = the top of every hour of every day.
		 "10 * * * * *" = every ten seconds.
		 "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
		 "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
		 "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
		 "0 0 0 25 12 ?" = every Christmas Day at midnight
		Cron expression is represented by six fields:

second, minute, hour, day of month, month, day(s) of week
The simple rules that need to be followed to annotate a method with @Scheduled are:

a method should have void return type
a method should not accept any parameters
Refer for more details: http://www.baeldung.com/spring-scheduled-tasks
*/

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask1() {
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
	}

	@Scheduled(cron = "*/10 * * * * *")
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));

		// Print out the current project classpath
		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader) cl).getURLs();

		for (URL url : urls) {
			log.info(url.getFile());
		}
	}

	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public void scheduleFixedRateWithInitialDelayTask() {

		long now = System.currentTimeMillis() / 1000;
		System.out.println("Fixed rate task with one second initial delay - " + now);
	}

	@Scheduled(fixedRate = 1000)
	public void scheduleFixedRateTask() {
		System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
	}

	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask2() {
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
	}
}
