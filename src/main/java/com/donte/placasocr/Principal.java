package com.donte.placasocr;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import com.donte.placasocr.service.SenderPlatesJob;

public class Principal {

	public static void main( String[] args ) throws Exception {
       	JobDetail job = new JobDetail();
    	job.setName("processarPlacasOcr");
    	job.setJobClass(SenderPlatesJob.class);
    	
    	SimpleTrigger trigger = new SimpleTrigger();
    	trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    	trigger.setRepeatInterval(4000);
    	trigger.setName("triggerProcessarPlacas");

    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);    	
    }

}
