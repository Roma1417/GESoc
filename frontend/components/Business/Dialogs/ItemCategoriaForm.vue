<template>
  <TheFormDialog
    v-model="mostrar"
    :header-message="$t('items.categorias.form')"
    :loading="loading"
    v-bind="$attrs"
    confirm-button="buttons.confirmButton"
    cancel-button="buttons.cancelButton"
    v-on="$listeners"
    @onConfirm="confirm"
    @onCancel="closeDialog"
  >
    <template #activator="{ on }">
      <TheButtonWithToolTip
        icon="mdi-pencil"
        title="categorias.editar"
        v-on="on"
      />
    </template>
    <TheCategoriasAutocomplete
      v-model="item.categorias"
      :disabled="loading"
    />
  </TheFormDialog>
</template>

<script>
import { cloneDeep } from 'lodash'
import TheCategoriasAutocomplete from '~/components/Business/Autocomplete/TheCategoriasAutocomplete'
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheButtonWithToolTip from '~/components/General/Buttons/TheButtonWithTooltip'
export default {
  components: {
    TheCategoriasAutocomplete,
    TheFormDialog,
    TheButtonWithToolTip
  },
  props: {
    originalItem: {
      type: Object,
      required: true
    }
  },
  data: () => ({
    mostrar: false,
    loading: false,
    item: {}
  }),
  watch: {
    mostrar (val) {
      if (val) {
        this.item = cloneDeep(this.originalItem)
      }
    }
  },
  methods: {
    confirm () {
      this.loading = true
      this.$itemService.vincularItemCategoria(this.item).then((result) => {
        if (result) {
          this.$emit('change')
          this.closeDialog()
        }
      }).finally(this.stopLoading)
    },
    closeDialog () {
      this.mostrar = false
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
