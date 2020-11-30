export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getEntidades (name, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      name
    }
    return this.$axios.getOrFalse('/api/entidad/', { params })
  }
}
