export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getEntidades (medioPagoName, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      medioPagoName
    }
    return this.$axios.getOrFalse('/api/entidad', { params })
  }
}
