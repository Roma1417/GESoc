export default class RegistroOperacion {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getOperaciones (entidad, tipoOperacion, { page, itemsPerPage }) {
    const params = {
      page,
      itemsPerPage,
      entidad,
      tipoOperacion
    }
    return this.$axios.getOrFalse('/api/registroOperacion', { params })
  }

  getTipoOperaciones (name, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      name
    }
    return this.$axios.getOrFalse('/api/xxxxx/', { params })
  }

  getEntidadesDeOperaciones (name, pageParam = { page: 1, itemsPerPage: 20 }) {
    const params = {
      ...pageParam,
      name
    }
    return this.$axios.getOrFalse('/api/xxxxx/', { params })
  }
}
