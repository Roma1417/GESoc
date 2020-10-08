package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;

public class DocumentoComercialDTO extends StandardDTO<DocumentoComercial> {
    private int numero;
    private int tipoDocumento;
    private PaisDTO pais;
    private MonedaDTO moneda;

    public DocumentoComercialDTO(){

    }

    @Override
    public StandardDTO from(DocumentoComercial object) {
        return null;
    }

    @Override
    public DocumentoComercial toEntity() {
        return null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

    public MonedaDTO getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaDTO moneda) {
        this.moneda = moneda;
    }
}
