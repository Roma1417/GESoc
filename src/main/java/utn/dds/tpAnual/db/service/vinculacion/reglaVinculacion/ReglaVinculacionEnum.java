package utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion;

public enum ReglaVinculacionEnum {

    REGLA_FECHA_ACEPTABLE(ReglaFechaAceptable.getInstance());

    private ReglaVinculacion reglaVinculacion;

    ReglaVinculacionEnum(ReglaVinculacion reglaVinculacion){
       this.reglaVinculacion = reglaVinculacion;
    }

    public ReglaVinculacion getReglaVinculacion() {
        return reglaVinculacion;
    }

    public void setReglaVinculacion(ReglaVinculacion reglaVinculacion) {
        this.reglaVinculacion = reglaVinculacion;
    }
}
