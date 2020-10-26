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
}
