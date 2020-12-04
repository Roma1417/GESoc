package utn.dds.tpAnual.db.dto.complex;

public class VinculacionProyectoIngresoDTO {

    private Long ingresoId;
    private Long egresoId;

    public VinculacionProyectoIngresoDTO(){

    }

    public VinculacionProyectoIngresoDTO(Long ingresoId, Long egresoId) {
        this.ingresoId = ingresoId;
        this.egresoId = egresoId;
    }

    public Long getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(Long ingresoId) {
        this.ingresoId = ingresoId;
    }

    public Long getEgresoId() {
        return egresoId;
    }

    public void setEgresoId(Long egresoId) {
        this.egresoId = egresoId;
    }
}
