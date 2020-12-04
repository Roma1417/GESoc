package utn.dds.tpAnual.db.dto.complex;

public class VinculacionProyectoIngresoDTO {

    private Long proyectoId;
    private Long ingresoId;

    public VinculacionProyectoIngresoDTO(){

    }

    public VinculacionProyectoIngresoDTO(Long proyectoId, Long ingresoId) {
        this.proyectoId = proyectoId;
        this.ingresoId = ingresoId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Long getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(Long ingresoId) {
        this.ingresoId = ingresoId;
    }
}
