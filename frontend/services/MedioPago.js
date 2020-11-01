export default class MedioPago {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getMediosDePago (medioPagoName, pageParam = { page: 1, itemsPerPage: 20 }) {
    return this.$axios.getOrFalse('/api/MedioPago', {
      params: {
        ...pageParam,
        medioPagoName
      }
    })
  }
}
