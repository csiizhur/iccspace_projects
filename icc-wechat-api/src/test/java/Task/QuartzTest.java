package Task;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;

public class QuartzTest implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Generating report - "
				+ arg0.getJobDetail().getJobBuilder() + ", type ="
				+ arg0.getJobDetail().getJobDataMap().get("type"));
		System.out.println(new Date().toString());

	}

	public static void main(String[] args) {
		try {
			// 创建一个Scheduler
			SchedulerFactory schedFact = 
			new org.quartz.impl.StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();
			// 创建一个JobDetail，指明name，groupname，以及具体的Job类名，
			//该Job负责定义需要执行任务
			//JobDetail jobDetail = new JobDetail("myJob", "myJobGroup",QuartzTest.class);
			JobDetail jobDetail= JobBuilder.newJob(QuartzTest.class).withIdentity("job_1","group_1").build();
			jobDetail.getJobDataMap().put("type", "FULL");
            // 创建一个每周触发的Trigger，指明星期几几点几分执行
			//Trigger trigger = TriggerUtils.makeWeeklyTrigger(3, 16, 38);
			Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger_1","group_1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();
			
			// 从当前时间的下一秒开始执行
			// 指明trigger的name
			// 用scheduler将JobDetail与Trigger关联在一起，开始调度任务
			sched.scheduleJob(jobDetail, trigger);
			sched.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
