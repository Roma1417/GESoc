package utn.dds.tpAnual.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class ProgramadorDeTareas {
	
	private static ProgramadorDeTareas instance = new ProgramadorDeTareas();
	private Scheduler scheduler;

	private ProgramadorDeTareas() {
	}

	public static ProgramadorDeTareas getInstance() {
		return instance;
	}
	
	
	public void iniciar() throws SchedulerException {
		scheduler = new StdSchedulerFactory().getScheduler();
		JobDetail job = JobBuilder.newJob(ValidadorEgresosJob.class)
				.withIdentity("ValidadorEgresosJob", "Grupo 1")
				  .build();
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("Trigger desc 1", "Grupo 1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?")) // Ejecuta cada 15 segundos.
				.build();
		scheduler.start();
		scheduler.scheduleJob(job,trigger);
	}
	
	public void stop() throws SchedulerException {
		scheduler.clear();
	}
}
