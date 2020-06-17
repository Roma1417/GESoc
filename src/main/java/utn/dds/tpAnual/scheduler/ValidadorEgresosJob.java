package utn.dds.tpAnual.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utn.dds.tpAnual.validador.ValidadorEgreso;

public class ValidadorEgresosJob implements Job {
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        ValidadorEgreso.getInstance().validarEgresos();
    }
}