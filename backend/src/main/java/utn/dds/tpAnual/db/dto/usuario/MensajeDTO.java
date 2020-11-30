package utn.dds.tpAnual.db.dto.usuario;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;

public class MensajeDTO extends StandardDTO<Mensaje> {

    private String asunto;
    private String cuerpo;

    public MensajeDTO(String asunto, String cuerpo){
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public MensajeDTO(){
    }

    @Override
    public StandardDTO from(Mensaje object){
        return new MensajeDTO(object.getAsunto(), object.getCuerpo());
    }

    @Override
    public Mensaje toEntity() {
        return null;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
