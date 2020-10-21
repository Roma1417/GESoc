import User from '../services/User'
import ValidationRule from '../services/ValidationRule'

export default function ({ app }, inject) {
  const user = new User({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('userService', user)
  const validationRule = new ValidationRule(app)
  inject('rl', validationRule)
}
