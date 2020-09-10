package utn.dds.tpAnual.db.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.dds.tpAnual.criterioCompra.ValidadorEgreso;

@Component
public class ProgramadorDeTareas {
	
	private static ProgramadorDeTareas instance = new ProgramadorDeTareas();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ImportInformacionGeograficaService importInformacionGeograficaService;

	private ProgramadorDeTareas() {
	}

	public static ProgramadorDeTareas getInstance() {
		return instance;
	}

	@Scheduled(fixedDelay = 2000)
	public void validarEgresos(){
		ValidadorEgreso.getInstance().validarEgresos();
	}

	@Scheduled(fixedDelay = 10000)
	public void importPaises(){
		importInformacionGeograficaService.importPaises();
	}

	@Scheduled(fixedDelay = 10000)
	public void importProvincias(){
		importInformacionGeograficaService.importProvincias();
	}
}
