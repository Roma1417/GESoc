export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getPaises ({ page, itemsPerPage }, paisName) {
    const params = {
      page,
      itemsPerPage,
      paisName
    }
    return this.$axios.getOrFalse('/api/pais', { params })
  }
}
