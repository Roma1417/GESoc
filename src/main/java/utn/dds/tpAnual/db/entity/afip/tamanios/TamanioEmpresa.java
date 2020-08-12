package utn.dds.tpAnual.db.entity.afip.tamanios;



/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class TamanioEmpresa {

	public TamanioEmpresa(){

	}

	public abstract int getJerarquia();

	public abstract String getNombre();

	@Override
	public boolean equals(Object obj) {
		
		return obj instanceof TamanioEmpresa
				&& this.getNombre().equals(((TamanioEmpresa)obj).getNombre());
	}
	
	
}