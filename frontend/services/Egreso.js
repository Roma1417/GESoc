export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getEgresos ({ page, itemsPerPage }, categoria) {
    return this.$axios.getOrFalse('/api/transaccion/egreso', {
      params: {
        page,
        itemsPerPage,
        categoria
      }
    })
  }
}
