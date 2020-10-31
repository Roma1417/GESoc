export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getEntidades ({ page, itemsPerPage }, entidadName) {
    const params = {
      page,
      itemsPerPage,
      entidadName
    }
    return this.$axios.getOrFalse('/api/entidad', { params })
  }
}
