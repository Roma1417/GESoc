<template>
  <v-container fluid>
    <v-row align="center">
      <v-col v-if="edit">
        <TheCategoriasAutocomplete
          v-model="values"
          multiple
        />
      </v-col>
      <v-col v-else>
        {{ !values.length ? "Sin categorias" : values.join() }}
      </v-col>
      <v-col v-if="edit">
        <TheButtonWithToolTip
          icon="mdi-floppy"
          v-bind="$attrs"
          title="categorias.editar"
          v-on="$listeners"
          @click="edit = false"
        />
      </v-col>
      <v-col v-else>
        <TheButtonWithToolTip
          icon="mdi-pencil"
          v-bind="$attrs"
          title="categorias.editar"
          v-on="$listeners"
          @click="edit = true"
        />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import TheButtonWithToolTip from '~/components/General/Buttons/TheButtonWithToolTip'
import TheCategoriasAutocomplete from '~/components/Business/Autocomplete/TheCategoriasAutocomplete'
export default {
  components: {
    TheButtonWithToolTip,
    TheCategoriasAutocomplete
  },
  data: () => ({
    items: ['foo', 'bar', 'fizz', 'buzz'],
    values: [],
    value: null,
    edit: false
  })
}

</script>
