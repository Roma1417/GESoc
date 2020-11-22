export default class Ingreso {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getIngresos ({ page, itemsPerPage }) {
    return this.$axios.getOrFalse('/api/transaccion/ingreso', {
      params: {
        page,
        itemsPerPage
      }
    })
  }

  getIngresosById (idIngreso, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      idIngreso
    }
    return this.$axios.getOrFalse('/api/transaccion/ingreso/' + idIngreso, params)
      .then((result) => {
        if (result) {
          return {
            ...result,
            data: result.data
              .map(ingreso => ({ ...ingreso, idIngreso: `${ingreso.idIngreso}` }))
          }
        }
        return result
      })
  }

  crearIngreso (ingreso) {
    return this.$axios.postOrFalse('/api/transaccion/ingreso', ingreso)
  }
}
