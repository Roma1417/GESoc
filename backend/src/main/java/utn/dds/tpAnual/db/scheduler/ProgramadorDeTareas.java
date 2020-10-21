package utn.dds.tpAnual.db.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.dds.tpAnual.db.service.ImportInformacionGeograficaService;
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

	@Autowired
	private ValidadorEgreso validadorEgresos;

	private ProgramadorDeTareas() {
	}

	public static ProgramadorDeTareas getInstance() {
		return instance;
	}

	@Scheduled(fixedDelay = 600000)
	public void validarEgresos(){
		validadorEgresos.validarEgresos();
	}

	//@Scheduled(fixedDelay = 600000)
	public void importPaises(){
		importInformacionGeograficaService.importPaises();
	}

	//@Scheduled(fixedDelay = 600000)
	public void importCiudades(){
		importInformacionGeograficaService.importCiudades();
	}

	//@Scheduled(fixedDelay = 600000)
	public void importEstados(){
		importInformacionGeograficaService.importEstados();
	}

	@Scheduled(fixedDelay = 600000)
	public void vincularSistema(){
		vinculador.vincularSistema();
	}
}
