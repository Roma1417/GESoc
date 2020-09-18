package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;

public enum CriterioVinculacionEnum {

    CRITERIO_VINCULACION_FECHA("CRITERIO_VINCULACION_FECHA", CriterioVinculacionFecha.getInstance());

    private String nombreCriterio;
    private CriterioVinculacion criterioVinculacion;

    CriterioVinculacionEnum(String nombreCriterio, CriterioVinculacion criterioVinculacion) {
        this.nombreCriterio = nombreCriterio;
        this.criterioVinculacion = criterioVinculacion;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public void setNombreCriterio(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
    }

    public CriterioVinculacion getCriterioVinculacion() {
        return criterioVinculacion;
    }

    public void setCriterioVinculacion(CriterioVinculacion criterioVinculacion) {
        this.criterioVinculacion = criterioVinculacion;
    }
}
