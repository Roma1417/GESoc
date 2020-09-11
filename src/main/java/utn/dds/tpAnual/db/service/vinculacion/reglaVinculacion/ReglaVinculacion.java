package utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

public abstract class ReglaVinculacion {
    public abstract boolean puedeVincularse(Ingreso ingreso, Egreso egreso);
}
