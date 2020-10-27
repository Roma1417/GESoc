export default class Categoria {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getCategorias (name, parametros = { page: 1, itemsPerPage: 20 }) {
    return this.$axios.getOrFalse('/api/categoria', {
      params: {
        page: parametros.page,
        itemsPerPage: parametros.itemsPerPage,
        name
      }
    })
  }
}
