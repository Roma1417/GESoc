package utn.dds.tpAnual.db.dto.complex;

public class VinculacionProyectoEgresoDTO {
    private Long proyectoId;
    private Long egresoId;

    public VinculacionProyectoEgresoDTO(){

    }

    public VinculacionProyectoEgresoDTO(Long proyectoId, Long egresoId) {
        this.proyectoId = proyectoId;
        this.egresoId = egresoId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Long getEgresoId() {
        return egresoId;
    }

    public void setEgresoId(Long egresoId) {
        this.egresoId = egresoId;
    }
}
