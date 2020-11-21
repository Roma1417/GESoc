<template>
  <v-card>
    <v-card-title>
      {{ $t('presupuestos.detalles_precio') }}
    </v-card-title>
    <TheFilterTable
      class="px-4"
      :items="detalles"
      :headers="headers"
      :total="totalList"
    >
      <template #[`item.precio`]="{ item }">
        <TheNumericInput
          v-model="item.precio"
          :rules="[$rl.positive()]"
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
    detalles: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      detailAux: {},
      totalList: 5
    }
  },
  computed: {
    headers () {
      return [
        {
          text: this.$t('presupuestos.detalles_operacion'),
          align: 'center',
          sortable: false,
          value: 'detalleOperacion.detalleOperacionId'
        },
        {
          text: this.$t('items.item'),
          align: 'start',
          sortable: false,
          value: 'item.descripcion'
        },
        {
          text: this.$t('detalles.precio'),
          align: 'right',
          sortable: false,
          width: '18%',
          value: 'precio'
        }
      ]
    }
  },
  methods: {
    deleteDetail (detail) {
      const index = this.detalles.indexOf(detail)
      this.detalles.splice(index, 1)
    },
    addNewDetail () {
      if (this.detailAux.item) {
        const newDetail = {
          item: this.detailAux.item,
          cantidad: null,
          precio: null
        }
        this.detailAux.item = null
        this.detalles.push(newDetail)
      }
    }
  }
}
</script>
