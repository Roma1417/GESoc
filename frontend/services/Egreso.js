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

  getEgresosById (egresoId, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam
    }
    return this.$axios.getOrFalse('/api/transaccion/egreso/' + egresoId, { params })
  }

  crearEgreso (egreso) {
    return this.$axios.postOrFalse('/api/transaccion/egreso', egreso)
  }

  vincularEgresoIngreso (params) {
    return this.$axios.postOrFalse('/api/transaccion/vincular', params)
  }
}
