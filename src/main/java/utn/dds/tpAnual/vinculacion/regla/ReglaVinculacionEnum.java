package utn.dds.tpAnual.vinculacion.regla;

public enum ReglaVinculacionEnum {

    REGLA_FECHA_ACEPTABLE(ReglaFechaAceptable.getInstance());

    private ReglaVinculacion reglaVinculacion;

    ReglaVinculacionEnum(ReglaVinculacion reglaVinculacion){
       this.reglaVinculacion = reglaVinculacion;
    }
}
