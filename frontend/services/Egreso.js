export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getEgresos ({ page, itemsPerPage }, categoriasList) {
    const params = {
      page,
      itemsPerPage
    }
    if (categoriasList && categoriasList.length) {
      params.categorias = categoriasList.map(categoria => categoria.id).join(',')
    }
    return this.$axios.getOrFalse('/api/transaccion/egreso', { params })
  }
}
