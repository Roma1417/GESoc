<template>
  <TheAutocompleteInput
    :items="items"
    :loading="loading"
    hide-no-data
    :label="$t('auditoria.entidad')"
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
    this.getEntidades()
  },
  methods: {
    getEntidades () {
      this.loading = true
      this.$registroOperacionService.getEntidadesDeOperaciones().then((result) => {
        if (result) {
          this.items = result
        }
      }).finally(() => { this.loading = false })
    }
  }
}
</script>
