export default class Item {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getItems (name, pageParam = { page: 1, itemsPerPage: 20 }) {
    return this.$axios.getOrFalse('/api/item/item', {
      params: {
        ...pageParam,
        name
      }
    })
  }

  vincularItemCategoria (item) {
    return this.$axios.putOrFalse('/api/item/categoria', item)
  }
}
