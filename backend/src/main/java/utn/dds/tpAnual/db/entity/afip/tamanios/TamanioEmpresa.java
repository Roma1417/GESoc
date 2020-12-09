package utn.dds.tpAnual.db.entity.afip.tamanios;


import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TAMANIO_EMPRESA")
public abstract class TamanioEmpresa implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long tamanioId;

	@Column(name = "JERARQUIA", nullable = false)
	private int jerarquia;

	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;

	public TamanioEmpresa(){

	}

	public Long getTamanioId() {
		return tamanioId;
	}

	public void setTamanioId(Long tamanioId) {
		this.tamanioId = tamanioId;
	}

	public void setJerarquia(int jerarquia) {
		this.jerarquia = jerarquia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public abstract int getJerarquia();

	public abstract String getNombre();

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TamanioEmpresa
				&& this.getNombre().equals(((TamanioEmpresa)obj).getNombre());
	}

	@Override
	public Long getId() {
		return getTamanioId();
	}

}