<template>
  <v-card>
    <v-card-title>
      {{ $t('presupuestos.detalles_precio') }}
    </v-card-title>
    <v-row class="px-5">
      <TheResponsiveColumn>
        <TheAsyncAutocompleteInput
          v-model="detailAux.item"
          item-text="descripcion"
          :ignore-items="detalles.map(detalle => detalle.item)"
          :match-ignore-function="(i1, i2) => i1.id === i2.id"
          :cache-items="false"
          :get-items-function="$itemService.getItems"
          :label="$t('items.item')"
          @keyup.enter="addNewDetail"
        />
      </TheResponsiveColumn>
      <v-col>
        <v-alert
          color="primary"
          outlined
          dense
          type="info"
        >
          {{ $t('items.info_ingresar_item') }}
        </v-alert>
      </v-col>
    </v-row>
    <TheFilterTable
      class="px-4"
      :items="detalles"
      :headers="headers"
      :total="totalList"
    >
      <template #[`item.cantidad`]="{ item }">
        <TheNumericInput
          v-model="item.cantidad"
          :rules="[$rl.required(),$rl.positive()]"
        />
      </template>
      <template #[`item.precio`]="{ item }">
        <TheNumericInput
          v-model="item.precio"
          :rules="[$rl.required(),$rl.positive()]"
        />
      </template>
      <template #[`item.actions`]="{ item }">
        <TheButtonWithTooltip
          icon="mdi-trash-can"
          title="Eliminar detalle"
          @click="deleteDetail(item)"
        />
      </template>
      <template #[`item.total`]="{ item }">
        ${{ financial(item.cantidad * item.precio) }}
      </template>
    </TheFilterTable>
  </v-card>
</template>
<script>
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import TheButtonWithTooltip from '~/components/General/Buttons/TheButtonWithTooltip'
import { numericMixin } from '~/services/mixins/numericMixin'
import TheNumericInput from '~/components/General/Inputs/TheNumericInput'
export default {
  components: {
    TheFilterTable,
    TheButtonWithTooltip,
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
          text: this.$t('items.item'),
          align: 'start',
          sortable: false,
          value: 'item.descripcion'
        },
        {
          text: this.$t('detalles.cantidad'),
          align: 'right',
          sortable: false,
          width: '15%',
          value: 'cantidad'
        },
        {
          text: this.$t('detalles.precio'),
          align: 'right',
          sortable: false,
          width: '18%',
          value: 'precio'
        },
        {
          text: this.$t('detalles.total'),
          align: 'right',
          sortable: false,
          width: '20%',
          value: 'total'
        },
        {
          text: this.$t('actions'),
          value: 'actions',
          width: '17%',
          sortable: false
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
