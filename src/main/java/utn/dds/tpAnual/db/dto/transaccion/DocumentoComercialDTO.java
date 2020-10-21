package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

public class DocumentoComercialDTO extends StandardDTO<DocumentoComercial> {
    private Integer numero;
    private Integer tipoDocumento;
    private PaisDTO pais;
    private MonedaDTO moneda;

    public DocumentoComercialDTO(){

    }

    @Override
    public DocumentoComercialDTO from(DocumentoComercial object) {
        return null;
    }

    @Override
    public DocumentoComercial toEntity() {
        return null;
    }

    public DocumentoComercial toEntity(Pais pais, Moneda moneda){
        DocumentoComercial documentoComercial = new DocumentoComercial();
        documentoComercial.setMoneda(moneda);
        documentoComercial.setPais(pais);
        documentoComercial.setNumero(numero);
        documentoComercial.setTipoDocumento(tipoDocumento);
        return documentoComercial;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
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
