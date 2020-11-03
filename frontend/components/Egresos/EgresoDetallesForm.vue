<template>
  <v-card>
    <v-card-title>
      {{ $t('egresos.detalles') }}
    </v-card-title>
    <TheAsyncAutocompleteInput
      v-model="detailAux.item"
      item-text="descripcion"
      :get-items-function="$itemService.getItems"
      :label="$t('item.item')"
      @submit="addNewDetail"
    />
    <TheFilterTable
      class="px-4"
      :items="detalles"
      :headers="headers"
      :total="totalList"
    >
      <template #[`item.descripcion`]="{ }">
        {{ item.descripcion }}
      </template>
      <template #[`item.actions`]="{ }">
        <TheButtonWithTooltip
          icon="mdi-trash-can"
          title="Eliminar detalle"
          @click="deleteDetail"
        />
      </template>
    </TheFilterTable>
  </v-card>
</template>
<script>
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import TheButtonWithTooltip from '~/components/General/Buttons/TheButtonWithTooltip'
export default {
  components: {
    TheFilterTable,
    TheButtonWithTooltip
  },
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
          align: 'center',
          sortable: false,
          value: 'cantidad'
        },
        {
          text: this.$t('detalles.precio'),
          align: 'end',
          sortable: false,
          value: 'precio'
        },
        {
          text: this.$t('detalles.total'),
          align: 'end',
          sortable: false,
          value: 'total'
        },
        {
          text: this.$t('actions'),
          value: 'actions',
          sortable: false
        }
      ]
    }
  },
  methods: {
    newEmptyDetail () {
      return {
        item: {}
      }
    },
    deleteDetail (detail) {

    }
  }
}
</script>
