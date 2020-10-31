export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getMonedas (monedaName, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      monedaName
    }
    return this.$axios.getOrFalse('/api/moneda', { params })
  }
}
