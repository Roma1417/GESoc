export default class RegistroOperacion {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getOperaciones (nombreClase, tipoOperacion, { page, itemsPerPage }) {
    const params = {
      page,
      itemsPerPage,
      nombreClase,
      tipoOperacion
    }
    return this.$axios.getOrFalse('/api/registroOperacion', { params })
  }

  getTipoOperaciones () {
    return this.$axios.getOrFalse('/api/registroOperacion/tipoOperacion')
  }

  getEntidadesDeOperaciones () {
    return this.$axios.getOrFalse('/api/registroOperacion/nombreClases')
  }
}
