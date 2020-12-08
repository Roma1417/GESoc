export default class ProyectoFinanciamiento {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getProyectos ({ page, itemsPerPage }) {
    return this.$axios.getOrFalse('/api/transaccion/proyecto', {
      params: {
        page,
        itemsPerPage
      }
    })
  }

  crearProyecto (proyecto) {
    return this.$axios.postOrFalse('/api/transaccion/proyecto', proyecto)
  }
}
