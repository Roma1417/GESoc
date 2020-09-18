package utn.dds.tpAnual.builders;

import org.apache.tomcat.jni.Local;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCompra.CriterioCompra;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCompra.CriterioMenorPrecio;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IngresoBuilder {
	
	private int codigoOperacion;
	private DocumentoComercial documentoComercial;
	private Entidad entidadRealizadora;
	private LocalDate fecha;
	private String descripcion;
	private Float total;
	private List<Egreso> EgresosAsociados = new ArrayList<Egreso>();

    public IngresoBuilder withCodigoOperacion(int codigoOperacion){
        this.codigoOperacion = codigoOperacion;
        return this;
    }

    public IngresoBuilder withDocumentoComercial(DocumentoComercial documentoComercial) {
		this.documentoComercial = documentoComercial;
		return this;
	}

	public IngresoBuilder withEntidadRealizadora(Entidad entidadRealizadora) {
		this.entidadRealizadora = entidadRealizadora;
		return this;
	}

	public IngresoBuilder withFecha(LocalDate fecha) {
		this.fecha = fecha;
		return this;
	}

	public IngresoBuilder withDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public IngresoBuilder withTotal(Float total) {
		this.total = total;
		return this;
	}

	public IngresoBuilder withEgresosAsociados(List<Egreso> EgresosAsociados) {
		this.EgresosAsociados = EgresosAsociados;
		return this;
	}
    
    public Ingreso build(){
        return new Ingreso(documentoComercial, entidadRealizadora, codigoOperacion, total, descripcion, EgresosAsociados);
    }
    

    public Ingreso buildIngresoCompletoWithFecha(LocalDate localDate){
		codigoOperacion = 124312;

		documentoComercial = new DocumentoComercial();
		documentoComercial.setNumero(12312);
		documentoComercial.setTipoDocumento(5);

		entidadRealizadora = null;
		fecha = localDate;
		descripcion = "Ingreso de prueba";
		total = 100000F;

		return build();
	}

	public Ingreso buildIngresoCompleto(){
		return buildIngresoCompletoWithFecha(LocalDate.now());
	}
}
