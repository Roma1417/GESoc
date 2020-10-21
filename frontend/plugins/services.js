import User from '../service/User'
import ValidationRule from '../service/ValidationRule'

export default function ({ app }, inject) {
  const user = new User({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('userService', user)
  inject('rl', validationRule)
}
