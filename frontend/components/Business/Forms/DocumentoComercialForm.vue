<template>
  <v-card>
    <v-card-title>
      {{ $t('documento_comercial.titulo') }}
    </v-card-title>
    <v-row class="px-2">
      <v-col md="4" sm="6" cols="12">
        <TheTextInput
          v-model="documentoComercial.numero"
          :label="this.$t('documento_comercial.numero')"
          :rules="[$rl.required()]"
        />
      </v-col>
      <v-col md="4" sm="6" cols="12">
        <TheTextInput
          v-model="documentoComercial.tipoDocumento"
          :label="this.$t('documento_comercial.tipo')"
          :rules="[$rl.required()]"
        />
      </v-col>
      <v-col md="4" sm="6" cols="12">
        <TheAsyncAutocompleteInput
          v-model="documentoComercial.pais"
          item-text="descripcion"
          :get-items-function="$paisService.getPaises"
          :label="$t('pais.pais')"
          :rules="[$rl.required()]"
        />
      </v-col>
      <v-col md="4" sm="6" cols="12">
        <TheAsyncAutocompleteInput
          v-model="documentoComercial.moneda"
          item-text="descripcion"
          :get-items-function="$monedaService.getMonedas"
          :label="$t('moneda.moneda')"
          :rules="[$rl.required()]"
        />
      </v-col>
    </v-row>
  </v-card>
</template>
<script>
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
export default {
  components: {
    TheTextInput,
    TheAsyncAutocompleteInput
  },
  props: {
    documentoComercial: {
      type: Object,
      required: true
    }
  }
}
</script>
