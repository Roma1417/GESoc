package utn.dds.tpAnual.vinculacion.regla;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

public abstract class ReglaVinculacion {
    public abstract boolean puedeVincularse(Ingreso ingreso, Egreso egreso);
}
