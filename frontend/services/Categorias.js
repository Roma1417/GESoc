export default class Categoria {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getItems (name, { page = 1, itemsPerPage = 20}) {
    return this.$axios.getOrFalse('/api/categoria', {
      params: {
        page,
        itemsPerPage,
        name
      }
    })
  }
}
