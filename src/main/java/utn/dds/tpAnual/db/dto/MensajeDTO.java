package utn.dds.tpAnual.db.dto;

import utn.dds.tpAnual.db.entity.usuario.Mensaje;

public class MensajeDTO extends StandardDTO<Mensaje>{

    private String asunto;

    private String cuerpo;

    public MensajeDTO(String asunto, String cuerpo){
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public MensajeDTO(){
    };

    @Override
    public StandardDTO from(Mensaje object){
        this.asunto = object.getAsunto();
        this.cuerpo = object.getCuerpo();
        return this;
    }

    @Override
    public Mensaje toEntity() {
        return null;
    }
}
