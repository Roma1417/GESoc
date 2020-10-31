export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getMonedas ({ page, itemsPerPage }, monedaName) {
    const params = {
      page,
      itemsPerPage,
      monedaName
    }
    return this.$axios.getOrFalse('/api/moneda', { params })
  }
}
