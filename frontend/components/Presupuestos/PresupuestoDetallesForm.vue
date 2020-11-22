<template>
  <v-card>
    <v-row>
      <v-card-title class="pl-8">
        {{ $t('presupuestos.detalles_precio') }}
      </v-card-title>
      <v-alert
        class="pt-3 mt-4"
        color="primary"
        outlined
        dense
        type="info"
      >
        {{ $t('presupuestos.info_cargar_precios') }}
      </v-alert>
    </v-row>
    <TheFilterTable
      class="px-4"
      :items="detallesPrecio"
      :headers="headers"
      :total="detallesPrecio.length"
    >
      <template #[`item.precio`]="{ item }" class="ml-100">
        <TheNumericInput
          v-model="item.precio"
        />
      </template>
    </TheFilterTable>
  </v-card>
</template>
<script>
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import { numericMixin } from '~/services/mixins/numericMixin'
import TheNumericInput from '~/components/General/Inputs/TheNumericInput'
export default {
  components: {
    TheFilterTable,
    TheNumericInput
  },
  mixins: [numericMixin],
  props: {
    detallesPrecio: {
      type: Array,
      required: true
    }
  },
  computed: {
    headers () {
      return [
        {
          text: this.$t('presupuestos.detalles_operacion_ID'),
          align: 'center',
          sortable: false,
          value: 'detalleOperacion.detalleOperacionId',
          width: '20%'
        },
        {
          text: this.$t('items.item'),
          align: 'start',
          sortable: false,
          value: 'detalleOperacion.item.descripcion',
          width: '20%'
        },
        {
          text: this.$t('detalles.precio'),
          align: 'center',
          sortable: false,
          width: '15%',
          value: 'precio'
        }
      ]
    }
  }
}
</script>
