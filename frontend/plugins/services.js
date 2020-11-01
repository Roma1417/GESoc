import User from '../services/User'
import Egreso from '../services/Egreso'
import Ingreso from '../services/Ingreso'
import Item from '../services/Item'
import Categoria from '../services/Categoria'
import Pais from '../services/Pais'
import Moneda from '../services/Moneda'
import Entidad from '../services/Entidad'
import ValidationRule from '../services/ValidationRule'
import Presupuesto from '~/services/Presupuesto'
import MedioPago from '../services/MedioPago'
import Proveedor from '../services/Proveedor'

export default function ({ app }, inject) {
  const user = new User({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('userService', user)
  const egreso = new Egreso({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('egresoService', egreso)
  const ingreso = new Ingreso({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('ingresoService', ingreso)
  const presupuesto = new Presupuesto({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('presupuestoService', presupuesto)
  const pais = new Pais({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('paisService', pais)
  const moneda = new Moneda({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('monedaService', moneda)
  const entidad = new Entidad({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('entidadService', entidad)
  const item = new Item({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('itemService', item)
  const categoria = new Categoria({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('categoriaService', categoria)
  const mediosPago = new MedioPago({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('medioPagoService', mediosPago)
  const proveedor = new Proveedor({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('proveedorService', proveedor)
  const validationRule = new ValidationRule(app)
  inject('rl', validationRule)
}
