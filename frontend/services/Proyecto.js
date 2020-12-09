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

  vincularProyecto (vincularConEgreso, objetoAVincular, idVinculado) {
    const extraPath = vincularConEgreso ? 'vincularEgreso' : 'vincularIngreso'
    const params = {
      proyectoId: objetoAVincular.id,
      egresoId: idVinculado,
      ingresoId: idVinculado
    }
    return this.$axios.postOrFalse(`/api/transaccion/${extraPath}`, params)
  }
}
