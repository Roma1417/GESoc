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
      <ThePaisAutocomplete
        :label="this.$t('documento_comercial.pais')"
        :rules="[$rl.required()]"
      />
    </v-col>
    <v-col md="4" sm="6">
      <TheMonedaAutocomplete
        :label="this.$t('documento_comercial.moneda')"
        :rules="[$rl.required()]"
      />
    </v-col>
  </v-row>
</template>
<script>
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import ThePaisAutocomplete from '~/components/Business/Autocomplete/ThePaisAutocomplete'
import TheMonedaAutocomplete from '~/components/Business/Autocomplete/TheMonedaAutocomplete'

export default {
  components: {
    TheTextInput,
    TheMonedaAutocomplete,
    ThePaisAutocomplete
  }
}
</script>
