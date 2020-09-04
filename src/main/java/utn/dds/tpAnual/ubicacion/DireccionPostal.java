package utn.dds.tpAnual.ubicacion;

public class DireccionPostal {

	private String calle;
	private int altura;
	private int piso;
	private Ciudad ciudad;

	public DireccionPostal() {
	}

	public DireccionPostal(String calle, int altura, int piso, Ciudad ciudad) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
}
