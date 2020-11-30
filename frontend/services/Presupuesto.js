export default class Presupuesto {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getPresupuestos ({ page, itemsPerPage }) {
    return this.$axios.getOrFalse('/api/transaccion/presupuesto', {
      params: {
        page,
        itemsPerPage
      }
    })
  }

  crearPresupuesto (presupuesto) {
    return this.$axios.postOrFalse('/api/transaccion/presupuesto', presupuesto)
  }
}
