<template>
  <v-card>
    <v-card-title>
      {{ $t('documento_comercial.titulo') }}
    </v-card-title>
    <v-row class="px-4">
      <TheResponsiveColumn>
        <TheTextInput
          v-model="documentoComercial.numero"
          :label="$t('documento_comercial.numero')"
          :rules="[$rl.required(),$rl.positive()]"
        />
      </TheResponsiveColumn>
      <TheResponsiveColumn>
        <TheTextInput
          v-model="documentoComercial.tipoDocumento"
          :label="$t('documento_comercial.tipo')"
          :rules="[$rl.required(),$rl.positive()]"
        />
      </TheResponsiveColumn>
      <TheResponsiveColumn>
        <TheAsyncAutocompleteInput
          v-model="documentoComercial.pais"
          item-text="descripcion"
          :get-items-function="$paisService.getPaises"
          :label="$t('pais.pais')"
          :rules="[$rl.required()]"
        />
      </TheResponsiveColumn>
      <TheResponsiveColumn>
        <TheAsyncAutocompleteInput
          v-model="documentoComercial.moneda"
          item-text="descripcion"
          :get-items-function="$monedaService.getMonedas"
          :label="$t('moneda.moneda')"
          :rules="[$rl.required()]"
        />
      </TheResponsiveColumn>
    </v-row>
  </v-card>
</template>
<script>
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
import TheResponsiveColumn from '~/components/General/Columns/TheResponsiveColumn'
export default {
  components: {
    TheTextInput,
    TheAsyncAutocompleteInput,
    TheResponsiveColumn
  },
  props: {
    documentoComercial: {
      type: Object,
      required: true
    }
  }
}
</script>
