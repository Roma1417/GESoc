<template>
  <TheFormDialog>
    <template #activator>
      <TheButtonWithToolTip
        icon="mdi-pencil"
        title="categorias.editar"
        v-bind="$attrs"
        v-on="$listeners"
        @click="$emit('value', true )"
      />
    </template>
    <TheCategoriasAutocomplete />
  </TheFormDialog>
</template>

<script>
import TheCategoriasAutocomplete from '~/components/Business/Autocomplete/TheCategoriasAutocomplete'
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheButtonWithToolTip from '~/components/General/Buttons/TheButtonWithToolTip'
export default {
  components: {
    TheCategoriasAutocomplete,
    TheFormDialog,
    TheButtonWithToolTip
  }
}
</script>
