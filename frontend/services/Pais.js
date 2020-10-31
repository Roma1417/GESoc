export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getPaises (paisName, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      paisName
    }
    return this.$axios.getOrFalse('/api/pais', { params })
  }
}
