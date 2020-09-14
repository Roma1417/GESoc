package utn.dds.tpAnual.db.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.dds.tpAnual.db.service.validador.ValidadorEgreso;
import utn.dds.tpAnual.db.service.vinculacion.vinculador.Vinculador;

@Component
public class ProgramadorDeTareas {
	
	private static ProgramadorDeTareas instance = new ProgramadorDeTareas();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ImportInformacionGeograficaService importInformacionGeograficaService;

	@Autowired
	private Vinculador vinculador;

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
	public void importEstados(){
		importInformacionGeograficaService.importEstados();
	}

	//@Scheduled(fixedDelay = 10000)
	public void importProvincias(){
		importInformacionGeograficaService.importProvincias();
	}

	//@Scheduled(fixedDelay = 10000)
	public void vincularSistema(){
		vinculador.vincularSistema();
	}
	@Scheduled(fixedDelay = 10000)
	public void importCiudades(){importInformacionGeograficaService.importCiudades();}
}
