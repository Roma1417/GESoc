export default class Proveedor {
  constructor (ctx) {
    this.$axios = ctx.$axios
  }

  getProveedores (proveedorName, pageParam = { page: 1, itemsPerPage: 20 }) {
    return this.$axios.getOrFalse('/api/proveedor', {
      params: {
        ...pageParam,
        proveedorName
      }
    })
  }
}
