export const numericMixin = {
  methods: {
    financial (value) {
      return Number.parseFloat(value).toFixed(2)
    }
  }
}
