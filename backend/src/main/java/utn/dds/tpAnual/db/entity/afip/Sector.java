package utn.dds.tpAnual.db.entity.afip;

import java.util.Collections;
import java.util.List;

import utn.dds.tpAnual.db.entity.EntityInterface;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
import javax.persistence.*;

@Entity
@Table(name = "SECTOR")
public class Sector implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long sectorId;

	@Column(name = "NOMBRE", unique = true, nullable = false, length = 100)
	private String nombreSector;

	@OneToMany(mappedBy="sector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RequisitoSectorEmpresa> requisitos;

	public Sector(String nombreSector, List<RequisitoSectorEmpresa> requisitos) {
		super();
		this.nombreSector = nombreSector;
		this.requisitos = requisitos;
	}

	public Sector(){

	}

	public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public String getNombreSector() {
		return nombreSector;
	}

	public void setNombreSector(String nombreSector) {
		this.nombreSector = nombreSector;
	}

	public List<RequisitoSectorEmpresa> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<RequisitoSectorEmpresa> requisitos) {
		this.requisitos = requisitos;
	}

	public TamanioEmpresa getTamanioPara(int empleados, Float facturacion){
		for(RequisitoSectorEmpresa requisito : getRequisitosOrdenados()){
			if(!requisito.excedeAlgunRequisito(empleados, facturacion)){
				return requisito.getTamanioEmpresa();
			}
		}
		throw new RuntimeException("No hay ninguna categoria posible");
	}
	
	private List<RequisitoSectorEmpresa> getRequisitosOrdenados(){
		Collections.sort(requisitos);
		return requisitos;
	}
	
	public String getNombre(){
		return this.nombreSector;
	}

	@Override
	public Long getId() {
		return getSectorId();
	}
}