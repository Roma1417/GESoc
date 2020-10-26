export default class Categoria {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getCategorias (name, { page = 1, itemsPerPage = 20 }) {
    return this.$axios.getOrFalse('/api/categoria', {
      params: {
        page,
        itemsPerPage,
        name
      }
    })
  }
}
