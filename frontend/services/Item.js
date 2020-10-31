export default class Item {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getItems ({ page, itemsPerPage }, name) {
    return this.$axios.getOrFalse('/api/item/item', {
      params: {
        page,
        itemsPerPage,
        name
      }
    })
  }

  vincularItemCategoria (item) {
    return this.$axios.putOrFalse('/api/item/categoria', item)
  }
}
