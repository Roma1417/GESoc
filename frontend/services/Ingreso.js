export default class Ingreso {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getIngresos ({ page, itemsPerPage }) {
    return this.$axios.getOrFalse('/api/transaccion/ingreso', {
      params: {
        page,
        itemsPerPage
      }
    })
  }

  crearIngreso (ingreso) {
    return this.$axios.postOrFalse('/api/transaccion/ingreso', ingreso)
  }
}
