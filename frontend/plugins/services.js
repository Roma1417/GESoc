import User from '../services/User'
import Egreso from '../services/Egreso'
import Ingreso from '../services/Ingreso'
import ValidationRule from '../services/ValidationRule'
import Presupuesto from '~/services/Presupuesto'

export default function ({ app }, inject) {
  const user = new User({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('userService', user)
  const egreso = new Egreso({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('egresoService', egreso)
  const ingreso = new Ingreso({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('ingresoService', ingreso)
  const presupuesto = new Presupuesto({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('presupuestoService', presupuesto)
  const validationRule = new ValidationRule(app)
  inject('rl', validationRule)
}
