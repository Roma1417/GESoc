<template>
  <TheAutocompleteInput
    :items="items"
    :loading="loading"
    hide-no-data
    :label="$t('auditoria.tipo_operacion')"
    v-bind="$attrs"
    v-on="$listeners"
  />
</template>
<script>
export default {
  data: () => ({
    loading: false,
    items: []
  }),
  mounted () {
    this.getTiposOperaciones()
  },
  methods: {
    getTiposOperaciones () {
      this.loading = true
      this.$registroOperacionService.getTipoOperaciones().then((result) => {
        if (result) {
          this.items = result
        }
      }).finally(() => { this.loading = false })
    }
  }
}
</script>
