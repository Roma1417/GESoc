<template>
  <v-row class="px-2">
    <v-col md="4" sm="6">
      <TheTextInput
        :label="this.$t('documento_comercial.numero')"
        :rules="[$rl.required()]"
      />
    </v-col>
    <v-col md="4" sm="6">
      <TheTextInput
        :label="this.$t('documento_comercial.tipo')"
        :rules="[$rl.required()]"
      />
    </v-col>
    <v-col md="4" sm="6">
      <TheAutocompleteInput
        :label="this.$t('documento_comercial.pais')"
        :rules="[$rl.required()]"
      />
    </v-col>
    <v-col md="4" sm="6">
      <TheAutocompleteInput
        :label="this.$t('documento_comercial.moneda')"
        :rules="[$rl.required()]"
      />
    </v-col>
  </v-row>
</template>
<script>
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheAutocompleteInput from '~/components/General/Inputs/TheAutocompleteInput'

export default {
  components: {
    TheTextInput,
    TheAutocompleteInput
  }
}
</script>
