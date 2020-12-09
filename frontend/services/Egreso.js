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
      .then((result) => {
        if (result) {
          return {
            ...result,
            data: result.data
              .map(egreso => ({ ...egreso, idEgreso: `${egreso.idEgreso}` }))
          }
        }
        return result
      })
  }

  crearEgreso (egreso) {
    return this.$axios.postOrFalse('/api/transaccion/egreso', egreso)
  }

  vincularEgresoIngreso (vincularConEgreso, objetoAVincular, idVinculado) {
    const params = { }
    if (vincularConEgreso) {
      params.egresoId = idVinculado
      params.ingresoId = objetoAVincular.idIngreso
    } else {
      params.egresoId = objetoAVincular.idEgreso
      params.ingresoId = idVinculado
    }
    return this.$axios.postOrFalse('/api/transaccion/vincular', params)
  }
}
