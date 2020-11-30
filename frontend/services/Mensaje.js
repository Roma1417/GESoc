export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getMensajes (pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam
    }
    return this.$axios.getOrFalse('/api/user/mensajes', { params })
  }
}
