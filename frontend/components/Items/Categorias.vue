<template>
  <v-container fluid>
    <v-row align="center">
      <v-col v-if="edit">
        <TheAutocompleteInput
          v-model="values"
          :items="items"
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
import TheAutocompleteInput from '~/components/General/Inputs/TheAutocompleteInput'
export default {
  components: {
    TheButtonWithToolTip,
    TheAutocompleteInput
  },
  data: () => ({
    items: ['foo', 'bar', 'fizz', 'buzz'],
    values: [],
    value: null,
    edit: false
  })
}

</script>
